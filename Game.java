// Game.java
// contains logic for running the Game
import java.util.ArrayList;
import ansi_terminal.*;

/**
 *
 *Game is a class that handles parts of the display for the game like the instructions as well as handling transitions between floors. 
 *@author Professor Ian, Tommy Garloch, Simon Jones, and Nico Guzzone.
 */

public class Game{
    private String name;
    public static Room room;
    public Player player;
    public static ArrayList<Box> boxes;
    public static ArrayList<Enemy> enemies;
    public Game(){ 	
        room = new Room();
	player = new Player(room.getPlayerStart());
        boxes = room.getBoxes();
        enemies = room.getEnemies();

    }

    // prints a help menu to the left of the map
    /**
     *displays controls
     */
    private void showHelp() {
        String[] cmds = {"Commands:",
                         "---------",
                         "Move: Arrow Keys",
                         "Pickup an item: p",
                         "Drop an item: d",
                         "List items: i",
                         "Equip weapon: w",
                         "Equip armor: a",
			 "Go down stairs: j",
			 "Go up stairs: k",
			 "Save: s",
			 "Load save: l",
			 "Clear save: c",
                         "Quit: q"
        };
        Terminal.setForeground(Color.GREEN);
        for (int row = 0; row < cmds.length; row++) {
            Terminal.warpCursor(row + 1, room.getCols());
            System.out.print(cmds[row]);
        }
        Terminal.reset();
    }

    // right under the map we keep a line for status messages
    /**
     *reserves a spot on console to display status messages.
     *@param mesg string variable for status message to user.
     */
    private void setStatus(String mesg) {
        // clear anything old first
        Terminal.warpCursor(room.getRows(), 0);
        for (int i = 0; i < 100; i++) {
            System.out.print(" ");
        }

        // then print the message
        Terminal.warpCursor(room.getRows(), 0);
        System.out.print(mesg);
    }

    // code for when the player tries to pickup an item
    /**
     *processes scenarios regarding a player picking up an item
     */
    private void pickup() {
        Box thing = checkForBox();
        if (thing == null) {
            setStatus("There is nothing here to pick up...");
            Terminal.pause(1.25);
        } else {
            if (player.getInventory().add(thing.getItem())) {
                setStatus("You added the " + thing.getItem().getName() + " to your inventory.");
                boxes.remove(thing);
            } else {
                setStatus("This is too large for you to add!");
            }
            Terminal.pause(1.25);
        }
    }

    // code for when the player tries to drop an item
    /**
     *Drops the item of a player's choosing and places a box on his position containing dropped item and redraws map to display it.
     */
    private void drop() {
        if (checkForBox() == null) {
            Item dropped = player.getInventory().drop();
            if (dropped != null) {
                boxes.add(new Box(player.getRow(), player.getCol(), dropped));
            }
            redrawMapAndHelp();
        } else {
            setStatus("You cannot drop something on an existing item...");
            Terminal.pause(1.25);
        }
    }

    // handle the key which was read - return false if we quit the game
    /**
     *Decides what to do with what keys the user presses based off of the controls.
     *@param key stores the key the player pressed.
     *@return returns a boolean that either quits the game or keeps the game going. 
     */
    private boolean handleKey(Key key) {
        switch (key) {
	    case j:
		goDown();
		break;

	    case k:
		goUp();
		break;

	    case p:
                pickup();
                break;

            case i:
                player.getInventory().print();
                redrawMapAndHelp();
                break;

            case d:
                drop();
                break;

            case w:
                player.getInventory().equipWeapon();
                redrawMapAndHelp();
                break;

            case a:
                player.getInventory().equipArmor();
                redrawMapAndHelp();
                break;

            // handle movement
            case LEFT: player.move(0, -1, room);
                break;
            case RIGHT: player.move(0, 1, room);
                break;
            case UP: player.move(-1, 0, room);
                break;
            case DOWN: player.move(1, 0, room);
                break;

	    case s: Save.save(player, Save.saved);
		    break;

	    case l: Save.load(player);
		    break;
	    case c: Save.clear();
		   break;
            // and finally the quit command
            case q:
                return false;
        }

        return true;
    }

    // this is called when we need to redraw the room and help menu
    // this happens after going into a menu like for choosing items
    /**
     *redraws the map and controls on console.
     */
    private void redrawMapAndHelp() {
        room.draw();
        showHelp();
    }

    // returns a Box if the player is on it -- otherwise null
    /**
     *checks if there is a box the player is on top of so that the player can see what is in it and collect it.
     *@return returns null.
     */
    private Box checkForBox() {
        Position playerLocation = player.getPosition();

        for (Box box : boxes) {
            if (playerLocation.equals(box.getPosition())) {
                return box;
            }
        }

        return null;
    }

    // check for battles and return false if player has died
    /**
     * checks if the player has died or not. If the player has not died in battle then it processes what happened to player and enemies.
     * @return returns fight data if player is alive. Otherwise it returns true if they are dead.
     */
    private boolean checkBattles() {
        Position playerLocation = player.getPosition();

        // look for an enemy that is close
        Enemy opponent = null;
        for (Enemy enemy : enemies) {
            if (playerLocation.isAdjacent(enemy.getPosition())) {
                opponent = enemy;
            }
        }

        // now do the battle
        if (opponent != null) {
            opponent.setBattleActive();
            return player.fight(opponent, room, enemies);
        }

        return true;
    }
	/**
	 *Allows the player to go down to a lower floor and informs them they cannot go if there is no way to go down.
	 */
    private void goDown(){
	int level = room.getLevel();
	Position playerLocation = player.getPosition();
	if(level == 4){
	    setStatus("There is no where for you to go down.");
            Terminal.pause(1.25);
	    return;
        }
	else if(playerLocation.isAdjacent(room.goDown())){
	    level++;
	    room.changeLevel(level);
	    this.boxes = room.getBoxes();
            this.enemies = room.getEnemies();
	    redrawMapAndHelp();
	    setStatus("You went down a level");
            Terminal.pause(1.25);
	}else{
	    setStatus("There is no where for you to go down.");
	    Terminal.pause(1.25);
	}

	 /**
         *Allows the player to go up to a higher floor and informs them they cannot go if there is no way to go up.
         */
    }private void goUp(){
        int level = room.getLevel();
        Position playerLocation = player.getPosition();
	if(level == 1){
	    setStatus("There is no where for you to go up.");
            Terminal.pause(1.25);
	    return;
        }
	else if(playerLocation.isAdjacent(room.goUp())){
	    level--;
            room.changeLevel(level);
            this.boxes = room.getBoxes();
            this.enemies = room.getEnemies();
            redrawMapAndHelp();
            setStatus("You went up a level");
            Terminal.pause(1.25);
	}else{
            setStatus("There is no where for you to go up.");
            Terminal.pause(1.25);
	    }

	}
    /**
     *applies the draw map and help method properly.
     */
    public void run() {
        // draw these for the first time now
        redrawMapAndHelp();

        boolean playing = true;
        while (playing) {
            // draw the entities
            for (Box box : boxes) {
                box.draw();
            }
            for (Enemy enemy : enemies) {
                enemy.draw();
            }
            player.draw();

            // read a key from the user
            Terminal.warpCursor(room.getRows() + 1, 0);
            Key key = Terminal.getKey();
            playing = handleKey(key);

            // clear status by default
            setStatus("");

            // move the enemies
            for (Enemy enemy : enemies) {
                enemy.walk(room);
            }

            // check for battles
            if (checkBattles() == false) {
                setStatus("You have been killed :(\n\r");
                playing = false;
            }

            // check if we are on a box and print what's in it
            Box thingHere = checkForBox();
            if (thingHere != null) {
                setStatus("Here you find: " + thingHere.getItem().getName());
	    }
	    }
    }
}

