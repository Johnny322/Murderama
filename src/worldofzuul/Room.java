package worldofzuul2;

import java.util.Set;
import java.util.HashMap;
import java.util.Random;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room 
{
    /**
     * Various atributs to describe a Room.
     */
    private final String description;
    private final HashMap<String, Room> exits;
    private final Step[][] stepList;
    private int currentPosition1 = 0;
    private int currentPosition2 = 0;
    private Step currentPosition;
    
    /**
     * Constructor for the room that the player spawns in.
     * @param description - Describes the Room and its postition.
     */
    public Room(String description) 
    {
        this.description = description;
        this.exits = new HashMap<String, Room>();
        this.stepList = new Step[3][3];
        this.currentPosition = stepList[currentPosition1][currentPosition2];
    }
   
    /**
     * Method to set exit for a room.
     * @param direction - The direction of the next Room.
     * @param neighbor - The neighboring Rooms.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Accesor for the describtion of this Room.
     * @return description - Returns the description of this Room.
     */
    
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Accesor for the long description for this Room.
     * @return LongDescription - Returns the Long description for this room.
     */
    
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }
    
    /**
     * Accesor for the Exits availbe for current room.
     * @return Exits - Returns the avaible exits for this room.
     */
    
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Accesor for the Exit in a certain direction.
     * @param direction - The direction of the next exit.
     * @return exits.get(direction) - Returns the exit in a certain direction.
     */
    
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Setter for current position of the player.
     * @param step - What step the player currently is on.
     * @param direction  - The direction the player is traveling.
     */
    
    public void setCurrentPosition(Step step, String direction) {
    	if(direction.toLowerCase().equals("east")) {
    		this.currentPosition1 = 1;
    		this.currentPosition2 = 0;
        	this.currentPosition = this.stepList[currentPosition1][currentPosition2];
        	System.out.println("Currently at 1, 0");
    	}
    	
    	if(direction.toLowerCase().equals("north")) {
    		this.currentPosition1 = 2;
    		this.currentPosition2 = 1;
        	this.currentPosition = this.stepList[currentPosition1][currentPosition2];
        	System.out.println("Currently at 2, 1");
    	}
    	
    	if(direction.toLowerCase().equals("west")) {
    		this.currentPosition1 = 1;
    		this.currentPosition2 = 2;
        	this.currentPosition = this.stepList[currentPosition1][currentPosition2];
        	System.out.println("Currently at 1, 2");
    	}
    	
    	if(direction.toLowerCase().equals("south")) {
    		this.currentPosition1 = 0;
    		this.currentPosition2 = 1;
        	this.currentPosition = this.stepList[currentPosition1][currentPosition2];
        	System.out.println("Currently at 0, 1");
    	}
    }
    
    /**
     * Constructer for the Outside
     */
    
    public void buildOutside() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);
        //Lav liste over Rooms til 0,0 i outside for map
       // this.stepList[0][0] = new Step();

        while(space1 == 0 && space2 == 0) {
            space1 = random.nextInt(3);
            space2 = random.nextInt(3);
        }
        
        this.stepList[space1][space2] = new Step(new Evil("Jeff", 10, 10, "I don’t know anything I just arrived", true, "Student, big guy, large feet, small hands"));
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Constructer for the Hallway
     */
    
    public void buildHallway() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);

        
        this.stepList[space1][space2] = new Step(new Friendly("Jan Ithor", 10, 150, "I have lost my key, help me find it and it may lead you to the murderer", false, true, "What's your problem?", 150, "Janitor, big guy, large feet and large hands"));
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Constructer for TEK
     */
    
    public void buildTEK() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);
        this.stepList[space1][space2] = new Step(Consumable.KEY);
        System.out.println("Clue placed at " + space1 + ", " + space2);

        int spaceCharacter1 = random.nextInt(3);
        int spaceCharacter2 = random.nextInt(3);
        while(spaceCharacter1 == space1 && spaceCharacter2 == space2) {
            spaceCharacter1 = random.nextInt(3);
            spaceCharacter2 = random.nextInt(3);
        }
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Friendly("Programmør", 150, 10, " saw something moving in the basement, I can assure that it did not belong down there", false, true, "Det er sindssygt", 200,  "A very nervous guy. Needs to get to know you before he will tell you anything"));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Constructer for the Cafeteria.
     */
    
    public void buildCafeteria() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);
        this.stepList[space1][space2] = new Step(Clue.VICTIM);

        int spaceCharacter1 = random.nextInt(3);
        int spaceCharacter2 = random.nextInt(3);
        while(spaceCharacter1 == space1 && spaceCharacter2 == space2) {
            spaceCharacter1 = random.nextInt(3);
            spaceCharacter2 = random.nextInt(3);
        }
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Friendly("Lunchlady", 150, 10, "TThe victim was from Jeff's class in Computer Systems,  their teacher Erik may know what happened", false, true, "They had an argument some days ago", 100, "Lunchlady, has a lot of information"));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Constructer for U55.
     */
    
    public void buildU55() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);
        this.stepList[space1][space2] = new Step(Consumable.POTION);
        System.out.println("Clue placed at " + space1 + ", " + space2);

        int spaceCharacter1 = random.nextInt(3);
        int spaceCharacter2 = random.nextInt(3);
        while(spaceCharacter1 == space1 && spaceCharacter2 == space2) {
            spaceCharacter1 = random.nextInt(3);
            spaceCharacter2 = random.nextInt(3);
        }
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Friendly("Janiel Dørgensen", 150, 10, "", false, false, "The murderer is a genius! Not that what he did was good, but he is sharp minded like me", 0, "Teacher of Object Oriented Programming"));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Constructer for the Library.
     */
    
    public void buildLibrary() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);
        this.stepList[space1][space2] = new Step(Consumable.POTION);
        System.out.println("Clue placed at " + space1 + ", " + space2);

        int spaceCharacter1 = random.nextInt(3);
        int spaceCharacter2 = random.nextInt(3);
        while(spaceCharacter1 == space1 && spaceCharacter2 == space2) {
            spaceCharacter1 = random.nextInt(3);
            spaceCharacter2 = random.nextInt(3);
        }
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Friendly("Librarian", 150, 10, "I saw a shadow behind the bookshelves. When i went to look I saw bloodstains on the ground. It was a big shadow, some heavy breaths, but eyes can betray. Surely this murderer is good at planning", false, true, "lol", 20, "Librarian, likely tells the truth"));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Constructer for U183.
     */
    
    public void buildU183() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);
        this.stepList[space1][space2] = new Step(Clue.VICTIM);

        int spaceCharacter1 = random.nextInt(3);
        int spaceCharacter2 = random.nextInt(3);
        while(spaceCharacter1 == space1 && spaceCharacter2 == space2) {
            spaceCharacter1 = random.nextInt(3);
            spaceCharacter2 = random.nextInt(3);
        }
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Evil("Srik Eørensen", 10, 150, "TSome pupils had a fight today after class, i do not know what it was about but Jeff seemed a bit nervous. He is a lonely kid, loneliness can make you do stupid things. My advise will be to talk to Jan Ithor, he is the janitor and knows the school better than any of us.", false, "Teacher of Computer Systems, small feet and large hands"));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Constructer for Projectroom.
     */
    
    public void buildProjectRoom() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);
        this.stepList[space1][space2] = new Step(Clue.VICTIM);

        int spaceCharacter1 = random.nextInt(3);
        int spaceCharacter2 = random.nextInt(3);
        while(spaceCharacter1 == space1 && spaceCharacter2 == space2) {
            spaceCharacter1 = random.nextInt(3);
            spaceCharacter2 = random.nextInt(3);
        }
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Evil("Steen", 10, 150, "I not aware of what have happened but let me tell you something. We people have a bad habit. We tend to always believe on what's obvious, and it make sense but is it not always what we see that is the truth", false, "Head of SIF, small stature, small feet and small hands"));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Method for placing Items randomly on steps.
     * @param item - The item that is placed on the step.
     */
    
    public void buildStepList(Item item) {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);
        this.stepList[space1][space2] = new Step(item);

    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[space1][space2] != this.stepList[i][j]) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Method for randomly placing a NPC on a step in a room, if there is no item on the step.
     * @param npc - What NPC is being placed on the step.
     * @param item  - If there is a item on the step, the NPC can't be placed.
     */
    
    public void buildStepList(NPC npc, Item item) {
        Random random = new Random();
        int spaceCharacter1 = random.nextInt(3);
        int spaceCharacter2 = random.nextInt(3);
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(npc);
        
        int spaceItem1 = random.nextInt(3);
        int spaceItem2 = random.nextInt(3);
        while(spaceItem1 == spaceCharacter1 && spaceItem2 == spaceCharacter2) {
            spaceItem1 = random.nextInt(3);
            spaceItem2 = random.nextInt(3);
        }
        
        this.stepList[spaceItem1][spaceItem2] = new Step(item);

    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[spaceCharacter1][spaceCharacter2] != this.stepList[i][j] || this.stepList[spaceItem1][spaceItem2] != this.stepList[i][j]) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    
    /**
     * Method for moving the player.
     * @param move - What direction the player needs to be moved.
     * @return Returns the new postion of the player.
     */
    
    public Step move(String move) {
    	if(move.toLowerCase().equals("north")) {
    		if(currentPosition1 - 1 >= 0) {
    			currentPosition1 = currentPosition1 - 1;
    			this.currentPosition = stepList[currentPosition1][currentPosition2];
    	    	System.out.print("\r" + "\r" + "\r" + "\r");
    			System.out.println("Currently at " + currentPosition1 + ", " + currentPosition2);
    			return currentPosition;
    		} else { 
    			return null;
    			}
    	} 
    	if(move.toLowerCase().equals("south")) {
    		if(currentPosition1 + 1 <= 2) {
    			currentPosition1 = currentPosition1 + 1;
    			this.currentPosition = stepList[currentPosition1][currentPosition2];
    	    	System.out.print("\r" + "\r" + "\r" + "\r");
    			System.out.println("Currently at " + currentPosition1 + ", " + currentPosition2);
    			return currentPosition;
    		} else { 
    			return null;	
    		}
    	} 
    	if(move.toLowerCase().equals("west")) {
    		if(currentPosition2 - 1 >= 0) {
    			currentPosition2 = currentPosition2 - 1;
    			this.currentPosition = stepList[currentPosition1][currentPosition2];
    	    	System.out.print("\r" + "\r" + "\r" + "\r");
    			System.out.println("Currently at " + currentPosition1 + ", " + currentPosition2);
    			return currentPosition;
    		} else { 
    			return null;
    			}
    	} 
    	if(move.toLowerCase().equals("east")) {
    		if(currentPosition2 + 1 <= 2) {
    			currentPosition2 = currentPosition2 + 1;
    			this.currentPosition = stepList[currentPosition1][currentPosition2];
    	    	System.out.print("\r" + "\r" + "\r" + "\r");
    			System.out.println("Currently at " + currentPosition1 + ", " + currentPosition2);
    			return currentPosition;
    		} else { 
    			return null;
                }
    	} else {
    		System.out.println("Bad move");
    		return null;
    	}
    }
    
    /**
     * Method for checking if the player can move to the next room.
     * @param direction - What direction the player is trying to move.
     * @return Returns if it is posible to move to the next room.
     */
    
    public boolean checkIfRoomTraversalIsOkay(String direction) {
        if(direction.toLowerCase().equals("east")) {
    		if(this.currentPosition != this.stepList[1][2]) {
    			System.out.println("You must be next to the door, at 1, 2.");
    			return false;
    		} else {
                    return true;
                }
    	} 
    	if(direction.toLowerCase().equals("west")) {
    		if(this.currentPosition != this.stepList[1][0]) {
    			System.out.println("You must be next to the door, at 1, 0.");
    			return false;
    		} else {
                    return true;
                }
    	} 
    	
    	if(direction.toLowerCase().equals("north")) {
    		if(this.currentPosition != this.stepList[0][1]) {
    			System.out.println("You must be next to the door, at 0, 1.");
    			return false;
    		} else {
                    return true;
                }
    	}
    	
    	if(direction.toLowerCase().equals("south")) {
    		if(this.currentPosition != this.stepList[2][1]) {
    			System.out.println("You must be next to the door, at 2, 1.");
    			return false;
    		} else {
                    return true;
                }
    	} 
        
    	
    	return false;
    }
    
    /**
     * Method thats prints if there is information, a item, NPC or a Monster on the step the player is standing on. 
     * @param step - The step the player is standing on.
     */
    
    public void printStep(Step step) {
    	if(step.getInformation() != null) {
    		System.out.println(step.getInformation());
    	} 
    	if(step.getCharacter() != null) {
    		System.out.println("Character: " +step.getCharacter().getName());
    	} 
    	if(step.getItem() != null) {
    		System.out.println("A  " +step.getItem().toString());
    	} 
    	if(step.getMonster() != null) {
    		System.out.println("Monster: " + step.getMonster().getHp() + " hp");
    	} 
     	printRoom();
    	   	
    }
    
    /**
     * Method for printing the exits of the current room + the information, item, NPC and Monster on the current step the player is on.
     */
    
    public void printRoom() {
        Set<String> keys = exits.keySet();
        String east = "", west = "", south = "";
        for(String exit : keys) {
            if(exit.equals("north")) {
                System.out.println("      d       ");
            }  
            if(exit.equals("south")) {
                south = "      d       ";
            }
            if(exit.equals("west")) {
                west = "d ";
            } else {
                west = "  ";
            }
            if(exit.equals("east")) {
                east = " d";
            }
        }
    	for(int i = 0; i < 3; i++) {
            if(i == 0 || i == 2) {
                System.out.print("  ");
            }
    		for(int j = 0; j < 3; j++) {
    			if(this.stepList[i][j] == this.stepList[currentPosition1][currentPosition2]) {
    				System.out.print(" X ");
    			} else if(this.stepList[i][j].getNPC() != null) { 
    				System.out.print(" C ");
    			} else if(this.stepList[i][j].getItem() != null) {
                                System.out.print(" I ");
                        }else if(this.stepList[i][j].getMonster() != null) {
                                System.out.print(" M ");
                        } else {
                            System.out.print(" O ");
                        }
    		}
                if(i == 1) {
                    System.out.print(east);
                }
    		System.out.println();
                if(i == 0) {
                    System.out.print(west);
                }
    	}
        System.out.println(south);
    }
    
    /**
     * Accesor method for getting the players current position.
     * @return Returns the players current postion.
     */
    
    public Step currentPosition() {
    	return this.stepList[currentPosition1][currentPosition2];
    }
   
    /**
     * Accesor for the steplist.
     * @return Returns the steplist.
     */
    
    public Step[][] getStepList() {
        return stepList;
    }
    
    /**
     * Setter for the currentposition1
     * @param currentPosition1 - The first cordinate of the player position.
     */
    
    public void setCurrentPosition1(int currentPosition1) {
        this.currentPosition1 = currentPosition1;
    }

    /**
     * Setter for the currentposition2
     * @param currentPosition2 - The second cordinate for the players posirion.
     */
    
    public void setCurrentPosition2(int currentPosition2) {
        this.currentPosition2 = currentPosition2;
    }
    
    /**
     * Accesor for the players current position.
     * @return Returns the Players current position.
     */
    
    public Step getCurrentPosition() {
        return currentPosition;
    }
    
    /**
     * Accesor for two cordinates of the players position.
     * @return Returns the Cordinates of the players position.
     */
    
    public String getPosition() {
        return currentPosition1 + " " + currentPosition2;
    }
	
}
