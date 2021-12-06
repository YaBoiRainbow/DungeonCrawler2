// Room.java
// provides code for the drawing of a room
// also provides starting locations for the player, boxes, and enemies

import java.util.ArrayList;
import ansi_terminal.*;

public class Room {
    // the grid holds the room geometry
    private String[] grid;
    private String[] grid1;
    private String[] grid2;
    private String[] grid3;
    // the size of the room
    private int rows;
    private int cols;
    public int level = 1;
    public Room() {
        // this initializes the room to one specific space
        rows = 30;
        cols = 60;
        // the actual room geometry
        // the i cells refer to where an item should be placed at
	 grid1 = new String[]{
            "##################                ######################    ",
            "##              ##                ##      i           ##    ",
            "##  @           ###########       ##        *         ##    ",
            "##                       ##       ##                  ##    ",
            "##              #######  ##       ##################  ##    ",
            "##              ##   ##  ##                       ##  ##    ",
            "##################   ##  ##################       ##  ##    ",
            "                     ##                  ##       ##  ##    ",
            "                     ##   *  i           ##       ##  ##    ",
            "                     ##                  ##       ##  ##    ",
            "                     ##############  ######       ##  ##    ",
            "                                 ##  ##           ##  ##    ",
            "                                 ##  ##           ##  ##    ",
            "                       ############  ###############  ######",
            "                       ##                                 ##",
            "                       ##                                 ##",
            "    #####################                  *              ##",
            "    ##                                                    ##",
            "    ##  #################                                 ##",
            "    ##  ##             ##                                 ##",
            "    ##  ##             #################  ##################",
            "    ##  ##                            ##  ##                ",
            "    ##  ##                            ##  ##                ",
            "    ##  ##                       #######  #######           ",
            "    ##  ##                       ##            ##           ",
            "######  ####                     ##  i  *      ##           ",
            "##        ##                     ##     D      ##           ",
            "## i  *   ##                     ##            ##           ",
            "##        ##                     ################           ",
            "############                                                "
        };
	grid2 = new String[]{
	    "##################                ######################### ",
            "##              ##                ##                     ## ",
            "##              ####################                i    ## ", 
            "##      D                                                ## ",
            "##              ####################               *     ## ",
            "##              ##                ##                     ## ",
            "##################                ##     i               ## ",
            " ###################################                     ## ",
            " ##                     *                                ## ",
            " ##    #################################################### ",
            " ##    ##                                                   ",
            " ##    ##                                                   ",
            " ##    #################################   #############    ",
            " ##                                   ##   ##         ##    ",
            " ##                                   #######   i     ##    ",
            " ##             i                                     ##    ",
            " ##             *                                *    ##    ",
            " ##                                  ########         ##    ",
            " ##                                  ##    ##    i    ##    ",
	    " #########################     ########    ##         ##    ",
            "                        ##     ##          #############    ",
            "                        ##     ##                           ",
            "                        ##     ##                           ",
            "                        ##     ##                           ",
            "                 #########     ###############              ",
            "                 ##                         ##              ",
            "                 ##  i                  U   ##              ",
            "                 ##                         ##              ",
            "                 ##                         ##              ",
            "                 #############################              "
	};
    	grid3 = new String[]{
            "   ###############                                          ",
            "   ##           ##                                          ",
            "   ##           ##                  ##########              ",
            "   ##   U  *    ##                  ##      ##              ",
            "   ##           ##                  ##      ##              ",
            "   ##           ##                  ##  D   ##              ",
            "   ######  #######                  ##      ##              ",
            "       ##  ##                 ########      #########       ",
            "       ##  ##                 ##                   ##       ",
            "       ##  ##                 ##    *              ##       ",
            "       ##  ##                 ##                   ##       ",
            "       ##  #####################     *     *       ##       ",
            "       ##                                          ##       ",
            "       ##                          *           *   ##       ",
            "       ########################                    ##       ",
            "                             ##                    ##       ",
            "                             ##           *        ##       ",
            "                             ##                    ##       ",
            "                             ##                    ##       ",
            "                             ########################       ",
            "                                                            ",
            "                                                            ",
            "                                                            ",
            "                                                            ",
            "                                                            ",
            "                                                            ",
            "                                                            ",
            "                                                            ",
            "                                                            ",
            "                                                            "
    };
    	grid = grid1;
    }
    // returns the player's strting location in this room
	public Position getPlayerStart() {
	        for (int row = 0; row < rows; row++) {
        	    for (int col = 0; col < cols; col++) {
                	if (grid[row].charAt(col) == '@') {
                   	 return new Position(row, col);
                }
            }
        }

        return null;
    }
    // returns a set of item boxes for this map, this is here because it depends on
    // the room geometry for where the boxes make sense to be
    public ArrayList<Box> getBoxes() {
        ArrayList<Box> boxes = new ArrayList<Box>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == 'i') {
                    boxes.add(new Box(row, col, ItemGenerator.generate()));
                }
            }
        }

        return boxes;
    }

    // returns a set of enemies from this map, similarly to the boxes above
    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '*') {
                    enemies.add(EnemyGenerator.generate(row, col));
                }
            }
        }

        return enemies;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // draws the map to the screen
    public void draw() {
        Terminal.clear();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char cell = grid[row].charAt(col);
                if (cell == '#') {
                    // a unicode block symbol
                    System.out.print('\u2588');
                }else if(cell == 'U') {
			System.out.print('U');
		}else if(cell == 'D') {
                        System.out.print('D');
		}else {
                    // whatever else, just draw a blank (we DONT draw starting items from map)
                    System.out.print(' ');
                }
            }

            System.out.print("\n\r");
        }
    }public Position goUp(){
	    for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (grid[row].charAt(col) == 'U'){
    			return new Position(row, col);
		}
	    }
	}return null;
    }public Position goDown(){
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == 'D'){
                        return new Position(row, col);
                }
            }
        }return null;
    }public void changeLevel(int newLevel){
	this.level = newLevel;
	if(this.level == 1){
		this.grid = this.grid1;
		Terminal.clear();
	}else if(this.level == 2){
		grid = grid2;
		Terminal.clear();
	}else if(this.level == 3){
		grid = grid3;
		Terminal.clear();
	}else{
       		Terminal.clear();
		Terminal.cookedMode();
		Terminal.clear();
		System.out.println("You found the exit! Thanks for playing!");
                Terminal.pause(3);
            	System.exit(0); 	

	}
    }public int getLevel(){
	return this.level;
    }
    // returns if a given cell in the map is walkable or not
    public boolean canGo(int row, int col) {
        if(grid[row].charAt(col) != '#'){
		if(grid[row].charAt(col) != 'U'){
			if(grid[row].charAt(col) != 'D'){
	    return true;
			}
		}
	}
   return false;
    }
}


