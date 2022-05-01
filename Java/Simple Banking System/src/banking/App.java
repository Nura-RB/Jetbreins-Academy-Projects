package banking;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;


public class App {

    static void mainMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while (true) {
            showMainMenu();
            choice = scanner.next();

            if(choice.equals("1"))
                ControlDB.createCard();
            else if(choice.equals("2"))
                ControlDB.logIn();
            else if(choice.equals("0"))
                break;
            else
                System.out.println("Please, choose correct option!");
        }
    }

    static void showMainMenu() {
        System.out.println("\n1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }

    static void showCardMenu() {
        System.out.println("1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
    }

    // --------------------- Card Menu -------------------------------

    static void loginMenu(String cardNum, String pin) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (true) {
            App.showCardMenu();
            choice = scanner.nextInt();
            if(choice == 1)
                ControlDB.showBalance(cardNum, pin);
            else if(choice == 2)
                ControlDB.addIncome(cardNum, pin);
            else if(choice == 3)
                ControlDB.doTransfer(cardNum, pin);
            else if(choice == 4)
                ControlDB.closeAccount(cardNum);
            else if(choice == 5) {
                System.out.println("You have successfully logged out!");
                App.mainMenu();
            }else if(choice == 0) {
                System.out.println("Bye!");
                exit(1);
            }
        }
    }

    static boolean checkForLuhnAl(String cardNum) {

        int luhnSum = 0;
        int[] arr = new int[cardNum.length()];

        for (int i = 0; i < cardNum.length(); i++)
            arr[i] = cardNum.toCharArray()[i] - '0';

        for (int i = 0; i < arr.length; i++) {
            if(i % 2 == 0) {
                if (arr[i] * 2 > 9) {
                    luhnSum += (arr[i] * 2) - 9;
                } else
                    luhnSum += arr[i] * 2;
            } else
                luhnSum += arr[i];
        }

        if (luhnSum % 10 != 0)
            return false;

        return true;
    }

    //-------------------------------------------------------------------------


}
