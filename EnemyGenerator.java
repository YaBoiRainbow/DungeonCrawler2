// EnemyGenerator.java
// this class contains a static method for creating enemies randomly

import java.util.Random;
/**
 *
 *EnemyGenerator as a class generates random enemies with stats for the player to fight. 
 *This class takes in the location of the entity. It does not create the location.
 *@author Professor Ian, Tommy Garloch, Simon Jones, and Nico Guzzone.
 */
public class EnemyGenerator {

	/**
	 *generates an Enemy Object
	 *@param row passes in the row the Enemy is to be located on.
	 *@param col passes in the col the Enemy is to be located on.
	 *@return returns an Enemy Object with stats and location on game map (grid).
	 */
    public static Enemy generate(int row, int col) {
        int difficulty;
	String name;
        int health;
        int damage;
        int protection;
	Random rng = new Random();
	int enemyType = rng.nextInt(4);
	switch(enemyType){
		case 0: name = "Goblin";
			health = 9;
			damage = 4;
			protection = 1;
			break;
		case 1: name = "Orc";
			health = 12;
			damage = 7;
			protection = 2;
			break;
		case 2: name = "Zombie";
			health = 5;
			damage = 10;
			protection = 1;
			break;
		case 3: name = "Evil Breadstick";
			health = 4;
			damage = 1;
			protection = 1;
			break;
		default: name = "Zombie";
                        health = 2;
                        damage = 9;
                        protection = 1;
			break;
	}
    return new Enemy(name, row, col, health, damage, protection);
	}	
}
