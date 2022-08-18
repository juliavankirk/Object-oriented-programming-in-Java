package project.Views;

import project.Utilities;

public class ViewUpdateItem {
    public String updateItemPrompt() {
        System.out.println("Please enter the item ID: ");
        String id = Utilities.stringInput();

        return id;
    }

    public String newItemName(){
        System.out.println("Please enter what you would like to rename this item:\n");
        String name = Utilities.stringInput();

        return name;
    }

    public double newItemPrice() {
        System.out.println("Please enter the new price for this item:\n");
        double price = Utilities.doubleInput();
        return price;
    }

}
