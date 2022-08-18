package project.review;


import project.Model.Item;
import project.Model.Model;
import project.Model.Review;
import project.Utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PrintReviewManager {
    public PrintReviewManager() {
    }

    public void printReview(Model model) {

        System.out.println("Please enter the ID of the review");
        String inputID = Utilities.stringInput();
        System.out.println("Please enter the index of the review");
        int index = Utilities.intInput() - 1;

        Item item = model.getItemByID(inputID);
        ArrayList theList = item.getRatingAndComment();


        if (index > theList.size() || index < 0) {
            System.out.println("Invalid review number. Choose between 1 and " + theList.size());
            return;
        }
        if (theList.isEmpty()) {
            System.out.println("Item " + inputID + " was not registered yet.");
            return;
        }

        Review ratingAndComment = item.getReview(index);

        if (ratingAndComment.getComment() == null && ratingAndComment.getRating() == 0) {
            System.out.println("Item " + item.getName() + " has not been reviewed yet."
            );
            return;
        }

        System.out.println("Rating: " + ratingAndComment.getRating() + "\nComment: " + ratingAndComment.getComment());
    }


    public void printAll(Model model) {
//Prints all reviews for one item
        System.out.println("Please specify the id of the item you wish to see the reviews of");

        String inputID = Utilities.stringInput();

        Item item = model.getItemByID(inputID);
        if (item == null) {
            System.out.println("\"Item " + inputID + " was not registered yet.\"\n");
        } else ;

        //local variable to access ratings and comments
        ArrayList theList = item.getRatingAndComment();
        int index = 0;
        Review ratingAndComment; //Create variable
        for (index = 0; index < theList.size(); index++) {
            ratingAndComment = item.getReview(index); //assign value to variable
            System.out.println(ratingAndComment.getRating() + ". " + ratingAndComment.getComment()); //print contents of the variable
        }
    }

    public void meanGrade(Model model) {
        System.out.println("Please specify the id of the item you wish to see the mean grade of");
        float sum = 0f;

        String inputID = Utilities.stringInput();

        Item item = model.getItemByID(inputID);
        if (item == null) {
            System.out.println("\"Item " + inputID + " was not registered yet.\"\n");
        } else ;

        //local variable to access ratings and comments
        ArrayList theList = item.getRatingAndComment();
        int index = 0;
        Review ratingAndComment; //Create variable

        for (index = 0; index < theList.size(); index++) {
            ratingAndComment = item.getReview(index); //assign value to variable

            int rating = ratingAndComment.getRating();
            sum += rating;
        }
        float mean = sum / index;
        String meanString = String.valueOf(mean);
        meanString = meanString.substring(0, 3);
        System.out.println("The mean grade is: " + meanString);


    }

    public Collection printComments(Model model) {
        //prints all comments for an item, If the item ID was not registered or
        // if the item has no reviews or written comments in it, the system should return an empty collection.
        System.out.println("Please specify the id of the item you wish to see the comments of");

        String inputID = Utilities.stringInput();

        Item item = model.getItemByID(inputID);

        //local variable to access ratings and comments
        ArrayList theList = item.getRatingAndComment();
        //returns an empty collection (completely useless, but required)
        if (theList.isEmpty() || item == null) {
            return Collections.EMPTY_LIST;
        }

        int index = 0;
        Review ratingAndComment; //Create variable
        for (index = 0; index < theList.size(); index++) {
            ratingAndComment = item.getReview(index); //assign value to variable
            System.out.println(ratingAndComment.getComment());
        }
        return null;
    }

}



