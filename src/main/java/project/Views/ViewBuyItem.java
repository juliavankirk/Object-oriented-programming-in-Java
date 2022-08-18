package project.Views;

import project.Utilities;

public class ViewBuyItem {
    public String buyItemPrompt() {
        System.out.println("Please enter the ID of the item you would like to purchase:");
        String id = Utilities.stringInput();

        return id;
    }

    public Integer promptAmount() {
        System.out.println("Please enter the amount of items you would like to purchase:");
        Integer amount = Utilities.intInput();

        return amount;
    }

    public void purchaseSuccessful(String message) {
        System.out.println("Purchase successful: " + message);
    }

}
