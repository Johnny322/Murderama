package worldofzuul2;

import java.util.Set;
import java.util.HashMap;
import java.util.Random;


/**
 * Class for constructing the rooms in which the game is played.
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room 
{
    /**
     * Various attributes to describe a Room and the players position within it.
     */
    private final String description;
    private final HashMap<String, Room> exits;
    private final Step[][] stepList;
    private int currentPosition1 = 0;
    private int currentPosition2 = 0;
    private Step currentPosition;
	
    /**
     * Constructor for a Room.
     * @param description - Describes the Room.
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
     * Accessor for the description of this Room.
     * @return the description of this Room.
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Accessor for the Exit in a certain direction.
     * @param direction - The direction of the next exit.
     * @return the Room on the other side of an exit in a certain direction.
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
     * Method for building the basement-Room.
     */
    public void buildBasement() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);
        //this.stepList[space1][space2] = new Step( ***ITEM HERE?*** );

        int spaceCharacter1 = random.nextInt(3);
        int spaceCharacter2 = random.nextInt(3);
        while(spaceCharacter1 == space1 && spaceCharacter2 == space2) {
            spaceCharacter1 = random.nextInt(3);
            spaceCharacter2 = random.nextInt(3);
        }
        //this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new ***CHARACTER HERE?*** );

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
	
    /**
     * Method for building the Room describing the Outside.
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
        
        this.stepList[space1][space2] = new Step(new Evil("Jeff", 10, 60, "I don’t know anything I just arrived.", true, "Student, big guy, large feet, small hands."));
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Method for building the Hallway.
     */
    public void buildHallway() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);

        
        this.stepList[space1][space2] = new Step(new Friendly("Jan Ithor", 10, 150, "I have lost my key, help me find it and it may lead you to the murderer.", true, 150, "Janitor, big guy, large feet and large hands."));
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Method for constructing TEK.
     */
    public void buildTEK() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);
        this.stepList[space1][space2] = new Step(Consumable.KEY);

        int spaceCharacter1 = random.nextInt(3);
        int spaceCharacter2 = random.nextInt(3);
        while(spaceCharacter1 == space1 && spaceCharacter2 == space2) {
            spaceCharacter1 = random.nextInt(3);
            spaceCharacter2 = random.nextInt(3);
        }
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Friendly("Programmer", 12, 110, " I saw something moving in the basement, I can assure you that it did not belong down there.", true, 300,  "A very nervous guy. Needs to get to know you before he will tell you anything."));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    
    /**
     * Method for building the Cafeteria.
     */
    public void buildCafeteria() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);
        this.stepList[space1][space2] = new Step(Clue.FINGERPRINT);

        int spaceCharacter1 = random.nextInt(3);
        int spaceCharacter2 = random.nextInt(3);
        while(spaceCharacter1 == space1 && spaceCharacter2 == space2) {
            spaceCharacter1 = random.nextInt(3);
            spaceCharacter2 = random.nextInt(3);
        }
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Friendly("Lunchlady", 11, 70, "The victim was from Jeff's class in Computer Systems, their teacher Srik may know what happened. \n They had an argument some days ago.", true, 100, "Lunchlady, has a lot of information."));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Method for building U55.
     */
    public void buildU55() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);
        this.stepList[space1][space2] = new Step(Consumable.POTION);

        int spaceCharacter1 = random.nextInt(3);
        int spaceCharacter2 = random.nextInt(3);
        while(spaceCharacter1 == space1 && spaceCharacter2 == space2) {
            spaceCharacter1 = random.nextInt(3);
            spaceCharacter2 = random.nextInt(3);
        }
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Friendly("Janiel Dørgensen", 10, 100, "The murderer is a genius! Not that what he did was good, but he is sharp minded like me.", false, 0, "Teacher of Object Oriented Programming."));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Method for building the Library
     */
    public void buildLibrary() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);
        this.stepList[space1][space2] = new Step(Consumable.POTION);

        int spaceCharacter1 = random.nextInt(3);
        int spaceCharacter2 = random.nextInt(3);
        while(spaceCharacter1 == space1 && spaceCharacter2 == space2) {
            spaceCharacter1 = random.nextInt(3);
            spaceCharacter2 = random.nextInt(3);
        }
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Friendly("Librarian", 10, 120, "I saw a shadow behind the bookshelves. When I went to look I saw bloodstains on the ground. It was a big shadow, some heavy breaths, but eyes can betray. Surely this murderer is good at planning.", true, 20, "Librarian, likely tells the truth."));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Method for bulding U183.
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
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Evil("Srik Eørensen", 10, 150, "Some pupils had a fight today after class, i do not know what it was about but Jeff seemed a bit nervous. He is a lonely kid, loneliness can make you do stupid things. My advice will be to talk to Jan Ithor, he is the janitor and knows the school better than any of us.", false, "Teacher of Computer Systems, small feet and large hands."));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }
    
    /**
     * Method for building Projectroom.
     */
    public void buildProjectRoom() {
        Random random = new Random();
        int space1 = random.nextInt(3);
        int space2 = random.nextInt(3);
        this.stepList[space1][space2] = new Step(Clue.FOOTPRINT);

        int spaceCharacter1 = random.nextInt(3);
        int spaceCharacter2 = random.nextInt(3);
        while(spaceCharacter1 == space1 && spaceCharacter2 == space2) {
            spaceCharacter1 = random.nextInt(3);
            spaceCharacter2 = random.nextInt(3);
        }
        this.stepList[spaceCharacter1][spaceCharacter2] = new Step(new Evil("Steen", 10, 150, "I'm not aware of what happened but let me tell you something. We people have a bad habit. We tend to always believe what's obvious, and it makes sense, but is it not always what we see that is the truth.", false, "Head of SIF, small stature, small feet and small hands."));

        
    	for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.stepList[i][j] == null) {
                    this.stepList[i][j] = new Step();
                }
            }
        }
    }

    /**
     * Method for moving the player.
     * @param move - What direction the player needs to be moved.
     * @return the new position of the player, as a Step.
     */
    public Step move(String move) {
    	if(move.toLowerCase().equals("north")) {
    		if(currentPosition1 - 1 >= 0) {
    			currentPosition1 = currentPosition1 - 1;
    			this.currentPosition = stepList[currentPosition1][currentPosition2];
    			return currentPosition;
    		} else { 
    			return null;
    			}
    	} 
    	if(move.toLowerCase().equals("south")) {
    		if(currentPosition1 + 1 <= 2) {
    			currentPosition1 = currentPosition1 + 1;
    			this.currentPosition = stepList[currentPosition1][currentPosition2];
    			return currentPosition;
    		} else { 
    			return null;	
    		}
    	} 
    	if(move.toLowerCase().equals("west")) {
    		if(currentPosition2 - 1 >= 0) {
    			currentPosition2 = currentPosition2 - 1;
    			this.currentPosition = stepList[currentPosition1][currentPosition2];
    			return currentPosition;
    		} else { 
    			return null;
    			}
    	} 
    	if(move.toLowerCase().equals("east")) {
    		if(currentPosition2 + 1 <= 2) {
    			currentPosition2 = currentPosition2 + 1;
    			this.currentPosition = stepList[currentPosition1][currentPosition2];
    			return currentPosition;
    		} else { 
    			return null;
                }
    	} else {
    		return null;
    	}
    }
    
     /**
     * Method for checking if the player can move to the next room.
     * @param direction - What direction the player is trying to move.
     * @return boolean indicating if it is possible to move to the next room.
     */
    public boolean checkIfRoomTraversalIsOkay(String direction) {
        if(direction.toLowerCase().equals("east")) {
    		if(this.currentPosition != this.stepList[1][2]) {
    			return false;
    		} else {
                    return true;
                }
    	} 
    	if(direction.toLowerCase().equals("west")) {
    		if(this.currentPosition != this.stepList[1][0]) {
    			return false;
    		} else {
                    return true;
                }
    	} 
    	
    	if(direction.toLowerCase().equals("north")) {
    		if(this.currentPosition != this.stepList[0][1]) {
    			return false;
    		} else {
                    return true;
                }
    	}
    	
    	if(direction.toLowerCase().equals("south")) {
    		if(this.currentPosition != this.stepList[2][1]) {
    			return false;
    		} else {
                    return true;
                }
    	} 
    	return false;
    }
    

     /**
     * Method for printing the current room and everything in it, including doors leading to adjacent rooms.
     * @return String for printing in GUI
     */
    public String printRoom() {
        String s = "";
        Set<String> keys = exits.keySet();
        String currentPosition = "";
        String east = "", west = "", south = "";
        for(String exit : keys) {
            if(exit.equals("north")) {
                s = s + ("       d       \n");
            }  
            if(exit.equals("south")) {
                south = "        d       ";
            }
            if(exit.equals("west")) {
                west = "d ";
            } else {
                west = "   ";
            }
            if(exit.equals("east")) {
                east = " d";
            }
        }
    	for(int i = 0; i < 3; i++) {
            if(i == 0 || i == 2) {
                s = s + "   ";
            }
    		for(int j = 0; j < 3; j++) {
    			if(this.stepList[i][j] == this.stepList[currentPosition1][currentPosition2]) {
                                currentPosition = "You are currently at " + i + ", " + j;
    				s = s +" X ";
    			} else if(this.stepList[i][j].getNPC() != null) { 
    				s = s + " C ";
    			} else if(this.stepList[i][j].getItem() != null) {
                                s = s + " I ";
                        } else {
                            s = s + " O ";
                        }
    		}
                if(i == 1) {
                    s = s + east;
                }
    		s = s + "\n";
                if(i == 0) {
                    s = s + west;
                }
    	}
        s = s + south + "\n";
        s = s + currentPosition + "\n";
        return s;
    }
    
   /**
     * Accessor method for getting the players current position.
     * @return the players current position.
     */
    public Step currentPosition() {
    	return this.stepList[currentPosition1][currentPosition2];
    }
   
    /**
     * Accessor for the steplist.
     * @return the steplist.
     */
    public Step[][] getStepList() {
        return stepList;
    }
    
    /**
     * Setter for currentposition1.
     * @param currentPosition1 - The first coordinate of the player position.
     */
    public void setCurrentPosition1(int currentPosition1) {
        this.currentPosition1 = currentPosition1;
    }

    /**
     * Setter for currentposition2.
     * @param currentPosition2 - The second coordinate of the players position.
     */
    public void setCurrentPosition2(int currentPosition2) {
        this.currentPosition2 = currentPosition2;
    }
    
    /**
     * Accessor for the players current position.
     * @return Returns the Players current position.
     */
    public Step getCurrentPosition() {
        return currentPosition;
    }
    
    /**
     * Accessor for two coordinates of the players position.
     * @return the Coordinates of the players position.
     */
    public String getPosition() {
        return currentPosition1 + " " + currentPosition2;
    }
	
}