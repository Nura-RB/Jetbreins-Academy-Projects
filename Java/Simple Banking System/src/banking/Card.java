package banking;

import java.util.Random;

public class Card {
    private String cardNum = "400000";
    private String pin = "";
    private int balance = 0;



    public Card() {

        Random random = new Random();
        int luhnSum = 0;
        int[] arr;

        for(int i = 0; i < 9; i++) {
            cardNum += random.nextInt(9);
        }
        for (int i = 0; i < 4; i++) {
            pin += random.nextInt(9);
        }
        
        arr = new int[cardNum.length()];
        
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

        if (luhnSum % 10 == 0)
            cardNum += 0;
        else
            cardNum += 10 - (luhnSum % 10);
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getPin() {
        return pin;
    }

    public int getBalance() {
        return balance;
    }

}