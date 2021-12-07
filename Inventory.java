// Inventory.java
// allows for storing some number of items for the player

import java.util.ArrayList;
import java.util.Scanner;

import ansi_terminal.*;

/**
 *
 *Inventory as a class is used to register the entity of the player.  
 *@author Professor Ian, Tommy Garloch, Simon Jones, and Nico Guzzone.
 */

public class Inventory {
    // the actual list of items
    public static ArrayList<Item> items;

    // which item is equipped, if any
    private Item equippedArmor;
    private Item equippedWeapon;

    // the max weight limit for the player here
    private int maxWeight;

/**
 *Constructor establishes how much weight the Inventory has a maximum. This is so players cannot just pick everything up in the map.
 *@param maxWeight passes in the maximum weight limit for the Inventory. 
 */

    public Inventory(int maxWeight) {
        items = new ArrayList<Item>();
        this.maxWeight = maxWeight;
    }

    /**
     *The add method makes it possible to add an Item object while determining if the character's inventory can carry it. 
     *@return returns a boolean determining if the item added will take the inventory above its max weight.
     *
     */

    // returns true on success, false when full
    public boolean add(Item item) {
        if ((item.getWeight() + totalWeight()) > maxWeight) {
            return false;
        } else {
            items.add(item);
            return true;
        }
    }

    // this method not only adds the item, but equips it into the correct slot
    // it is used for setting up the player's starting gear
    
    /**
     *addAndEquip will take in the item and put it in the slot it needs to go to in order to be used by the player. 
     *@param item The item that needs to be equipped.
     */
    public void addAndEquip(Item item) {
        items.add(item);

        if (item.getType() == ItemType.Weapon) {
            equippedWeapon = item;
        } else if (item.getType() == ItemType.Armor) {
            equippedArmor = item;
        }
    }

    // get the equipped weapon and armor
    
    /**
     *getEquippedWeapon gets the equipped weapon.
     *@return returns the equipted weapon.
     */

    public Item getEquippedWeapon() {
        return equippedWeapon;
    }


    /**
     *getEquippedWeapon gets the equipped armor.
     *@return returns the equipted armor.
     */
    public Item getEquippedArmor() {
        return equippedArmor;
    }

    // returns the total weight of all items stored
   
	/**
	 *totalWeight calculates the total weight the inventory has currently.
	 *@return returns "total" which is the variable that contains the integer of the weight.
	 */
    public int totalWeight() {
        int total = 0;
        for (Item i : items) {
            total += i.getWeight();
        }
        return total;
    }

    // print all of the items in the list, that match they given type (can be null)
    // returns the number of items matching they type
    
    /**
     *This print method prints items associated with the type passed in as parameter.
     *@param filer passes in an ItemType to narrow down items by their type. 
     *@return returns the number associated with the slot in the inventory of the item.
     */

    private int print(ItemType filter) {
        // clear the terminal so we print over all else
        Terminal.clear();

        // print a heading row
        // the numbers and junk are to make it print in nice columns
        Terminal.setForeground(Color.RED);
        System.out.printf("%-4s %-40s %-8s %-8s %-8s\n\r", "No.", "Name", "Weight", "Value", "Strength");
        Terminal.reset();

        // print each item out
        int num = 0;
        for (Item i : items) {
            if (filter == null || i.getType() == filter) {
                System.out.printf("%-4d %-40s %-8s %-8s %-8s", num + 1, i.getName(), i.getWeight(), i.getValue(), i.getStrength());

                // tell them if this thing is equipped
                if (i == equippedArmor) {
                    System.out.print(" (equipped armor)");
                } else if (i == equippedWeapon) {
                    System.out.print(" (equipped weapon)");
                }
                System.out.print("\n\r");

                num++;
            }
        }

        return num;
    }

    // stay here until the user is ready to go back
    /**
     *pressAnyKey is a method that informs the user that if they press a key, they can return to game.
     */
    public void pressAnyKey() {
        System.out.printf("\n\rPress any key to return...\n\r");
        Terminal.getKey();
    }

    // print all of the items in the list
    
 	/**
	 *this print method prints all of the items in the list.
	 */
    public void print() {
        print(null);
        pressAnyKey();
    }

    // drop an item from the inventory, return what was dropped

    /**
     *The method "drop" will drop an item that the player picks. 
     *@return this method returns a toDrop item if one is properly selected.
     */

    public Item drop() {
        Item toDrop = pickItem(null);
        if (toDrop != null) {
            // if we're dropping our equipped stuff, remove those too!
            if (equippedWeapon == toDrop) {
                equippedWeapon = null;
            } else if (equippedArmor == toDrop) {
                equippedArmor = null;
            }

            items.remove(toDrop);
        }

        if (toDrop != null) {
            System.out.print("You dropped the " + toDrop.getName() + "...\n\r");
        } else {
            System.out.print("You dropped nothing...\n\r");
        }

        pressAnyKey();
        return toDrop;
    }

    // equip something
    /**
     *The equip method equipts an item and displays this process to the player.
     *@param type passes in the type of the item.
     * @return returns the "thing" which was equipped.
     */

    private Item equip(ItemType type) {
        Item thing = pickItem(type);
        if (thing != null) {
            System.out.print("You equipped the " + thing.getName() + "\n\r");
        } else {
            System.out.print("You equipped nothing...\n\r");
        }
        pressAnyKey();
        return thing;
    }

    // equip a weapon
    /**
     *equipWeapon specifically equips a weapon.
     */
    public void equipWeapon() {
        equippedWeapon = equip(ItemType.Weapon);
    }

    // equip a piece of armor
     /**
     *equipWeapon specifically equips a weapon.
     */
    public void equipArmor() {
        equippedArmor = equip(ItemType.Armor);
    }



    // a method which allows users to choose an item
    // this is private - only called by drop and equip

    /**
     *pickItem constructs the main way for a player to choose what Item they wish to select for an action.
     *@return it returns the item index of the item that needs to be removed. 
     */
    private Item pickItem(ItemType filter) {
        // print all the matching items
        int options = print(filter);

        if (options == 0) {
            System.out.print("You have no appropriate items!\n\r");
            return null;
        }

        // give them a cancel option as well
        System.out.print((options + 1) + "    None\n\r");

        // get their choice, only allowing ints in the correct range
        int choice = 0;
        do {
            String entry = Terminal.getLine("Select an item: ");
            try {
                choice = Integer.parseInt(entry) - 1;
            } catch (NumberFormatException e) {
                choice = -1;
            }
        } while (choice < 0 || choice > options);

        // go through and skip items until we reach this one
        int realIndex = 0;
        for (Item i : items) {
            if (filter == null || i.getType() == filter) {
                if (choice == 0) {
                    break;
                }
                choice--;
            }
            realIndex++;
        }

        // return the thing they chose
        if (realIndex < 0 || realIndex >= items.size()) {
            return null;
        } else {
            return items.get(realIndex);
        }
    }
}

