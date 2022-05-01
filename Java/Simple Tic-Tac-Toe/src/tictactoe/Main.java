package tictactoe;

import java.util.Scanner;

public class Main {
   public static boolean checkWinX(char[] arr) {
        if (arr[0] + arr[3] + arr[6] == 264
                || arr[1] + arr[4] + arr[7] == 264
                || arr[2] + arr[5] + arr[8] == 264
                || arr[0] + arr[1] + arr[2] == 264
                || arr[3] + arr[4] + arr[5] == 264
                || arr[6] + arr[7] + arr[8] == 264
                || arr[0] + arr[4] + arr[8] == 264
                || arr[2] + arr[4] + arr[6] == 264)
            return true;
        return false;
    }

    public static boolean checkWinO(char[] arr) {
            if (arr[0] + arr[3] + arr[6] == 237
                    || arr[1] + arr[4] + arr[7] == 237
                    || arr[2] + arr[5] + arr[8] == 237
                    || arr[0] + arr[1] + arr[2] == 237
                    || arr[3] + arr[4] + arr[5] == 237
                    || arr[6] + arr[7] + arr[8] == 237
                    || arr[0] + arr[4] + arr[8] == 237
                    || arr[2] + arr[4] + arr[6] == 237)
                return true;
            return false;
    }
    
    public static boolean checkDraw (char[] arr) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (arr[i] == ' ')
                count++;
        }
        if (count == 0)
            return true;
        return false;
    }

    public static char[] scanCrdnts(char[] arr, int i) {
        Scanner scan = new Scanner(System.in);
        int crdntX = scan.nextInt();
        int crdntY = scan.nextInt();
        char ticOrTac;
        if (crdntX < 1 || crdntX > 3 || crdntY < 1 || crdntY > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return null;
        }
        if (crdntX == 1 && arr[crdntX + crdntY - 2] != ' ' ||
                crdntX == 2 && arr[crdntX + crdntY] != ' ' ||
                crdntX == 3 && arr[crdntX + crdntY + 2] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return null;
        }
            System.out.println();
        if (crdntX < 0 || crdntX > 9 || crdntY < 0 || crdntY > 9) {
            System.out.println("You should enter numbers!");
            return null;
        }
        if (i % 2 == 1)
            ticOrTac = 'X';
        else
            ticOrTac = 'O';
        if(crdntX == 1)
            arr[crdntX + crdntY - 2] = ticOrTac;
        else if (crdntX == 2)
            arr[crdntX + crdntY] = ticOrTac;
        else if (crdntX == 3)
            arr[crdntX + crdntY + 2] = ticOrTac;
        return arr;
    }

    public static void ticTacOut (char [] arr) {
        for (int i = 0; i < 9; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < arr.length; i += 3) {
            System.out.printf("| %c %c %c |%n", arr[i], arr[i + 1], arr[i + 2]);
        }
        for (int i = 0; i < 9; i++) {
            System.out.print("-");
        }
    }

    public static void main(String[] args) {
        char[] arr = new char[9];
        for (int i = 0; i < 9; i++)
            arr[i] = ' ';
        int i = 1;
        while (true) {
            ticTacOut(arr);
            System.out.println("\nEnter the coordinates:");
            if (scanCrdnts(arr, i) != null)
                i++;
            if (checkWinX(arr)) {
                ticTacOut(arr);
                System.out.println("X wins");
                break;
            }else if (checkWinO(arr)) {
                ticTacOut(arr);
                System.out.println("O wins");
                break;
            }
            if (checkDraw(arr)) {
                ticTacOut(arr);
                System.out.println("Draw");
                break;
            }
        }
    }               
}
