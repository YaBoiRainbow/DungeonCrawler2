// Player.java
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import ansi_terminal.*;

/**
 *The class Player handles all the information regarding the user's character in game.
 *This class extends Character
 *@author Professor Ian, Tommy Garloch, Simon Jones, and Nico Guzzone.
 */

public class Player extends Character {
    private Inventory items;
    public static String name;
    public Player(Position start) {
        // our starting details
        super(start.getRow(), start.getCol(), '@', Color.CYAN, 50);

        // we can carry 100 pounds of items
        items = new Inventory(100);

        // give them some basic stuff to start with
        // TODO make up your own starting equipment!
        items.addAndEquip(new Item(ItemType.Weapon, "Iron Sword", 8, 13, 5));
        items.addAndEquip(new Item(ItemType.Armor, "Iron Armor", 19, 32, 1));
    }

    /**
     *gets the damage value based on player's equipped weapon.
     *@return returns damage value of 1 if we have no weapon or the strength of weapon value if we do have a weapon equipped. 
     */
    @Override
    public int getDamage() {
        Item weapon = items.getEquippedWeapon();
        if (weapon != null) {
            return weapon.getStrength();
        } else {
            // if we have no weapon, our fists are pretty weak...
            return 1;
        }
    }

    /**
     *gets the name of the character that he player input.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     *gets the armor value attributed to the player. 
     *@return returns armor strength of equipped armor and if there is no armor then it returns 0. 
     */
    @Override
    public int getProtection() {
        Item armor = items.getEquippedArmor();
        if (armor != null) {
            return armor.getStrength();
        } else {
            // without armor, we have no protection
            return 0;
        }
    }

    /**
     *gets the inventory that the player is attatched to as well as processing player's dialogue and name before game starts.
     */
    public Inventory getInventory() {
        return items;
    }public void getInfo()throws InterruptedException{
                Terminal.cookedMode();
                Scanner in = new Scanner(System.in);
		String choice;
                System.out.println("Who... Who are you...");
                this.name = in.nextLine();
                System.out.println(this.name + "... I see... And what is it that you are here for...");
                System.out.println("(Enter the number corrisponding to your answer)\n1: For Glory!\n2: To destroy you!\n3: I dont know, who even are you?");
                choice = in.nextLine();
                switch(choice){
                        case "1":
                                System.out.println("As you wish...");
                                Thread.sleep(3000);
                                break;
                        case "2":
                                System.out.println("... You will try...");
                                Thread.sleep(3000);
                                break;
                        case "3":
                                System.out.println("I... I am your worst nightmare...");
                                Thread.sleep(3000);
                                break;
                        default:
                                System.out.println("What? what did you just say? Because it was definitly not 1, 2 or 3... anyways, you will die now.");
                                Thread.sleep(5000);
                                break;
                }Terminal.rawMode();
    }	    
}

