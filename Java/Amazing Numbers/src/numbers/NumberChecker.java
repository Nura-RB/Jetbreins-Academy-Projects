package numbers;

import java.util.ArrayList;

public class NumberChecker {
    static boolean isEven (long num) {
        if (num % 10 % 2 == 0)
            return true;
        return false;
    }

    static boolean isBuzz (long num) {
        boolean b = Math.abs(num / 10 - num % 10 * 2) % 7 == 0;
        if (b || num % 10 == 7)
            return true;
        return false;
    }

    static boolean isDuck (long num) {
        String strNum = String.valueOf(num);
        if (strNum.contains("0") && strNum.charAt(0) != '0')
            return true;
        return false;
    }

    static boolean isPalindromic(long num) {
        String strNum = String.valueOf(num);
        if(strNum.equals(new StringBuffer(strNum).reverse().toString()))
            return true;
        return false;
    }

    static boolean isGapful(long num) {
        if (num / 100 < 1)
            return false;
        String strNum = String.valueOf(num);
        String div = "";
        div += strNum.charAt(0);
        div += strNum.charAt(strNum.length() - 1);
        if (num % Integer.parseInt(div) == 0)
            return true;
        return false;
    }

    static boolean isSpy(long num) {
        int sumOfDigits = 0;
        int productOfDigits = 1;

        long temp = num;
        while (temp > 0) {
            sumOfDigits += (int)(temp % 10);
            temp = temp / 10;
        }
        while (num > 0) {
            productOfDigits *= (int)(num % 10);
            num = num / 10;
        }
        if (sumOfDigits == productOfDigits)
            return true;
        return false;
    }

    static boolean isSquare (long num) {
        if (Math.sqrt(num) % 1 == 0)
            return true;
        else return false;
    }

    static boolean isSunny (long num) {
        if (Math.sqrt(num + 1) % 1 == 0)
            return true;
        else return false;
    }

    static boolean isJumping (long num) {
        int[] digits = Long.toString(num).chars().map(c -> c-'0').toArray();
        if (digits.length == 1)
            return true;
        for(int i = 1; i < digits.length; i++) {
            if (Math.abs(digits[i] - digits[i - 1]) != 1)
                return false;
        }
        return true;
    }

    static boolean isHappy (long num) {
        ArrayList<Long> sequence = new ArrayList<>();
        sequence.add(num);

        if (num != 1) {
            for (int i = 0; i < sequence.size(); i++) {
                long sum = 0;
                if (sequence.get(i) == 1) { return true; }

                long check = sequence.get(i);
                while (check != 0) {
                    sum += Math.pow(check % 10, 2);
                    check /= 10;
                }

                if (sum == 145 || sum == 3 || sum == 4 || sum == 5 || sum == 6) { return false; }

                sequence.add(sum);
            }
        }
        return true;
    }
}
