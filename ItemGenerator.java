import java.util.Random;
public class ItemGenerator{
	//local varriables
	private static ItemType type;
        private static String name;
        private static int weight;
        private static int value;
        private static int strength;

	//Weapon varribles 
	private static String[] weaponName = {"Bronze Sword", "Iron Sword", "Steel Sword", "Gold Sword", "Dragon Sword"};
	private static int[] weaponWeight = {9, 8, 11, 7, 13};
	private static int[] weaponValue = {10, 13, 20, 35, 60};
	private static int[] weaponStrength = {4, 5, 7, 6, 10};

	//Armor varribles
	private static String[] armorName = {"Bronze Armor", "Iron Armor", "Steel Armor", "Gold Armor", "Dragon Armor"};
	private static int[] armorWeight = {15, 19, 28, 14, 40};
	private static int[] armorValue = {25, 32, 45, 80, 120};
	private static int[] armorStrength = {16, 20, 28, 24, 40};

	//Other varribles
	private static String[] otherName = {"Diamond", "Cheese Wheel"};
	private static int[] otherWeight = {1, 4};
	private static int[] otherValue = {200, 4000};
	private static int[] otherStrength = {200, 1};


	//make new random generator
	private static Random rng = new Random();

	public static Item generate(){

		//generate the type
		int intType = rng.nextInt(3);

		//find out what type of item it is
		switch(intType){
			case 1: type = ItemType.Weapon; break;
			case 2: type = ItemType.Armor;  break;
			case 3: type = ItemType.Other;  break;	
			default: type = ItemType.Other; break;

		//depending on the type, set the item varribles 		 
		}if(type.equals(ItemType.Weapon)){
			//get a random weapon
			int weaponNum = rng.nextInt(5);
			//set all varribles of the weapon to the item
			name = weaponName[weaponNum];
			weight = weaponWeight[weaponNum];
			value = weaponValue[weaponNum];
			strength = weaponStrength[weaponNum];

		}if(type.equals(ItemType.Armor)){
			
			//get a random Armor
			int armorNum = rng.nextInt(5);

			//set all varrible of the armor to the item 
			name = armorName[armorNum];
			weight = armorWeight[armorNum];
			value = armorValue[armorNum];
			strength = armorStrength[armorNum];

                }if(type.equals(ItemType.Other)){
			
			//get a random Other
			int otherNum = rng.nextInt(2);

			//set all varribles of the other to the item
			name = otherName[otherNum];
			weight = otherWeight[otherNum];
			value = otherValue[otherNum];
			strength = otherStrength[otherNum];
		}
		Item item = new Item(type, name, weight, value, strength);
	return item;
	}
}
