// Player.java
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import ansi_terminal.*;

public class Player extends Character {
    private Inventory items;
    private String name;
    public Player(Position start) {
        // our starting details
        super(start.getRow(), start.getCol(), '@', Color.CYAN, 50);

        // we can carry 100 pounds of items
        items = new Inventory(100);

        // give them some basic stuff to start with
        // TODO make up your own starting equipment!
        items.addAndEquip(new Item(ItemType.Weapon, "Iron Dagger", 5, 12, 7));
        items.addAndEquip(new Item(ItemType.Armor, "Leather Armor", 15, 20, 3));
    }

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

    @Override
    public String getName() {
        return this.name;
    }

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

    public Inventory getInventory() {
        return items;
    }public void getInfo()throws InterruptedException{
                Terminal.cookedMode();
                int choice;
                Scanner in = new Scanner(System.in);
                System.out.println("Who... Who are you...");
                this.name = in.nextLine();
                System.out.println(this.name + "... I see... And what is it that you are here for...");
                System.out.println("(Enter the number corrisponding to your answer)\n1: For Glory!\n2: To destroy you!\n3: I dont know, who even are you?");
                choice = in.nextInt();
                switch(choice){
                        case 1:
                                System.out.println("As you wish...");
                                Thread.sleep(3000);
                                break;
                        case 2:
                                System.out.println("... You will try...");
                                Thread.sleep(3000);
                                break;
                        case 3:
                                System.out.println("I... I am your wort nightmare...");
                                Thread.sleep(3000);
                                break;
                        default:
                                System.out.println("What? what did you just say? Because it was definitly not 1, 2 or 3... anyways, you will die now.");
                                Thread.sleep(5000);
                                break;
                }Terminal.rawMode();
        }	
}

