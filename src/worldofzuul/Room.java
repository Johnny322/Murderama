package worldofzuul;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Character jeff = new Character();
    private Step[][] stepList;
    private int currentPosition1 = 0;
    private int currentPosition2 = 0;
    private Step currentPosition;
    private Character player;
    
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
    	this.player = jeff;
        buildStepList(3, 3);

    }
    
    /** 
     * 
     * @param description
     * @param length
     * @param depth 
     */
    public Room(String description, int length, int depth) 
    {
    	
        this.description = description;
        this.exits = new HashMap<String, Room>();
    	this.player = jeff;
        this.stepList = new Step[length][depth];
        this.currentPosition = stepList[0][0];
        buildStepList(length, depth);
    }
    

    /**
     * Accessor method for the Player
     * @return 
     */
    public Character getCharacter() {
        return this.player;
    }
    
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
       // switch(direction) {
         //   case "east": 
           //     this.currentPosition1 = 1;
             //   this.currentPosition2 = 0;
               // this.currentPosition = this.stepList[currentPosition1][currentPosition2];
        	//System.out.println("Currently at 1, 0");
    	
        //}
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
    
    public void buildStepList(int length, int depth) {
    	for(int i = 0; i < length; i++) {
    		this.stepList[0][i] = new Step(new Character("Bobby", 50, "Noget information."));
    		this.stepList[1][i] = new Step(new Monster(50, 10));
    		this.stepList[2][i] = new Step(Item.BEER);
    	}
    }
    
    public Step move (String move) {
    	if(move.toLowerCase().equals("up")) {
    		if(currentPosition1 - 1 >= 0) {
    			currentPosition1 = currentPosition1 - 1;
    			this.currentPosition = stepList[currentPosition1][currentPosition2];
    	    	System.out.print("\r" + "\r" + "\r" + "\r");
    			System.out.println("Currently at " + currentPosition1 + ", " + currentPosition2);
    			return currentPosition;
    		} else { 
    			System.out.println("Out of bounds, try again"); 
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
    			System.out.println("Out of bounds, try again"); 
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
    			System.out.println("Out of bounds, try again"); 
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
    			System.out.println("Out of bounds, try again"); 
    			return null;
    			}
    	} else {
    		System.out.println("Bad move");
    		return null;
    	}
    }
    
    public boolean checkIfRoomTraversalIsOkay(String direction) {
        if(direction.equals("east")) {
    		if(this.currentPosition != this.stepList[1][2]) {
    			System.out.println("You must be next to the door, at 1, 2.");
    			return false;
    		} 
                   else { 
            return true;
        }
    	}
      
            
    	if(direction.equals("west")) {
    		if(this.currentPosition != this.stepList[1][0]) {
    			System.out.println("You must be next to the door, at 1, 0.");
    			return false;
    		} 
                   else { 
            return true;
        }
    	}
     
    	
    	if(direction.equals("north")) {
    		if(this.currentPosition != this.stepList[0][1]) {
    			System.out.println("You must be next to the door, at 0, 1.");
    			return false;
    		} 
                   else { 
            return true;
        }
         
        }
       
    	if(direction.equals("south")) {
    		if(this.currentPosition != this.stepList[2][1]) {
    			System.out.println("You must be next to the door, at 2, 1.");
    			return false;
    		} 
                   else { 
            return true;
        }    	
        } 
        
    	
    	return false;
    }
    
    public void printStep(Step step) {
    	System.out.println("On this step, there is: ");
    	if(step.getInformation() != null) {
    		System.out.println(step.getInformation());
    	} else { System.out.println("No information"); }
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
    	for(int i = 0; i < 3; i++) {
    		for(int j = 0; j < 3; j++) {
    			if(this.stepList[i][j] == this.stepList[currentPosition1][currentPosition2]) {
    				System.out.print(" X ");
    			} else { 
    				System.out.print(" O ");
    			}
    		}
    		System.out.println();
    	}
    }
    
    public Step currentPosition() {
    	return this.stepList[currentPosition1][currentPosition2];
    }
}