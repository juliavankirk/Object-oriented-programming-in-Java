package project.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Item {
    private final String mID;
    private String mName;
    private Double mPrice;
    private final ArrayList<Review> mRatingAndComment;
    private final ArrayList<Integer> mRatings;


    public Item(String ID, String name, double price) {
        mID = ID;
        mName = name;
        mPrice = price;
        mRatings = new ArrayList<Integer>();
        mRatingAndComment = new ArrayList<>();
    }

    public String getID() {
        return mID;
    }

    public String getName() { return mName; }
    public void setName(String name) { mName = name; }

    public double getPrice() { return mPrice; }
    public void setPrice(double price) { mPrice = price; }

    public Transaction buyItem(int quantity) {
        double total = 0.00;
        double discount = 0.7;

        if (quantity > 4) {
            //30% discount for every item past 4
            total = this.getPrice() * 4;
            total += (this.getPrice() * (quantity - 4)) * discount;
        } else {
            total = this.getPrice() * quantity;
        }
        total = Math.floor(total * 100) / 100;

        return new Transaction(this.getID(), quantity, total);
    }

    public ArrayList<Review> getRatingAndComment() {
        return mRatingAndComment;
    }


    public Review getReview(int index) {
        Review element = mRatingAndComment.get(index);
        return element;
    }

    public void addReview(Review ratingAndComment) {
        mRatingAndComment.add(ratingAndComment);
    }

    public int getNumOfComments() {
        int numOfComments = 0;
        for (Review ratingAndComment : mRatingAndComment) {
            String comment = ratingAndComment.getComment();
            if (comment != null) {
                numOfComments = numOfComments + 1;
            }
        }
        return numOfComments;
    }

    public double getMeanRating() {
        double numberOfRatings = 0.0;
        double sumOfRatings = 0.0;
        for (Review ratingAndComment : mRatingAndComment) {
            int rating = ratingAndComment.getRating();
            if (rating != 0) {
                numberOfRatings = numberOfRatings + 1;
                sumOfRatings = sumOfRatings + rating;
            }
        }
        if (numberOfRatings == 0){
            return 0;
        }
        double mean = sumOfRatings / numberOfRatings;
        return mean;
    }

    @Override

    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");

        String retVal = (getID() + ": ");
        retVal += (getName() + ". ");
        retVal += (df.format(getPrice()) + " SEK");

        return retVal;
    }
}
