package project;

import java.util.Scanner;
import java.util.UUID;

public class Utilities {
    private static Scanner scan = new Scanner(System.in);
    public static int intInput () {
        int value;
        value = scan.nextInt();
        scan.nextLine();
        return value;
    }

    public static Double doubleInput () {
        double value;
        value = scan.nextDouble();
        scan.nextLine();
        return value;
    }

    public static String stringInput () {
        return scan.nextLine();
    }

    public static UUID enterID() {
        String input = stringInput();
        UUID id = UUID.fromString(input);
        scan.nextLine();

        return id;
    }

    public static void closeScanner() {
        scan.close();
    }
    public static String line() {
        return "------------------------------------\n";
    }

    public static double trunncate(double inputNumber){
        int inputInt = (int) (inputNumber *100);
        return inputInt/100.00;

    }

}
