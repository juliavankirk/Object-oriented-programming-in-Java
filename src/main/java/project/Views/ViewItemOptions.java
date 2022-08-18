package project.Views;

import project.Utilities;

public class ViewItemOptions {

    public void renderItemOptions() {
        System.out.println(Utilities.line() +
                "Item options menu:\n" +
                "0. Return to main menu.\n" +
                "1. Create an Item.\n" +
                "2. Remove an Item.\n" +
                "3. Print all registered items.\n" +
                "4. Buy an item.\n" +
                "5. Update am item's name\n" +
                "6. Update an item's price.\n" +
                "7. Print a specific item.\n" +

                "Type an option number:\n"
                );
    }

    public void returnMainMenu() { System.out.println("Returning to main menu.\n"); }

    public int itemOptionsInput() {
        int itemOptionsInput = Utilities.intInput();

        return itemOptionsInput;
    }
}
