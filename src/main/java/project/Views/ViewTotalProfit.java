package project.Views;

import project.Model.Item;
import project.Utilities;

import java.util.UUID;

public class ViewTotalProfit {
    public void showTotalProfit(double totalProfit) {
        System.out.println("All purchases made:\n" +
                "Total profit: " + totalProfit + " SEK");
    }

    public String promptItemSearch() {
        System.out.println("Please enter the item ID:\n");
        String id  = Utilities.stringInput();

        return id;
    }



    public void totalUnitsSold(Integer totalUnits) {
        System.out.println("Total items sold: " + totalUnits + " units");
    }

    public void showAllTransactions(Integer allTransactions) {
        System.out.println("Total purchases made: " + allTransactions + " transactions");
    }

    public void printMostProfitable(Double profit, Item item) {
        System.out.println("Most profitable items:\n" +
                "Total profit: " + profit + " SEK.\n" +
                item);
    }

}
