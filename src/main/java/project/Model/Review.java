package project.Model;

public class Review {

    private final int mRating;
    private final String mComment;

    public Review(int rating, String comment){

        mRating = rating;
        mComment = comment;
    }

    public int getRating() {
        return mRating;
    }

    public String getComment() {
        return mComment;
    }

    public int get(int index) {
        return index;
    }
}
