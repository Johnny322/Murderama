package worldofzuul2;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game 
{
    private final Parser parser;
    private Room currentRoom;
    
          
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    private void createRooms()
    {
        
        Room outside, cafeteria, U55, basement, library, hallway, TEK;

        outside = new Room("outside the main entrance of the university");
        cafeteria = new Room("in the cafeteria");
        U55 = new Room("in lecture room U55");
        basement = new Room("in the basement of the university");
        hallway = new Room ("in the long hallway in front of room U55");
        library = new Room (" in the university's library");
        TEK = new Room ("in front of the bronze stairs at TEK");
        
        
        outside.setExit("TEK", TEK);
        hallway.setExit("U55", U55);
        hallway.setExit("Cafeteria", cafeteria);
        outside.setExit("Basement", basement);
        hallway.setExit("Library", library);
        outside.setExit("Hallway", hallway);
        U55.setExit("Hallway", hallway);        
        hallway.setExit("Basement", basement);
        basement.setExit("Hallway", hallway);
        TEK.setExit("Outside", outside);
        TEK.setExit("Hallway", hallway);
        cafeteria.setExit("Hallway", hallway);
        library.setExit("Hallway", hallway);
        hallway.setExit("Outside", outside);
        outside.setExit("Basement", basement);
        
        currentRoom = outside;
    }

    public void play() 
    {            
        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        currentRoom.printRoom();
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN || commandWord == CommandWord.FLEE) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.MOVE) {
            move(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } 
        else if (commandWord == commandWord.FIGHT) {
            return fight(command);
        }
        else if (commandWord == commandWord.LOOT) {
            return loot(currentRoom);
        }
        else if (commandWord == commandWord.USE) {
            return use(currentRoom);
        }
        else if (commandWord == commandWord.ACCUSE) {
            return accuse(currentRoom);
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    
    private boolean processFight(Monster monster) {
    	currentRoom.getCharacter().fight(currentRoom.currentPosition().getMonster());;
    	if(currentRoom.currentPosition().getMonster().getHp() <= 0) {
    		System.out.println("Monster is dead!");
    		currentRoom.printRoom();
    		return false;
    	}
    	return true;
    }
    private boolean processFight(Character character) {
    	currentRoom.getCharacter().fight(character);;
    	if(currentRoom.currentPosition().getCharacter().getHp() <= 0) {
    		System.out.println("Monster is dead!");
    		currentRoom.printRoom();
    		return false;
    	}
    	return true;
    }
    
    private boolean fight(Command command) {
    	Room room = currentRoom;
    	if(!command.hasSecondWord()) {
            System.out.println("Fight monster or character?");
            return false;
        }
    	if(command.getSecondWord().toLowerCase().equals("monster")) {
    		if(room.currentPosition().getMonster() != null) {
        		boolean wantsToFight = processFight(room.currentPosition().getMonster());
        		System.out.println("You attack the monster. Type fight to attack the monster or flee if you do not want to fight. ");
        		while(wantsToFight) {	
        			Command command1 = parser.getCommand();
        			if(command1.getCommandWord().equals(CommandWord.FLEE)) {
        				wantsToFight = false;
        				room.printRoom();
        			} else if(command1.getCommandWord().equals(CommandWord.FIGHT)) {
        				wantsToFight = processFight(room.currentPosition().getMonster());
        			} else { 
        				System.out.println("Bad keyword. Try fight or flee. ");
        			}
        		}
        		return false;
        	} else {
        		System.out.println("There is no monster on this step, try again");
        		return false;
        	}
    	} else if(command.getSecondWord().toLowerCase().equals("character")) {
    		if(room.currentPosition().getCharacter() != null) {
        		boolean wantsToFight = processFight(room.currentPosition().getCharacter());
        		System.out.println("You attack " + room.currentPosition().getCharacter().getName() + ". Type fight to attack or flee if you do not want to fight. ");
        		while(wantsToFight) {	
        			Command command1 = parser.getCommand();
        			if(command1.getCommandWord().equals(CommandWord.FLEE)) {
        				wantsToFight = false;
        				room.printRoom();
        			} else if(command1.getCommandWord().equals(CommandWord.FIGHT)) {
        				wantsToFight = processFight(room.currentPosition().getCharacter());
        			} else { 
        				System.out.println("Bad keyword. Try fight or flee. ");
        				wantsToFight = true;
        			}
        		}
        		return false;
        	} else {
        		System.out.println("There is no character on this step, try again");
        		return false;
        	}
    	}
    	return false;
    	
    	
    }
    
    private boolean loot(Room room) {
    	if(room.currentPosition().getItem() != null) {
    		room.getCharacter().lootItem(room.currentPosition().getItem());
                room.currentPosition().voidItem();
    		room.getCharacter().showInventory();
    	} else { System.out.println("No item here"); }
        return false;
    }
    
    private boolean use(Room room) {
        room.getCharacter().useItem(room.getCharacter().getItemFromInventory(0));
        return false;
    }
    

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else if(!currentRoom.checkIfRoomTraversalIsOkay(direction)) {
        	System.out.println("Try again!");
        }
        else {
        	nextRoom.setCurrentPosition(currentRoom.currentPosition(), direction);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            currentRoom.printStep(currentRoom.currentPosition());
        }
    }
    
    private void move(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Invalid move!");
            return;
        }

        String direction = command.getSecondWord();

        Step nextStep = currentRoom.move(direction);

        if (nextStep == null) {
            System.out.println("Bad move!");
        }
        else {
            currentRoom.printStep(nextStep);
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
    
    private boolean accuse(Room room) 
    {
    	Character character = room.currentPosition().getCharacter();
    	character.setStatus();
    	if(character.isMurderer()) {
    		System.out.println("You are correct, " + character.getName() + " is the murderer. ");
    		System.out.println(character.getName() + " wants to fight you. ");
    		return fight(new Command(CommandWord.FIGHT, "character"));
    	} else {
    		System.out.println("You are not corrent, " + character.getName() + " is not the murderer. ");
    		System.out.println(character.getName() + " wants to fight you for believing he was a murderer. ");
    		return fight(new Command(CommandWord.FIGHT, "character"));
    	}
    	
    }
}
