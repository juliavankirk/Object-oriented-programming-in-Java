package project.Views;

import project.Utilities;

public class ViewRemoveEmployee {
    public void promptRemoveEmployee() {
        System.out.println(Utilities.line()+ "Enter the ID of the item you would like to remove:");
    }

    public void empRemovalSuccess(String ID) {
        System.out.println("Employee " + ID + " was successfully removed.");
    }

    public void empRemovalError(String ID) {
        System.out.println("Employee " + ID + " could not be removed.");
    }

    public String removeEmp() {
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
