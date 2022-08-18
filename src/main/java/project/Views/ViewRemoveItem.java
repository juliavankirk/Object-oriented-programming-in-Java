package project.Views;

import project.Utilities;

public class ViewRemoveItem {
    public void promptRemoveItem() {
        System.out.println(Utilities.line()+ "Enter the ID of the item you would like to remove:");
    }

    public void itemRemovalSuccess(String ID) {
        System.out.println("Item " + ID + " was successfully removed.");
    }

    public void itemRemovalError(String ID) {
        System.out.println("Item " + ID + " could not be removed.");
    }

    public String removeItem() {
        String retVal = null; //initialize as null otherwise compiler doesn't know what to do with it/ have to give it
        //initial value
        try {
            retVal = Utilities.stringInput(); //converts UUID from a string
            return retVal;
        } catch (IllegalArgumentException e) {
            return retVal;
        }
    }
}
