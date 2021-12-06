// EnemyGenerator.java
// this class contains a static method for creating enemies randomly

import java.util.Random;
public class EnemyGenerator {
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
			damage = 9;
			protection = 2;
			break;
		case 1: name = "Orc";
			health = 12;
			damage = 13;
			protection = 4;
			break;
		case 2: name = "Zombie";
			health = 2;
			damage = 12;
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
