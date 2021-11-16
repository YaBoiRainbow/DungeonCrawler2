import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Intro{	
	private String name;
	public void getInfo()throws InterruptedException{
        
	int choice;
        	Scanner in = new Scanner(System.in);
		System.out.println("Who... Who are you...");
        	this.name = in.nextLine();
        	System.out.println(this.name + "... I see... And what is it that you are here for...");
        	System.out.println("(Enter the number corrisponding to your answer)\n1: For Glory!\n2: To destroy you!\n3: I dont know, who even are you?");
        	choice = in.nextInt();
        	switch(choice){
            		case 1: System.out.println("As you wish...");
                    		Thread.sleep(3000);
				break;
            		case 2: System.out.println("... You will try...");
				Thread.sleep(3000);
				break;
            		case 3: System.out.println("I... I am your wort nightmare...");
				Thread.sleep(3000);
				break;
            		default: System.out.println("What? what did you just say? Because it was definitly not 1, 2 or 3... anyways, you will die now.");
				Thread.sleep(5000);
				break;
		}
	}


}
