// Entity.java
// this class represents one moveable, drawable thing in the game

import ansi_terminal.*;


/**
 *
 *Entity as a class is used by other classes to handle the representation of drawing characters and 
 *on the map. 
 *@author Professor Ian, Tommy Garloch, Simon Jones, and Nico Guzzone.
 */

public class Entity {
    // the location of the entity in space
    private Position position;

    // the character used to draw it
    private char display;

    // the color used for drawing
    private Color color;

    /**
     *This Constructor sets up an entity's color, location, and display icon.
     *@param row passes in row where entity is located
     *@param col passes in column where entity is located
     *@param display passes in the character that is needed to display the entity properly
     *@param color passes in the color the entity will be displayed with. 
     */
    public Entity(int row, int col, char display, Color color) {
        position = new Position(row, col);
        this.display = display;
        this.color = color;
    }

    // move the entity to a new location
    /**
     *setPosition makes a new position object to set the Entity to a new location.
     *@param row passes in row where entity is located
     *@param col passes in column where entity is located
     */
    public void setPosition(int row, int col) {
        position = new Position(row, col);
    }

    // get the position of this entity
    /**
     *gets the position object.
     *@return returns a position object.
     */
    public Position getPosition() {
        return position;
    }
/**
     *gets the row of the position object.
     *@return returns the row of the position object.
     */
    public int getRow() {
        return position.getRow();
    }

    /**
     *gets the collumn of the position object.
     *@return returns the collumn of the position object.
     */
    public int getCol() {
        return position.getCol();
    }

    // translate the entity in space, unless it would hit a wall
    /**
     *Registers and handles movement of an entity in the 2D grid. Prevents entity from hitting the wall.
     *@param rowChange passes in value needed to construct the location of the next row.
     *@param colChange passes in value needed to construct the location of the next column.
     *@param room passes in data regarding which room the entity is in.
     *@return returns a boolean concerning the possibility of a movement to the next location.
     */
    public boolean move(int rowChange, int colChange, Room room) {
        // find new position
        int newRow = position.getRow() + rowChange;
        int newCol = position.getCol() + colChange;

        if (room.canGo(newRow, newCol)) {
            // draw a space where it currently is
            Terminal.warpCursor(position.getRow(), position.getCol());
            System.out.print(" ");

            // and then move it
            position = new Position(newRow, newCol);
            return true;
        } else {
            return false;
        }
    }

    // draw this entity to the screen
    /**
     *draws the entity onto the map so that the user can see its location and display icon.
     */
    public void draw() {
        Terminal.warpCursor(position.getRow(), position.getCol());
        Terminal.setForeground(color);
        System.out.print(display);
        Terminal.reset();
    }
}

