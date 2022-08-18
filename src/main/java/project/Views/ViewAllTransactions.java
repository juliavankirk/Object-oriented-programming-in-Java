package project.Views;

import project.Model.Transaction;
import project.Utilities;

import java.util.Collection;

public class ViewAllTransactions {
    public String promptTransactionSearch() {
        System.out.println("Please enter the item ID of transactions you would like to scan:\n");
        String id = inputUUID();

        return id;
    }

    public void printAllTransactions(Collection<Transaction> transactions) {
        System.out.println(Utilities.line() + transactions.toString());
    }

    public void displayItemTransactions(String header) {
        System.out.println("Transactions for item: " + header);
    }

    public String inputUUID() {
        String retVal = null; //initialize as null otherwise compiler doesn't know what to do with it/ have to give it
        //initial value
        try {
            return retVal;
        } catch (IllegalArgumentException e) {
            return retVal;
        }
    }
}
