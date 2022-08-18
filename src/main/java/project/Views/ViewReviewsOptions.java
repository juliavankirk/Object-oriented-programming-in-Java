package project.Views;

import project.Utilities;

public class ViewReviewsOptions {
    public void reviewsOptionsMenu() {
        System.out.println(Utilities.line() +
                "Reviews options menu:\n" +
                "0. Return to main menu.\n" +
                "1. Create review for an item.\n" +
                "2. Print a specific review of an item.\n" +
                "3. Print all reviews of an item.\n" +
                "4. Print mean grade of an item.\n" +
                "5. Print all comments of an item.\n" +
                "6. Print all registered reviews.\n" +
                "7. Print item(s) with most reviews.\n" +
                "8. Print item(s) with least reviews\n" +
                "9. Print item(s) with best mean review grade.\n" +
                "10. Print item(s) with worst mean review grade.\n\n" +

                "Type an option number:\n"
                );
    }

    public void returnMainMenu() { System.out.println("Returning to main menu.\n"); }

    public int reviewsOptionsInput() {
        int input = Utilities.intInput();

        return input;
    }
}
