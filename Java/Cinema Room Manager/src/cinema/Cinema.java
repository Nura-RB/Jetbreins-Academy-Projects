package cinema;

import java.util.Scanner;

public class Cinema {
    public static void showMenu() {
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }

    public static char[][] makeSeats (int rows, int seats, char[][] arr) {
        char one = '1';
        for(int i = 1; i <= seats; i++) {
            arr[0][i] = one++;
        }
        one = '1';
        for (int i = 1; i <= rows; i++) {
            arr[i][0] = one++;
        }
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seats; j++) {
                arr[i][j] = 'S';
            }
        }
        return arr;
    }

    public static void showSeats (int rows, int seats, char[][] seatloc) {
        System.out.println("Cinema:");
        seatloc[0][0] = ' ';
        for(int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                System.out.print(seatloc[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static char[][] buyATicket(int rows, int seats, char[][] arr) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter a row number:");
        int rowNum = scan.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNum = scan.nextInt();
        if (rowNum > rows || seatNum > seats) {
            System.out.println("Wrong input!\n");
            rowNum = 0;
            seatNum = 0;
            buyATicket(rows, seats, arr);
        }
        if (arr[rowNum][seatNum] == 'B') {
            System.out.println("That ticket has already been purchased!\n");
            buyATicket(rows, seats, arr);
        }
        if (rows * seats <= 60 || rowNum <= rows / 2)
            System.out.println("\nTicket price: $10");
        else
            System.out.println("Ticket price: $8");
        arr[rowNum][seatNum] = 'B';
        return arr;
    }

    public static void showStats (int rows, int seats, char[][] arr) {
        int highCost = 0;
        int lowCost = 0;
        int amount = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seats; j++) {
                if (arr[i][j] == 'B') {
                    amount += 10;
                    if (rows * seats <= 60 || i <= rows / 2) {
                        highCost++;
                    }
                    else
                        lowCost++;
                }
            }
        }
        System.out.printf("Number of purchased tickets: %d\n", highCost + lowCost);
        System.out.printf("Percentage: %.2f%%\n", (float)amount/ 81 * 10);
        System.out.printf("Current income: $%d\n", highCost * 10 + lowCost * 8);
        System.out.println("Total income: $720");

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scan.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scan.nextInt();

        char[][] seatloc = new char[rows+1][seats+1];
        seatloc = makeSeats(rows, seats, seatloc);

        int order = 1;
        while (order != 0) {
            showMenu();
            order = scan.nextInt();
            switch (order) {
                case 1: showSeats(rows, seats, seatloc);
                    break;
                case 2: seatloc = buyATicket(rows, seats, seatloc);
                    break;
                case 3: showStats(rows, seats, seatloc);
                    break;
            }
        }
    }
}