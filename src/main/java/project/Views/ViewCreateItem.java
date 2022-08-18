package project.Views;

import project.Utilities;

public class ViewCreateItem {
    public String itemId() {
        System.out.println(Utilities.line() + "Creating an Item. Please enter the item's:\n " +
                "ID:" + System.lineSeparator());
        String id = Utilities.stringInput();

        return id;
    }

    public String itemName() {
        System.out.println("Name:");
        String name = Utilities.stringInput();

        return name;
    }

    public Double price() {
        System.out.println("Price:");
        Double price = Utilities.doubleInput();

        return price;
    }

    public void creationSuccess(String id) {
        System.out.println("Item <" + id + "> was registered successfully." );
    }

    public void creationFailed() { System.out.println("Invalid data for item."); }

}
