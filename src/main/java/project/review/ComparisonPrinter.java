package project.review;

import project.Model.Item;
import project.Model.Model;
import project.Model.Review;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ComparisonPrinter {
    public String everyReview(Model model) {
        //Prints all reviews for all items
        boolean isAllEmpty = true;
        Collection<Item> itemList = model.getItemList();
        StringBuilder sb = new StringBuilder();

        if (itemList.isEmpty()) {
            sb.append("No items registered yet.");
            return sb.toString();
        }
        for (Item items : itemList) {
            ArrayList<Review> theList = items.getRatingAndComment();
            if (theList.isEmpty() && isAllEmpty == true) {
                isAllEmpty = true;
            } else {
                isAllEmpty = false;
                break;
            }
        }
        if (isAllEmpty == false) {
            for (Item items : itemList) {

                String name = items.getName();
                String uuid = items.getID().toString();
                double price = items.getPrice();
                ArrayList<Review> theList = items.getRatingAndComment();

                isAllEmpty = false;
                sb.append("------------------------------------------------------------");
                sb.append("Reviews for: " + uuid + "    " + name + "    " + price);

                for (Review ratingAndComment : theList) {
                    int rating = ratingAndComment.getRating();
                    String comment = ratingAndComment.getComment();
                    sb.append("Grade: " + rating + "  " + comment);

                }
            }
        } else {
            sb.append("No items were reviewed yet.");
        }
        return sb.toString();
    }

    public List getMostReviews (Model model) {
        Collection<Item> itemList = model.getItemList();
        List<Item> mostReviewed = new ArrayList<>();
        if (itemList.isEmpty()) {
            System.out.println("No items registered yet.");
        }

        for (Item item : itemList) {
            List<Review> list = item.getRatingAndComment();

            if (mostReviewed.isEmpty()) {
                mostReviewed.add(item);
            } else if (mostReviewed.get(0).getNumOfComments() == item.getNumOfComments()) {
                mostReviewed.add(item);
            } else if (mostReviewed.get(0).getNumOfComments() < item.getNumOfComments()) {
                mostReviewed.clear();
                mostReviewed.add(item);
            }
            return list;
        }
        return mostReviewed;
    }

    public String mostReviews(Model model) {
        StringBuilder sb = new StringBuilder();

        Collection<Item> itemList = model.getItemList();
        ArrayList<Item> mostReviewedItemS = new ArrayList<>();

        if (itemList.isEmpty()) {
            sb.append("No items registered yet.");
            return sb.toString();
        }

        for (Item items : itemList) {
            ArrayList<Review> theList = items.getRatingAndComment();

            if (mostReviewedItemS.isEmpty()) {
                mostReviewedItemS.add(items);
            } else if (mostReviewedItemS.get(0).getNumOfComments() == items.getNumOfComments()) {

                mostReviewedItemS.add(items);
            } else if (mostReviewedItemS.get(0).getNumOfComments() < items.getNumOfComments()) {
                mostReviewedItemS.clear();
                mostReviewedItemS.add(items);
            }
        }
        if (mostReviewedItemS.get(0).getNumOfComments() == 0) {
            sb.append("No items were reviewed yet.");
        } else {
            sb.append("------------------------------------------------------------");
            System.out.printf("Most reviews: %d review(s) each.%n", mostReviewedItemS.get(0).getNumOfComments());
            for (Item items : mostReviewedItemS) {
                System.out.printf("<%s>: <%s>. <%f> SEK %n", items.getID().toString(), items.getName(), items.getPrice());

            }
        }
        return  sb.toString();
    }

    public List getLeastReviewed(Model model) {
        Collection<Item> itemList = model.getItemList();
        List<Item> mostReviewed = new ArrayList<>();

        if (itemList.isEmpty()) {
            System.out.println("No items registered yet.");
        }

        for (Item item : itemList) {
            List<Review> list = item.getRatingAndComment();
            if (mostReviewed.isEmpty()) {
                mostReviewed.add(item);
            } else if (mostReviewed.get(0).getNumOfComments() == item.getNumOfComments()) {
                mostReviewed.add(item);
            } else if (mostReviewed.get(0).getNumOfComments() > item.getNumOfComments()) {
                mostReviewed.clear();
                mostReviewed.add(item);
            }
            return list;
        }
        return mostReviewed;
    }

    public String leastReviews(Model model)  {

        Collection<Item> itemList = model.getItemList();
        ArrayList<Item> mostReviewedItemS = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        if (itemList.isEmpty()) {
            sb.append("No items registered yet.");
            return sb.toString();
        }

        for (Item items : itemList) {

            ArrayList<Review> theList = items.getRatingAndComment();
            if (mostReviewedItemS.isEmpty()) {
                mostReviewedItemS.add(items);
            } else if (mostReviewedItemS.get(0).getNumOfComments() == items.getNumOfComments()) {

                mostReviewedItemS.add(items);
            } else if (mostReviewedItemS.get(0).getNumOfComments() > items.getNumOfComments()) {
                mostReviewedItemS.clear();
                mostReviewedItemS.add(items);
            }
        }
        if (mostReviewedItemS.get(0).getNumOfComments() == 0) {
            sb.append("No items were reviewed yet.");
        } else {
            sb.append("------------------------------------------------------------");
            sb.append("Least reviews: ");
            sb.append(mostReviewedItemS.get(0).getNumOfComments());
            sb.append(" review(s) each.");
            for (Item items : mostReviewedItemS) {
                sb.append(items.getID());
                sb.append(items.getName());
                sb.append(items.getPrice());
            }
        }
        return sb.toString();
    }

    public List bestReviewed(Model model) {
        Collection<Item> itemList = model.getItemList();
        List<Item> highestMeanGrade = new ArrayList<>();

        if (itemList.isEmpty()) {
            System.out.println("No items registered yet.");
        }

        for (Item item : itemList) {

            List<Review> list = item.getRatingAndComment();
            double mean = item.getMeanRating();

            if (list.isEmpty()) {
                System.out.println("No items were reviewed yet");
            }

            if (mean == -1){
                System.out.println("No items were reviewed yet");
            }

            if (highestMeanGrade.isEmpty()) {
                //System.out.println("No items were reviewed yet");
                highestMeanGrade.add(item);
            } else if (highestMeanGrade.get(0).getMeanRating() == mean) {
                highestMeanGrade.add(item);
            } else if (highestMeanGrade.get(0).getMeanRating() < item.getMeanRating()) {
                highestMeanGrade.clear();
                highestMeanGrade.add(item);
            }
            return list;
        }
        return highestMeanGrade;
    }

    public String bestRating(Model model){

        Collection<Item> itemList = model.getItemList();
        ArrayList<Item> highestMeanGrade = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        if (itemList.isEmpty()) {
            sb.append("No items registered yet.");
            return sb.toString();
        }

        for (Item items : itemList) {

            ArrayList<Review> list = items.getRatingAndComment();
            double mean = items.getMeanRating();

            if (list.isEmpty()) {
                sb.append("No items were reviewed yet.");
                return sb.toString();
            }

            if (mean == -1){
                sb.append("No items were reviewed yet.");
                return sb.toString();
            }

            if (highestMeanGrade.isEmpty()) {
                highestMeanGrade.add(items);
            } else if (highestMeanGrade.get(0).getMeanRating() == mean) {

                highestMeanGrade.add(items);
            } else if (highestMeanGrade.get(0).getMeanRating() < items.getMeanRating()) {
                highestMeanGrade.clear();
                highestMeanGrade.add(items);
            }
        }
        sb.append("Items with best mean reviews:");
        sb.append(highestMeanGrade.get(0).getMeanRating());
            //System.out.printf("Grade: <%d> %n", highestMeanGrade.get(0).getMeanRating());
            for (Item items : highestMeanGrade) {
                sb.append(items.toString());
               // System.out.printf("<%s>: <%s>. <%f> SEK %n", items.getID().toString(), items.getName(), items.getPrice());

        }
            return sb.toString();
    }

    public List getWorstReviewed(Model model) {
        Collection<Item> itemList = model.getItemList();
        List<Item> highestMeanGrade = new ArrayList<>();

        if (itemList.isEmpty()) {
            System.out.println("No items registered yet.");
        }

        for (Item item : itemList) {

            List<Review> list = item.getRatingAndComment();
            double mean = item.getMeanRating();

            if (list.isEmpty()) {
                System.out.println("No items were reviewed yet.");
            }

            if (mean == -1 ) {                                         //since -1 is impossible it will be assigned when there are no ratings in the getMeanRating method
                System.out.println("No items were reviewed yet");
            }

            if (highestMeanGrade.isEmpty()) {
                highestMeanGrade.add(item);
            } else if (highestMeanGrade.get(0).getMeanRating() == mean) {

                highestMeanGrade.add(item);
            } else if (highestMeanGrade.get(0).getMeanRating() > item.getMeanRating()) {
                highestMeanGrade.clear();
                highestMeanGrade.add(item);
            }
            return list;
        }
        return highestMeanGrade;
    }

    public String worstRating(Model model){

        Collection<Item> itemList = model.getItemList();
        ArrayList<Item> highestMeanGrade = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        if (itemList.isEmpty()) {
            sb.append("No items registered yet.");
            return sb.toString();
        }

        for (Item items : itemList) {

            ArrayList<Review> list = items.getRatingAndComment();
            double mean = items.getMeanRating();

            if (list.isEmpty()) {
                sb.append("No items were reviewed yet.");
                return sb.toString();
            }

            if (mean == -1){                                         //since -1 is impossible it will be assigned when there are no ratings in the getMeanRating method
                sb.append("No items were reviewed yet");
                return sb.toString();
            }

            if (highestMeanGrade.isEmpty()) {
                highestMeanGrade.add(items);
            } else if (highestMeanGrade.get(0).getMeanRating() == mean) {

                highestMeanGrade.add(items);
            } else if (highestMeanGrade.get(0).getMeanRating() > items.getMeanRating()) {
                highestMeanGrade.clear();
                highestMeanGrade.add(items);
            }
        }
        sb.append("Items with worst mean reviews:");
        sb.append(highestMeanGrade.get(0).getMeanRating());
        //System.out.printf("Grade: <%d> %n", highestMeanGrade.get(0).getMeanRating());
        for (Item items : highestMeanGrade) {
            sb.append(items.toString());
            //System.out.printf("<%s>: <%s>. <%f> SEK %n", items.getID().toString(), items.getName(), items.getPrice());
        }
        return sb.toString();
    }

}
