package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.Scanner;

import static java.lang.System.exit;



public class ControlDB {
    static SQLiteDataSource dataSource = new SQLiteDataSource();


    //------------------- Table Creation Method ---------------------------

    static void createNewTable(String fileName) {
        String url = "jdbc:sqlite:./" + fileName;
        dataSource.setUrl(url);

        String sql = "CREATE TABLE IF NOT EXISTS card (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	number TEXT,\n"
                + "	pin TEXT,\n"
                + "balance INTEGER DEFAULT 0"
                + ");";

        try (Connection con = dataSource.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //-------------------- Card Creation method -----------------------

    static Card createCard() {
        Card newCard = new Card();

        String sqlStmnt = "INSERT INTO card(number,pin) VALUES(?,?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlStmnt)) {
            pstmt.setString(1, newCard.getCardNum());
            pstmt.setString(2, newCard.getPin());
            pstmt.executeUpdate();
            System.out.println("Your card has been created");
            System.out.printf("Your card number:\n%s\n", newCard.getCardNum());
            System.out.printf("Your card PIN:\n%s\n", newCard.getPin());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return newCard;
    }

    //-------------------- Login Into Account --------------------

    static void logIn() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your card number:");
        String cardNum = scanner.nextLine();

        System.out.println("Enter your PIN:");
        String pin = scanner.nextLine();

        if(checkLogin(cardNum, pin)) {
            System.out.println("You have successfully logged in!");
            App.loginMenu(cardNum, pin);
        } else
            System.out.println("Wrong card number or PIN!");


    }

    //---------------------- Checking For Carrect Login ---------------------

    static boolean checkLogin(String cardNum, String pin) {
        String sql = "SELECT number, pin FROM card WHERE number = ? and pin = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, cardNum);
            pstmt.setString(2, pin);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    boolean found = rs.getBoolean(1);
                    if (found) {
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    //------------------- Show Balance Method ---------------------------

    static void showBalance(String cardNum, String pin) {

        String sql = "SELECT balance FROM card WHERE number = ? and pin = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, cardNum);
            pstmt.setString(2, pin);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("balance: " + rs.getInt("balance"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //---------------------- Add Income method ---------------------

    static void addIncome(String cardNum, String pin) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter income:");

        int income = scanner.nextInt();

        String sql = "UPDATE card SET balance = balance + ?"
                + "WHERE number = ? and pin = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmnt = conn.prepareStatement(sql)) {
            pstmnt.setInt(1, income);
            pstmnt.setString(2, cardNum);
            pstmnt.setString(3, pin);

            pstmnt.executeUpdate();
            System.out.println("Income was added!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //------------------- Transferring Money To Another Card -------------------

    static void doTransfer(String cardNum, String pin) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Transfer");
        System.out.println("Enter card number:");

        String anotherCardNum = scanner.next();

        if (!App.checkForLuhnAl(anotherCardNum)) {
            System.out.println("Probably you made a mistake in the card number. Please try again!\n");
            App.loginMenu(cardNum, pin);
        }
        if (!checkForExist(anotherCardNum)) {
            System.out.println("Such a card does not exist.");
            App.loginMenu(cardNum, pin);
        }
        System.out.println("Enter how much money you want to transfer:");
        int amount = scanner.nextInt();
        if (amount > checkBalance(cardNum)) {
            System.out.println("Not enough money!");
            App.loginMenu(cardNum, pin);
        }


        String sqlSendMoney = "UPDATE card SET balance = balance - ? WHERE number = ?";
        String sqlReceiveMoney = "UPDATE card SET balance = balance + ? WHERE number = ?";

        try (Connection con = dataSource.getConnection()){
            con.setAutoCommit(false);

            try (PreparedStatement pstmt1 = con.prepareStatement(sqlSendMoney);
                 PreparedStatement pstmt2 = con.prepareStatement(sqlReceiveMoney)){

                pstmt1.setInt(1, amount);
                pstmt1.setString(2, cardNum);
                pstmt1.executeUpdate();

                pstmt2.setInt(1, amount);
                pstmt2.setString(2, anotherCardNum);
                pstmt2.executeUpdate();

                con.commit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                if (con != null) {
                    try {
                        System.err.print("Transaction is being rolled back");
                        con.rollback();
                    } catch (SQLException excep) {
                        System.out.println(excep.getMessage());
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //----------------------- Cheking for Existence -----------------------------------------

    static boolean checkForExist(String cardNum) {
        String sql = "SELECT number, pin FROM card WHERE number = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, cardNum);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    boolean found = rs.getBoolean(1);
                    if (found) {
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    //--------------------------------------------------------------------

    static int checkBalance(String cardNum) {
        int balance = 0;
        String sql = "SELECT balance FROM card WHERE number = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, cardNum);
            ResultSet rs = pstmt.executeQuery();
            balance = rs.getInt("balance");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return balance;
    }

    static void closeAccount (String cardName) {
        String sql = "DELETE FROM card WHERE number = ?";

        try (Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){

            pstmt.setString(1, cardName);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("The account has been closed!");
    }
}