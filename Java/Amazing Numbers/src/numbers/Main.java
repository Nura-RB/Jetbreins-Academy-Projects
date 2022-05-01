package numbers;
;
import java.util.Scanner;
import static numbers.NumberChecker.*;

public class Main {

    static void printHello() {
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println(" * the first parameter represents a starting number;");
        System.out.println(" * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }

    static void printPropOfOne (long num) {
        System.out.printf("Properties of %d\n", num);
        System.out.printf("buzz: %b\n", isBuzz(num));
        System.out.printf("duck: %b\n", isDuck(num));
        System.out.printf("palindromic: %b\n", isPalindromic(num));
        System.out.printf("gapful: %b\n", isGapful(num));
        System.out.printf("spy: %b\n", isSpy(num));
        System.out.printf("square: %b\n", isSquare(num));
        System.out.printf("sunny: %b\n", isSunny(num));
        System.out.printf("jumping: %b\n", isJumping(num));
        System.out.printf("happy: %b\n", isHappy(num));
        System.out.printf("sad: %b\n", !isHappy(num));
        System.out.printf("even: %b\n", isEven(num));
        System.out.printf("odd: %b\n", !isEven(num));
    }

    static void printPropOfMany (long num, long amount) {
        for (int i = 0; i < amount; i++){
            System.out.printf("%d is", num);
            if (isBuzz(num))
                System.out.print(" buzz,");
            if (isDuck(num))
                System.out.print(" duck,");
            if (isPalindromic(num))
                System.out.print(" palindromic,");
            if (isGapful(num))
                System.out.print(" gapful,");
            if (isSpy(num))
                System.out.print(" spy,");
            if (isSquare(num))
                System.out.print(" square,");
            if (isSunny(num))
                System.out.print(" sunny,");
            if (isJumping(num))
                System.out.print(" jumping,");
            if (isHappy(num))
                System.out.print(" happy,");
            else
                System.out.print(" sad,");
            if (isEven(num))
                System.out.println(" even");
            else
                System.out.println(" odd");
            num++;
        }
    }

    static boolean isProp (long num, String filter) {
        if (filter.equalsIgnoreCase("buzz") && isBuzz(num))
            return true;
        else if (filter.equalsIgnoreCase("duck") && isDuck(num))
            return true;
        else if (filter.equalsIgnoreCase("palindromic") && isPalindromic(num))
            return true;
        else if (filter.equalsIgnoreCase("gapful") && isGapful(num))
            return true;
        else if (filter.equalsIgnoreCase("spy") && isSpy(num))
            return true;
        else if (filter.equalsIgnoreCase("square") && isSquare(num))
            return true;
        else if (filter.equalsIgnoreCase("sunny") && isSunny(num))
            return true;
        else if (filter.equalsIgnoreCase("jumping") && isJumping(num))
            return true;
        else if (filter.equalsIgnoreCase("happy") && isHappy(num))
            return true;
        else if (filter.equalsIgnoreCase("sad") && !isHappy(num))
            return true;
        else if (filter.equalsIgnoreCase("even") &&  isEven(num))
            return true;
        else if (filter.equalsIgnoreCase("odd")  && !isEven(num))
            return true;
        return false;
    }

    static String removeMinus (String str) {
        StringBuilder sb = new StringBuilder(str);
        sb = sb.deleteCharAt(0);
        return String.valueOf(sb);
    }

    static boolean filterProps (long num, String[] props) {
        for (int i = 2; i < props.length; i++) {
            if (props[i].contains("-")) {
                if (isProp(num, removeMinus(props[i])))
                    return false;
            } else if(!isProp(num, props[i])) {
                return false;
            }
        }
        return true;
    }

    static void printWithProps(long num, long amount, String[] props) {
        int i = 0;
        while (i < amount) {
            if (filterProps(num, props)) {
                printPropOfMany(num, 1);
                i++;
            }
            num++;
        }
    }


    static void amazingNumbers () {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("\nEnter a request: ");
            String[] usrInput = scan.nextLine().split(" ");

            if (usrInput[0].equals("0")) {
                System.out.println("Goodbye!");
                System.exit(1);
            }
            if (usrInput.length == 1) {
                ErrorHandler.checkInputNumbers(usrInput);
                printPropOfOne((Long.parseLong(usrInput[0])));
            } else if (usrInput.length == 2) {
                ErrorHandler.checkInputNumbers(usrInput);
                printPropOfMany(Long.parseLong(usrInput[0]), Long.parseLong(usrInput[1]));
            } else if (usrInput.length > 2) {
                ErrorHandler.checkInputProps(usrInput);
                ErrorHandler.ifMutualExclusive(usrInput);
                printWithProps(Long.parseLong(usrInput[0]), Long.parseLong(usrInput[1]), usrInput);
            }
        }
    }

    public static void main(String[] args) {
        printHello();
        amazingNumbers();
    }
}