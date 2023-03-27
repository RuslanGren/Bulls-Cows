import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final char[] symbols = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
    static boolean flag = true;
    public static void main(String[] args) {
        StringBuilder code = new StringBuilder();
        start(code);
    }

    static void start(StringBuilder code) {
        generateCode(code);
        if (flag) play(code);
    }

    static void generateCode(StringBuilder code) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Input the length of the secret code:");
        try {
            int length = scanner.nextInt();
            if (length <= 36 && length > 0) {
                System.out.println("Input the number of possible symbols in the code:");
                int numSymbols = scanner.nextInt();
                if (numSymbols >= length) {
                    while (code.length() != length) {
                        char randomChar = symbols[random.nextInt(numSymbols)];
                        if (!code.toString().contains(String.valueOf(randomChar))) code.append(randomChar);
                    }
                    System.out.printf("The secret is prepared: %s (0-9, a-%c).\n", "*".repeat(length), symbols[numSymbols - 1]);
                } else {
                    System.out.println("Error");
                    flag = false;
                }
            } else {
                System.out.println("Error");
                flag = false;
            }
        } catch (Exception e) {
            System.out.println("Error");
            flag = false;
        }
    }


    static void play(StringBuilder code) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Okay, let's start a game!");
        int counter = 1;
        while (true) {
            System.out.printf("\nTurn %d:\n", counter);
            char[] out = scanner.next().toCharArray();
            int bull = 0, cows = 0;
            for (int i = 0; i < out.length; i++) {
                if (out[i] == code.toString().toCharArray()[i]) bull++;
                else {
                    for (char c : code.toString().toCharArray()) {
                        if (out[i] == c) cows++;
                    }
                }
            }
            if (bull > 0) {
                if (cows > 0) {
                    System.out.printf("Grade: %d bull(s) and %d cow(s).", bull, cows);
                } else System.out.printf("Grade: %d bull(s).", bull);
            } else if (cows > 0) System.out.printf("Grade: %d cow(s).", cows);
            else System.out.print("Grade: None.");
            if (bull == out.length) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
            counter++;
        }
    }

}