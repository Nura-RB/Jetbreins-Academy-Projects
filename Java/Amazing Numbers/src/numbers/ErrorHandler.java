package numbers;

import java.util.ArrayList;
import java.util.List;

import static numbers.Main.amazingNumbers;
import static numbers.Main.removeMinus;

public class ErrorHandler {

    static boolean isNumber(String numStr) {
        try {
            Long.parseLong(numStr);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    static void checkInputNumbers(String[] str) {
        if (str[0].contains("-") || !isNumber(str[0])) {
            System.out.println("The first parameter should be a natural number or zero.");
            amazingNumbers();
        }
        if (str.length > 1 && (str[1].contains("-") || !isNumber(str[1]) || str[1].equals("0"))) {
            System.out.println("The second parameter should be a natural number.");
            amazingNumbers();
        }
    }

    static void checkInputProps(String[] str) {
        boolean bool = true;
        List<String> properties = List.of("EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE",
                "SUNNY", "JUMPING", "HAPPY", "SAD", "-EVEN", "-ODD", "-BUZZ", "-DUCK", "-PALINDROMIC", "-GAPFUL", "-SPY",
                "-SQUARE", "-SUNNY", "-JUMPING", "-HAPPY", "-SAD");
        List<String> mistakes = new ArrayList<>();
        for (int i = 2; i < str.length; i++) {
            for (int z = 0; z < properties.size(); z++) {
                if (!properties.contains(str[i].toUpperCase())) {
                    bool = false;
                    mistakes.add(str[i].toUpperCase());
                    break;
                }
            }
        }
        if (!bool) {
            if (mistakes.size() == 1)
                System.out.println("The property " + mistakes + " is wrong.");
            else
                System.out.println("The properties " + mistakes + " are wrong.");
            System.out.println("Available properties: " + properties.subList(0, 12));
            amazingNumbers();
        }
    }

    static void ifMutualExclusive(String[] str) {
        boolean bool = true;
        for (int i = 2; i < str.length; i++) {
            for (int z = 2; z < str.length; z++) {
                if (str[i].equalsIgnoreCase("EVEN") && str[z].equalsIgnoreCase("ODD")) {
                    bool = false;
                } else if (str[i].equalsIgnoreCase("DUCK") && str[z].equalsIgnoreCase("SPY")) {
                    bool = false;
                } else if (str[i].equalsIgnoreCase("SUNNY") && str[z].equalsIgnoreCase("SQUARE")) {
                    bool = false;
                } else if (str[i].equalsIgnoreCase("HAPPY") && str[z].equalsIgnoreCase("SAD")) {
                    bool = false;
                }else if (str[i].equalsIgnoreCase("-EVEN") && str[z].equalsIgnoreCase("-ODD")) {
                    bool = false;
                }else if (str[i].equalsIgnoreCase(removeMinus(str[z])))
                    bool = false;
                if (!bool) {
                    System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", str[i], str[z]);
                    break;
                }
            }
        }
        if (!bool) {
            System.out.println("There are no numbers with these properties.");
            amazingNumbers();
        }
    }
}
