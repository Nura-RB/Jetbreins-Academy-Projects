package banking;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ControlDB.createNewTable(args[1]);
        App.mainMenu();
    }
}