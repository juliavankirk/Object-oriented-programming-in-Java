package project.Views;

import project.Utilities;

public class ViewTransactionHistoryOptions {
    public void transactionHistoryOptions() {
        System.out.println(Utilities.line() +
                "Transaction History options menu:\n" +
                "0. Return to main menu.\n" +
                "1. Print total profit from all item purchases\n" +
                "2. Print total units sold from all item purchases\n" +
                "3. Print the total number of item transactions made.\n" +
                "4. Print all transactions made.\n" +
                "5. Print the total profit of a specific item.\n" +
                "6. Print the number of units sold of a specific item.\n" +
                "7. Print all transactions of a specific item.\n" +
                "8. Print item with highest profit.\n" +

                "Type an option number:\n\n"
                );
    }

    public void returnMainMenu() { System.out.println("Returning to main menu.\n"); }

    public int transactionHistoryOptionsInput() {
        int input = Utilities.intInput();

        return input;
    }
}
