// Box.java
// represents a pickup-able item

import ansi_terminal.*;

public class Box extends Entity {
    // the Item that is in the box
    private Item item;

    // add a box with a given item in it
    public Box(int row, int col, Item item) {
        super(row, col, 'i', Color.MAGENTA);
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
    //Converts a box to a string, in order to save it in a save.txt file.
    public String boxToString(Box b){
	String itemS = this.item.toString();
	return b.getRow() + "." + b.getCol() + "." + itemS;
	    
    }
}


