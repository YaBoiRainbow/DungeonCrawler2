import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Save{

	File saveFile = new File("Save.txt");
	public static PrintWriter saved;
//	private static Scanner sv = new Scanner(saveFile);

	private static int savePDmg;
	private static String savePName;
	private static int savePProc;	
	private static int savePRow;
	private static int savePCol;
	private static ArrayList<Enemy> saveEnemies;
	private static ArrayList<Box> saveBoxes;

//This method grabs all the games current info and stores it in a file named Save.txt . It does this by using printwriter to print all the variables and their info to the file as a string.
	public static void save(Player player, PrintWriter saved){
		//grbs player info and stores in new save variabl
		//
		//Tries to open the Save.txt and gives a error if file is nout found.
		File saveFile = new File("Save.txt");
		try{saved = new PrintWriter(saveFile);}

                catch(FileNotFoundException e){
                        System.out.println("ERROR: File Not Found.");
			return;
                }

		//grabs all of the player data and stores into new "save" variables. 
		savePName = player.getName();
                savePDmg = player.getDamage();
                savePProc = player.getProtection();
                 ArrayList<Item> saveInventory = Inventory.items;
                savePRow = player.getRow();
                savePCol = player.getCol();


                
                Room saveRoom = Game.room;
                //Stores all of the enemies in the game into an array. Same thing with the boxes.
		saveEnemies = Game.enemies;
                saveBoxes = Game.boxes;

		//Directly prints all of the Player variables onto the file in this order (name on the first line, Player Dmg on the second, Proc on the third etc. Printed as a string.
                saved.println(savePName);
                saved.println(savePDmg);
                saved.println(savePProc);
                saved.println(savePRow);
                saved.println(savePCol);

		//Since the inentory is saved as an Array of Items. This for loop goes through each item in the players inventory one by one, and prints out the item to the Save file as a string.
		saved.println(saveInventory.size());
		for(int i = 0; i < saveInventory.size(); i++){
			
			Item x = saveInventory.get(i);
			saved.println(x.toString());
			
		}

		//goes through the array of boxes and adds them to the save file one by one, each on a new line. Prints it as a string to the file via the boxToString method (located in Box class).
		saved.println(saveBoxes.size());
		for(int i = 0; i < saveBoxes.size(); i++){
			Box b = saveBoxes.get(i);
			String bs = b.boxToString(b);
			saved.println(bs);
		}

		//Gets all the enemies from the map and prints them and their info out to the "Save.txt" file as a string by using the eToString(enemy) method.
		saved.println(saveEnemies.size());
		for(int i = 0; i < saveEnemies.size(); i++){
			Enemy e = saveEnemies.get(i);
			String es = e.eToString(e);
			saved.println(es);
		}
		// String[] saveGrid = Room.mapGrid();
		//saved.println(Arrays.toString(saveGrid));
		//
		//Closes the printwriter and prints Save successful if it ran throuhg the method correctly w no hiccups.
		saved.close();
		System.out.println("Save succesful!");
	}


	//This method grabs all the info from the Save.txt file, converts it to its proper form (String to int, String to bool, etc.), and then replaces all current in game variables with the saved ones. It uploads files in the same order that they are saved in the save method. This ones a bit more complicated than save  
	public static void load(Player player){
		
		try{
		//takes in the Save.txt file
		File loadFile = new File("Save.txt");
		Scanner lf = new Scanner(loadFile);

		//initialize variables to load the saved info onto. 
		 int invSize = 0;
                ArrayList<Item> loadInv = new ArrayList<Item>();
		
		int boxSize = 0;
		ArrayList<Box> loadBox = new ArrayList<Box>();


		int eSize = 0;
		ArrayList<Enemy> loadEnemy = new ArrayList<Enemy>();

                int i = 0;
		//while loop repeats until there is no more informtion in the save.txt file.
                while(lf.hasNextLine()){
		//i acts as a line counter, counting what line the pc is currently on.
                i++;
		//Takes in an entire line of the Save file and converts it into a string
                String line = lf.nextLine();
                int lLength = line.length();

		//heres where things get tricky.

		//Since i == 0, we are on the first line, which always will be the players name and replaces it with whats in the firat line of the save file. 
                if(i == 0){
                        player.name = line;
                }
		//Since i == 1, we are on the second line of the file. We take in the players damage, and convert it from a String to an int, using Integer,valueOf(). We then save that value to loadDmg.
                else if(i == 1){
                        int loadDmg  = Integer.valueOf(line);
                }
                else if(i == 2){
                        int loadProc = Integer.valueOf(line);
                }
                else if(i == 3){
                        int loadPRow = Integer.valueOf(line);
                }
                else if(i == 4){
                        int loadPCol = Integer.valueOf(line);
                }
                else if(i == 5){
                        invSize = Integer.valueOf(line);
                        loadInv = new ArrayList<Item>(invSize);
                }
		//Inside this if statement, the computer takes all the variables individually from an Item from the players saved inventory and converts its proper variable, and then adds it to the players new load inventory.
		//
		//Explaining the if statement parameters.
		//If we are past the player info lines (i > 5), then for however long the array is is how many items we create.
                else if(i > 5 && i < 5 + invSize){
			//Saves the index of the first space between Item variables
                        int spaceIndex = line.indexOf(" ");
			//it then saves a substring of the text between spaces
                        String itemType = line.substring(0,spaceIndex);
			//After saving the text as a string it then converts it to its pproper variable form (if needed)
                        ItemType iT = Enum.valueOf(ItemType.class, itemType);

			//starts where the last variable ended and goes till the next space.
                        int spaceIndex2 = line.indexOf(" ", spaceIndex);
                        String nombre = line.substring(spaceIndex + 1, spaceIndex2);

                        int spaceIndex3 = line.indexOf(" ", spaceIndex2);
                        String weight = line.substring(spaceIndex2 +1, spaceIndex3);
                        int wg = Integer.valueOf(weight);

                        int spaceIndex4 = line.indexOf(" ", spaceIndex3);
                        String val = line.substring(spaceIndex3 + 1, spaceIndex4);
                        int value = Integer.valueOf(val);

                        String strength = line.substring(spaceIndex4 + 1);
                        int stren = Integer.valueOf(strength);
			
			//Once it has all of the Items info/variables, it can then create the Item and add it to the players new load inventory
                        Item a = new Item(iT, nombre, wg, value, stren);
                        loadInv.add(a);

		}
		//After loading in the Items info, it then takes in how many Boxes there are in the map, so the computer can then know how many Boxes it will need to create.
		else if(i == 5 + invSize){
			boxSize = Integer.valueOf(line);
			loadBox = new ArrayList<Box> (boxSize);
		}
		//Loads the boxs coordinates and what item is inside, as well as the items properties.
		else if(i > 5 + invSize && i < 5 + invSize + boxSize){
			int spaceIndex = line.indexOf(" ");
			String bStrRow = line.substring(0, spaceIndex);
		       	int bRow = Integer.valueOf(bStrRow);

			int spaceIndex2 = line.indexOf(" ", spaceIndex);
			String bStrCol = line.substring(spaceIndex, spaceIndex2);
			int bCol = Integer.valueOf(bStrCol);

			int spaceIndex3 = line.indexOf(" ", spaceIndex2);
			String itemType = line.substring(spaceIndex2, spaceIndex3);
			ItemType iT = Enum.valueOf(ItemType.class, itemType);

			int spaceIndex4 = line.indexOf(" ", spaceIndex3);
			String nombre = line.substring(spaceIndex3, spaceIndex4);
			
			int spaceIndex5 = line.indexOf(" ", spaceIndex4);
			String weight = line.substring(spaceIndex4, spaceIndex5);
			int wg = Integer.valueOf(weight);

			int spaceIndex6 = line.indexOf(" ",spaceIndex5);
			String val = line.substring(spaceIndex5, spaceIndex6);
			int value = Integer.valueOf(val);

			int spaceIndex7 = line.indexOf(" ",spaceIndex6);
			String strength = line.substring(spaceIndex6, spaceIndex7);
			int stren = Integer.valueOf(strength);

			Item a = new Item(iT,nombre,wg,value,stren);
			Box b = new Box(bRow, bCol, a);
			loadBox.add(b);
		}

		//It then restarts the same process but with enemies in the map instead of boxes.
		else if(i == 5 + invSize + boxSize){
			eSize = Integer.valueOf(line);
		}
			loadEnemy = new ArrayList<Enemy>(eSize);
		if(i > 5 + invSize + boxSize && i < 5 + invSize + boxSize + eSize){
			int spaceIndex = line.indexOf(".");
			String enemyName = line.substring(0,spaceIndex);
			
			int spaceIndex2 = line.indexOf(".", spaceIndex);
			int eAtck = Integer.valueOf(line.substring(spaceIndex ,spaceIndex2));

			int spaceIndex3 = line.indexOf(".");
			int eHeal = Integer.valueOf(line.substring(spaceIndex2,spaceIndex3));
			
			//if(line.substring(spaceIndex3 + 1) == "false"){
			//	boolean bttlActive = false;
			//}
			//else{boolean bttlActive = true;}

			int spaceIndex4 = line.indexOf(".", spaceIndex3);
			String eStrRow = line.substring(spaceIndex3, spaceIndex4);
			int erow = Integer.valueOf(eStrRow);

			int spaceIndex5 = line.indexOf(".", spaceIndex4);
			String eStrCol = line.substring(spaceIndex4,spaceIndex5);
			int ecol = Integer.valueOf(eStrCol);

			int spaceIndex6 = line.indexOf(".", spaceIndex5);
			String eStrProt = line.substring(spaceIndex5,spaceIndex6);
			int eProt = Integer.valueOf(eStrProt);
			Enemy e = new Enemy(enemyName, erow, ecol, eHeal, eAtck, eProt);
			loadEnemy.add(e);
		}
		}	
		}
		//If there isn't a file to be found, then nothing gets executed besides a simple Error message.
		 catch(FileNotFoundException e){
                        System.out.println("Error: File not found");
                } 
	}
	//Deletes all the contents inside of the Save.txt file
	public static void clear(){
	try{	
	saved = new PrintWriter("Save.txt");
	saved.print("");
	saved.close();
	}
	catch(FileNotFoundException e){
		System.out.println("Error: Save file not found");
	}
	}


	}
