package worldofzuul2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game 
{
    private final Parser parser;
    private Room currentRoom;
    private final Consumables items = new Consumables();
    private final PointSystem points = new PointSystem();
    private final Notes notes = new Notes();
    private Room outside, cafeteria, U55, basement, library, hallway, TEK;
    
    /**
     *
     */
    public Game() 
    {
        Gui start = new Gui();
        Gui.start();
        printHighscore();
        createRooms();
        parser = new Parser();
    }
    
    public static void printHighscore() {
        File file = new File("src/worldofzuul2/Highscore.txt");
        try {
            Scanner scanner = new Scanner(file);
            System.out.println("Current highscore is: " + scanner.nextInt());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateHighscore(int currentScore) {
        File file = new File("src/worldofzuul2/Highscore.txt");
        try {
            Scanner scanner = new Scanner(file);
            int currentHighscore = scanner.nextInt();
            if(currentScore > currentHighscore) {
                PrintStream writer = new PrintStream(file);
                writer.println(Integer.toString(currentScore));
                System.out.println("You now have the current highscore with: " + currentScore);
            } else {
                System.out.println("You dont have the highest score. ");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createRooms()
    {
        
        outside = new Room("outside the main entrance of the university");
        outside.buildOutside();
        cafeteria = new Room("in the cafeteria");
        cafeteria.buildCafeteria();
        U55 = new Room("in lecture room U55");
        U55.buildU55();
        basement = new Room("in the basement of the university");
        hallway = new Room ("in the long hallway in front of room U55");
        hallway.buildHallway();
        library = new Room (" in the university's library");
        library.buildLibrary();
        TEK = new Room ("in front of the bronze stairs at TEK");
        TEK.buildTEK();
        
        
        outside.setExit("west", TEK);
        hallway.setExit("east", U55);
        hallway.setExit("south", cafeteria);
        hallway.setExit("north", library);
        outside.setExit("east", hallway);
        U55.setExit("east", hallway);
        TEK.setExit("east", outside);
        //TEK.setExit("Hallway", hallway);
        cafeteria.setExit("north", hallway);
        library.setExit("south", hallway);
        hallway.setExit("west", outside);
        
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
        System.out.println("Your score was: " + points.getPoints());
        updateHighscore(points.getPoints());
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
        switch(commandWord) {
            case HELP:
                 printHelp();
                 break;
                 
            case GO:
                goRoom("north");
                break;
                
            case MOVE:
                move(command);
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                break;
                
//            case FIGHT:
//                return fight(command);
                
            case LOOT:
                return loot(currentRoom);     
            
            case USE:
                return use(command);
                
            case ACCUSE: 
                return accuse(currentRoom);
                
             case SEARCH: 
                return search();
                
             case TALK:
                 return talk();
                 
             case W:
                tempMove(commandWord);
                break;
                
            case A:
                tempMove(commandWord);
                break;
                
             case S:
                tempMove(commandWord);
                break;
                
             case D:
                tempMove(commandWord);
                break;
                
             case SHOWNOTES:
                 return notes.showNotes();
                
            default:
                return wantToQuit;
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
    
    private boolean processFight(Character character) {
        Player player = currentRoom.getPlayer();
        points.updateOnAction(currentRoom.getPlayer().getFightSpeed());
    	player.setHp(character.getDamage());
        character.changeHp(player.getDamage());
    	if(character.getHp() <= 0) {
    		System.out.println("Monster is dead!");
    		currentRoom.printRoom();
    		return false;
    	}
    	return true;
    }
//    
//    private boolean fight(Command command) {
//    	Room room = currentRoom;
//    	if(!command.hasSecondWord()) {
//            System.out.println("Fight monster or character?");
//            return false;
//        }
//        
//        System.out.println("You attack the monster. Type fight to attack the monster or flee if you do not want to fight. ");
//        boolean wantsToFight = processFight(monster);
//        while(wantsToFight) {	
//            Command command1 = parser.getCommand();
//            switch (command1.getCommandWord()) {
//                case FLEE:
//                    wantsToFight = false;
//                    room.printRoom();
//                    break;
//                                        
//                case FIGHT:
//                    wantsToFight = processFight(room.currentPosition().getMonster());
//                    break;
//                                        
//                default:
//                    System.out.println("Bad keyword. Try fight or flee. ");
//                    break;
//            }
//        }
//        return false;
//        
//        
//        Monster monster = room.currentPosition().getMonster();
//        Character character = room.currentPosition().getCharacter();
//    	if(command.getSecondWord().toLowerCase().equals("monster")) {
//    		if(monster != null) {
//                        System.out.println("You attack the monster. Type fight to attack the monster or flee if you do not want to fight. ");
//        		boolean wantsToFight = processFight(monster);
//        		while(wantsToFight) {	
//        			Command command1 = parser.getCommand();
//                                switch (command1.getCommandWord()) {
//                                    case FLEE:
//                                        wantsToFight = false;
//                                        room.printRoom();
//                                        break;
//                                        
//                                    case FIGHT:
//                                        wantsToFight = processFight(room.currentPosition().getMonster());
//                                        break;
//                                        
//                                    default:
//                                        System.out.println("Bad keyword. Try fight or flee. ");
//                                        break;
//                            }
//        		}
//        		return false;
//        	} else {
//        		System.out.println("There is no monster on this step, try again");
//        		return false;
//        	}
//    	} else if(command.getSecondWord().toLowerCase().equals("character")) {
//    		if(character != null) {
//                        System.out.println("You attack " + character.getName() + ". Type fight to attack or flee if you do not want to fight. ");
//        		boolean wantsToFight = processFight(character);
//        		while(wantsToFight) {	
//        			Command command1 = parser.getCommand();
//        			if(command1.getCommandWord().equals(CommandWord.FLEE)) {
//        				wantsToFight = false;
//        				room.printRoom();
//        			} else if(command1.getCommandWord().equals(CommandWord.FIGHT)) {
//        				wantsToFight = processFight(room.currentPosition().getCharacter());
//        			} else { 
//        				System.out.println("Bad keyword. Try fight or flee. ");
//        				wantsToFight = true;
//        			}
//        		}
//        	} else {
//        		System.out.println("There is no character on this step, try again");
//        		return false;
//        	}
//    	}
////        if(currentRoom.getCharacter().getHp() <= 0) {
//            System.out.println("You are dead");
//            return true;
//        }
//        
//        if(!currentRoom.getCharacter().isMurdererAlive()) {
//            System.out.println("You have killed the murderer. You have won the game!");
//            return true;
//        }
//        if(currentRoom.getCharacter().getLives() <= 0) {
//            System.out.println("You dont have any lives left. You have lost the game");
//            points.setScoreZero();
//            return true;
//        }
//    	return false;
//    }
    
    private boolean loot(Room room) {
    	if(room.currentPosition().getItem() != null) {
                points.updateOnAction(currentRoom.getPlayer().getFightSpeed());
    		System.out.println(room.getPlayer().lootItem(room.currentPosition().getItem()));
                room.currentPosition().voidItem();
    	} else { System.out.println("No item here"); }
        return false;
    }
    
    private boolean use(Command command) {
        Item[] inventory = currentRoom.getPlayer().getInventory();
        String secondWord = command.getSecondWord();
        
        if(!command.hasSecondWord()) {
            System.out.println("Use what?");
            currentRoom.getPlayer().showInventory();
            return false;
        }
        for(int i = 0; i < inventory.length; i++) {
            if(inventory[i] != null) {
                if(items.getConsumable(secondWord).equals(inventory[i])) {
                    points.updateOnAction(currentRoom.getPlayer().getFightSpeed());
                    getItemEffect(items.getConsumable(secondWord));
                    System.out.println("Used " + inventory[i].getName());
                    inventory[i] = null;
                    return false;
                }
            }
            
        }
        System.out.println("There is no " + secondWord + " in your inventory. ");
        return false;
    }
    

    private void goRoom(String direction) 
    {
//        if(!command.hasSecondWord()) {
  //          System.out.println("Go where?");
    //        return;
      //  }

        //String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else if(!currentRoom.checkIfRoomTraversalIsOkay(direction)) {
        	System.out.println("Try again!");
        }
        else {
                points.updateOnAction(currentRoom.getPlayer().getWalkSpeed());
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

        if (nextStep != null) {
            points.updateOnAction(currentRoom.getPlayer().getWalkSpeed());
            currentRoom.printStep(nextStep);
        } else {
            goRoom(direction);
        }
        
    }
    
    
    
    private void tempMove(CommandWord commandword) {
        String original = "";
        String direction = "";
        switch(commandword) {
            case W:
                direction = "up";
                original = "north";
                break;
                
            case A:
                direction = "left";
                original = "west";
                break;
                
            case S:
                direction = "down";
                original = "south";
                break;
                
            case D:
                direction = "right";
                original = "east";
                break;
        }
        Step nextStep = currentRoom.move(direction);

        if (nextStep != null) {
            points.updateOnAction(currentRoom.getPlayer().getWalkSpeed());
            currentRoom.printStep(nextStep);
        } else {
            goRoom(original);
        } 
        if (currentRoom.currentPosition().getNPC().friendly())
        { 
           currentRoom.currentPosition().getNPC().getInformation();
            System.out.println((currentRoom.currentPosition().getNPC().getInformation()));
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
    	NPC npc = room.currentPosition().getNPC();
        Character character = room.currentPosition().getCharacter();
                npc.setHostile(true);
    	if(npc.isMurderer()) {
    		System.out.println("You are correct, " + character.getName() + " is the murderer. ");
    		System.out.println(character.getName() + " wants to fight you. ");
    		return false;//fight(new Command(CommandWord.FIGHT, "character"));
    	} else {
    		System.out.println("You are not correct, " + character.getName() + " is not the murderer. ");
    		System.out.println(character.getName() + " wants to fight you for believing he was a murderer. ");
    		return false; //fight(new Command(CommandWord.FIGHT, "character"));
    	}
    	
    }
    
    public boolean search() {
        if(currentRoom.currentPosition().getItem() != null) {
            Item item = currentRoom.currentPosition().getItem();
            if(item.isMovable()) {
                getClue(item);
            } else {
                System.out.println(item.getName() + " is not movable");
            }
            
        }
        System.out.println("There is no clue here.");
        return false;
    }
    
    public boolean talk() {
        if(currentRoom.currentPosition().getNPC() == null) {
            System.out.println("There is no NPC here");
            return false;
        }
        String information = currentRoom.currentPosition().getNPC().getInformation();
        if(information == null) {
            return false;
        }
        System.out.println(information);
        for(String note : notes.getNotes()) {
            if(note.equals(currentRoom.currentPosition().getCharacter().getName() + " said: " + information)) {
                return false;
            }
        }
        notes.addNote(currentRoom.currentPosition().getCharacter().getName(), information);
        return false;
    }
    
    private void getItemEffect(Item item) {
        switch((Consumable) item) {
            case BEER:
                currentRoom.getPlayer().setWalkSpeed(2);
                currentRoom.getPlayer().setFightSpeed(2);
                currentRoom.getPlayer().setSearchSpeed(2);
                System.out.println("You feel slower. ");
                break;
                
            case POTION:
                currentRoom.getPlayer().setHp(-25);
                System.out.println("Potion has been used. ");
                System.out.println("Your current hp has been increased by 25 to " + currentRoom.getPlayer().getHp());
                break;
                
            case COFFEE:
                if(currentRoom.getPlayer().getWalkSpeed() - 2 < 5) {
                    currentRoom.getPlayer().setWalkSpeed(0);
                } else {
                    currentRoom.getPlayer().setWalkSpeed(-2);
                }
                
                if(currentRoom.getPlayer().getSearchSpeed() - 3 < 10) {
                    currentRoom.getPlayer().setSearchSpeed(0);
                } else {
                    currentRoom.getPlayer().setSearchSpeed(-3);
                }
                
                if(currentRoom.getPlayer().getFightSpeed() - 1 < 0) {
                    currentRoom.getPlayer().setFightSpeed(0);
                } else {
                    currentRoom.getPlayer().setFightSpeed(-1);
                }
                System.out.println("You feel energized. ");
                break;
                
            case KEY:
                if (currentRoom == outside || currentRoom == hallway) {
                    hallway.setExit("west", basement);
                    basement.setExit("east", hallway);
                    basement.setExit("south", outside);
                    outside.setExit("north", basement);
                    System.out.println("The key has opened a door to the basement!");
                }
                else {
                    System.out.println("The key doesn't fit in any doors in this area.");
                }
                break;
                
            default:
                System.out.println("This item does not exist. ");
                break;
        }
    }
    
    private void getClue(Item item) {
        switch((Clue) item) {
            case VICTIM:
                System.out.println("This is victim");
                break;
                
            case KNIFE:
                System.out.println("");
                break;
                
            case WITNESS:
                currentRoom.getPlayer().setWalkSpeed(-2);
                break;
                
            case FINGERPRINT:
                currentRoom.getPlayer().setWalkSpeed(-2);
                break;
                
            case FOOTPRINT:
                currentRoom.getPlayer().setWalkSpeed(-2);
                break;
                
            case CLOTHES:
                currentRoom.getPlayer().setWalkSpeed(-2);
                break;
                
            case LAMP:
                
                break;
                
            default:
                System.out.println("This clue does not exist. ");
                break;
        }
   }   
}
