package project.Model;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Transaction {
    private final String mItemId;
    private final int mQuantity;
    private final double mTotal;

    public Transaction(String itemId, int quantity, double total) {
        mItemId = itemId;
        mQuantity = quantity;
        mTotal = total;
    }

    public String getItemId() {
        return mItemId;
    }

    public Integer getQuantity() { return mQuantity; }

    public double getTotal() {
        return mTotal;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);

        String retVal = (getItemId() + ": ");
        retVal += (getQuantity() + " item(s). ");
        retVal += (df.format(getTotal()) + " SEK");

        return retVal;
    }
}
