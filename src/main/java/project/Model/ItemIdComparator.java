package project.Model;

import java.util.Comparator;

public class ItemIdComparator implements Comparator<Item> {
    @Override
    public int compare(Item left, Item right) {
        return (left.getID().compareTo(right.getID()));
    }

}
