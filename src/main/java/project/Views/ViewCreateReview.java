package project.Views;


import project.Model.Item;
import project.Model.Model;
import project.Model.Review;
import project.Utilities;

public class ViewCreateReview {

    private Model mModel;

    public ViewCreateReview(Model model) {
        this.mModel = model;
    }

    public void createReview() {

        System.out.println("Please enter the ID of the item you wish to review");
        String id = Utilities.stringInput();
        Item item = mModel.getItemByID(id);
//mModel.getItemByID(ID);
        if (item != null) {
            System.out.println("Please give a score from 1-5");

           int score = Utilities.intInput();
            System.out.println("Please add a comment, if you do not want to add a comment, press enter");

           String comment = Utilities.stringInput();
           if (comment.equals("")) {
               comment = null;
           }
            if (score > 0 && score < 6) {
                System.out.println("You have given " + id + " " + score + " stars");
                Review ratingAndComment = new Review(score, comment);
                item.addReview(ratingAndComment);
                System.out.println("Your item review was registered successfully."
                );

            } else {
                System.out.println("Score must be between 1 and 5");
            }
        } else {

        System.out.println("Item "+ id + " was not registered yet");

        }

    }


}