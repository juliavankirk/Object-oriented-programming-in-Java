package project.Views;

import project.Utilities;

public class ViewMainMenu {

    public void renderMenu() {
        System.out.println(Utilities.line() +
                "Main menu: Please choose among the options below.\n\n" +
                "0. Close system.\n" +
                "1. Open Item options.\n" +
                "2. Open Review options.\n" +
                "3. Open Transaction History options.\n" +
                "4. Open Employee options.\n" +

                "Type an option number: \n"
        );
    }

    public void renderExit() {
        System.out.println("Exiting the system. Goodbye!");
    }

    public void renderError() {
        System.out.println("Invalid selection, restarting...\n");
    }

    public int readInput() {
        int entryMenuSelect = Utilities.intInput();

        return entryMenuSelect;
    }
}
