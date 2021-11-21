// Main.java
// contains the main class for running the game
import ansi_terminal.*;

public class Main {
    public static void main(String args[])throws InterruptedException{
	// put termain in raw mode
        Terminal.rawMode();
        // make and run the Game
        Game game = new Game();
       	game.player.getInfo();	
	game.run();

        // put terminal back into cooked mode
        Terminal.cookedMode();
    }
}

