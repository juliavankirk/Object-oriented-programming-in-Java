package project.Views;

import project.Model.Item;
import project.Utilities;

import java.util.Collection;

public class ViewPrintItems {
    public String printItem() {
        System.out.println(Utilities.line() + "Item Database\n" +
                "Please enter item ID:\n");
        String id = inputUUID();

        return id;
    }

    public void displayItemInfo(Item item) {
        String output = item.toString();

        System.out.println(Utilities.line() + output);
    }

    public void printAllItems(Collection<Item> itemList) {
        System.out.println("All registered items:\n");

        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    public void printEmptyItems() {
        System.out.println("No items registered yet.");
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
