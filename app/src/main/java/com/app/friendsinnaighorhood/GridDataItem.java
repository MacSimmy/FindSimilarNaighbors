package com.app.friendsinnaighorhood;

/**
 * Created by apps on 31/5/16.
 */
public class GridDataItem {

    public GridDataItem(int item, boolean shouldHighlight){
        this.item = item;
        this.shouldHighlight =shouldHighlight;
    }

    private int item;
    private boolean shouldHighlight = false;

    public int getItem() {
        return item;
    }

    public GridDataItem setItem(int item) {
        this.item = item;
        return this;
    }

    public boolean isShouldHighlight() {
        return shouldHighlight;
    }

    public GridDataItem setShouldHighlight(boolean shouldHighlight) {
        this.shouldHighlight = shouldHighlight;
        return this;
    }

}
