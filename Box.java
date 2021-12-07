// Box.java
// represents a pickup-able item


import ansi_terminal.*;

/**
 *
 *Box as a class represents the data associated with an item storage unit in the game.
 *This class extends {@link Entity}
 *@author Professor Ian, Tommy Garloch, Simon Jones, and Nico Guzzone.
 */

public class Box extends Entity {
    // the Item that is in the box
    private Item item;

    /**
     *Constructor that deals with parameters of coordinates and the Item to be stored. 
     *@param item sets what the item stored in this coordinate awaiting puckup from player will be
     *@param row sets the row on the grid that the item is stored on.
     *@param col sets the colomn on the grid that the item is stored on.
     */
    // add a box with a given item in it
    public Box(int row, int col, Item item) {
        super(row, col, 'i', Color.MAGENTA);
        this.item = item;
    }

    /**
     *getItem retunrs an item.
     *This is useful mainly for adding things to the player's inventory.
     *@return returns an item.  
     *
     */

    public Item getItem() {
        return item;
    }

    //Converts a box to a string, in order to save it in a save.txt file.
    /**
     *boxToString converts a box on the map to a string so it can be saved on a .txt file.
     *@param b a box.
     */
    public String boxToString(Box b){
	String itemS = this.item.toString();
	return b.getRow() + "." + b.getCol() + "." + itemS;
	    
    }
}


