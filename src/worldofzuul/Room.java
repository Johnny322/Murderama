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
    private final String description;
    private final HashMap<String, Room> exits;
    private final Step[][] stepList;
    private int currentPosition1 = 0;
    private int currentPosition2 = 0;
    private Step currentPosition;
    
    /**
     * Constructor for the room that the player spawns in
     * @param description 
     */
    public Room(String description) 
    {
        this.description = description;
        this.exits = new HashMap<String, Room>();
        this.stepList = new Step[3][3];
        this.currentPosition = stepList[currentPosition1][currentPosition2];
    }

    /**
     * Accessor method for the Player
     * @return 
     */
 
    
    /**
     * Method to set exit for a room
     * @param direction
     * @param neighbor 
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }
    
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    
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
        
        this.stepList[space1][space2] = new Step(new Evil("Jeff", 10, 10, "I think I saw Janiel Dørgensen in the projekt room earlier", true, "Student, big guy, large feet, small hands"));
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    public void buildHallway() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);

        
        this.stepList[space1][space2] = new Step(new Friendly("Jan Ithor", 10, 150, "I definately saw Jeff with the victim earlier", false, true, "What's your problem?", 150, "Janitor, big guy, large feet and large hands"));
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
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
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Friendly("Programmør", 150, 10, "Jeg tror jeg så ofret i project room tidligere", false, true, "Det er sindssygt", 200,  "A very nervous guy. Needs to get to know you before he will tell you anything"));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
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
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Friendly("Lunchlady", 150, 10, "The victim was from Jeff's class", false, true, "They had an argument some days ago", 100, "Lunchlady, has a lot of information"));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
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
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Friendly("Janiel Dørgensen", 150, 10, "", false, false, "fuck", 0, "Teacher of Object Oriented Programming"));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
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
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Friendly("Librarian", 150, 10, "Det var Jeff", false, true, "lol", 20, "Librarian, likely tells the truth"));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
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
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Evil("Srik Eørensen", 10, 150, "The person who did it starts with J", false, "Teacher of Computer Systems, small feet and large hands"));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
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
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Evil("Steen", 10, 150, "The person who did this has large feet", false, "Head of SIF, small stature, small feet and small hands"));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
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
    
    
    
    
    public Step move(String move) {
    	if(move.toLowerCase().equals("up")) {
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
    	if(move.toLowerCase().equals("down")) {
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
    	if(move.toLowerCase().equals("left")) {
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
    	if(move.toLowerCase().equals("right")) {
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
    
    public Step currentPosition() {
    	return this.stepList[currentPosition1][currentPosition2];
    }
}