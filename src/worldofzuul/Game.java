package worldofzuul2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private final  Items items = new Items();
    private final PointSystem points = new PointSystem();
    private final Notes notes = new Notes();
    
    /**
     *
     */
    public Game() 
    {
        printHighscore();
        createRooms();
        parser = new Parser();
    }
    
    public static void printHighscore() {
        File file = new File("C:/Users/Simon/Documents/NetBeansProjects/WorldOfZuul2/src/worldofzuul2/Highscore.txt");
        try {
            Scanner scanner = new Scanner(file);
            System.out.println("Current highscore is: " + scanner.nextInt());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateHighscore(int currentScore) {
        File file = new File("C:/Users/Simon/Documents/NetBeansProjects/WorldOfZuul2/src/worldofzuul2/Highscore.txt");
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
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        TEK = new Room ("in front of the bronze stairs at TEK", Item.BEER);
        
        
        outside.setExit("west", TEK);
        hallway.setExit("west", U55);
        hallway.setExit("south", cafeteria);
        outside.setExit("south", basement);
        hallway.setExit("north", library);
        outside.setExit("east", hallway);
        U55.setExit("east", hallway);        
        hallway.setExit("Basement", basement);
        basement.setExit("Hallway", hallway);
        basement.setExit("south", outside);
        TEK.setExit("east", outside);
        //TEK.setExit("Hallway", hallway);
        cafeteria.setExit("Hallway", hallway);
        library.setExit("south", hallway);
        hallway.setExit("west", outside);
        outside.setExit("north", basement);
        
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
                
            case FIGHT:
                return fight(command);
                
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
    
    private boolean processFight(Monster monster) {
        points.updateOnAction(currentRoom.getCharacter().getFightSpeed());
    	currentRoom.getCharacter().fight(monster);;
    	if(monster.getHp() <= 0) {
    		System.out.println("Monster is dead!");
    		currentRoom.printRoom();
    		return false;
    	}
    	return true;
    }
    private boolean processFight(Character character) {
        Character player = currentRoom.getCharacter();
        points.updateOnAction(player.getFightSpeed());
    	player.fight(character);
        if(currentRoom.getCharacter().getHp() <= 0) {
            return false;
        }
    	if(character.getHp() <= 0) {
                if(character.isMurderer()) {
                    player.setMurdererAlive();
                    return false;
                } else {
                    System.out.println("Character is dead!");
                    player.setLives();
                    System.out.println("You lost one life. Only " + player.getLives());
                    currentRoom.printRoom();
                    return false;
                }
    	}
    	return true;
    }
    
    private boolean fight(Command command) {
    	Room room = currentRoom;
    	if(!command.hasSecondWord()) {
            System.out.println("Fight monster or character?");
            return false;
        }
        Monster monster = room.currentPosition().getMonster();
        Character character = room.currentPosition().getCharacter();
    	if(command.getSecondWord().toLowerCase().equals("monster")) {
    		if(monster != null) {
                        System.out.println("You attack the monster. Type fight to attack the monster or flee if you do not want to fight. ");
        		boolean wantsToFight = processFight(monster);
        		while(wantsToFight) {	
        			Command command1 = parser.getCommand();
                                switch (command1.getCommandWord()) {
                                    case FLEE:
                                        wantsToFight = false;
                                        room.printRoom();
                                        break;
                                        
                                    case FIGHT:
                                        wantsToFight = processFight(room.currentPosition().getMonster());
                                        break;
                                        
                                    default:
                                        System.out.println("Bad keyword. Try fight or flee. ");
                                        break;
                            }
        		}
        		return false;
        	} else {
        		System.out.println("There is no monster on this step, try again");
        		return false;
        	}
    	} else if(command.getSecondWord().toLowerCase().equals("character")) {
    		if(character != null) {
                        System.out.println("You attack " + character.getName() + ". Type fight to attack or flee if you do not want to fight. ");
        		boolean wantsToFight = processFight(character);
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
        	} else {
        		System.out.println("There is no character on this step, try again");
        		return false;
        	}
    	}
        if(currentRoom.getCharacter().getHp() <= 0) {
            System.out.println("You are dead");
            return true;
        }
        
        if(!currentRoom.getCharacter().isMurdererAlive()) {
            System.out.println("You have killed the murderer. You have won the game!");
            return true;
        }
        if(currentRoom.getCharacter().getLives() <= 0) {
            System.out.println("You dont have any lives left. You have lost the game");
            return true;
        }
    	return false;
    }
    
    private boolean loot(Room room) {
    	if(room.currentPosition().getItem() != null) {
                points.updateOnAction(currentRoom.getCharacter().getFightSpeed());
    		room.getCharacter().lootItem(room.currentPosition().getItem());
                room.currentPosition().voidItem();
    		room.getCharacter().showInventory();
    	} else { System.out.println("No item here"); }
        return false;
    }
    
    private boolean use(Command command) {
        Item[] inventory = currentRoom.getCharacter().getInventory();
        String secondWord = command.getSecondWord();
        
        if(!command.hasSecondWord()) {
            System.out.println("Use what?");
            currentRoom.getCharacter().showInventory();
            return false;
        }
        for(int i = 0; i < inventory.length; i++) {
            if(items.getItem(secondWord) == inventory[i]) {
                points.updateOnAction(currentRoom.getCharacter().getFightSpeed());
                getItemEffect(items.getItem(secondWord));
                inventory[i] = null;
                return false;
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
                points.updateOnAction(currentRoom.getCharacter().getWalkSpeed());
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
            points.updateOnAction(currentRoom.getCharacter().getWalkSpeed());
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
            points.updateOnAction(currentRoom.getCharacter().getWalkSpeed());
            currentRoom.printStep(nextStep);
        } else {
            goRoom(original);
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
    		System.out.println("You are not correct, " + character.getName() + " is not the murderer. ");
    		System.out.println(character.getName() + " wants to fight you for believing he was a murderer. ");
    		return fight(new Command(CommandWord.FIGHT, "character"));
    	}
    	
    }
    
    public boolean search() {
        if(currentRoom.currentPosition().getClue() != null) {
            Clue clue = currentRoom.currentPosition().getClue();
            getClue(clue);
        }
        System.out.println("There is no clue here.");
        return false;
    }
    
    public boolean talk() {
        String information = currentRoom.currentPosition().getCharacter().getInformation();
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
        switch(item) {
            case BEER:
                currentRoom.getCharacter().setWalkSpeed(2);
                currentRoom.getCharacter().setFightSpeed(2);
                currentRoom.getCharacter().setSearchSpeed(2);
                System.out.println("You feel slower. ");
                break;
                
            case POTION:
                currentRoom.getCharacter().setHp(-25);
                System.out.println("Potion has been used. ");
                System.out.println("Your current hp has been increased by 25");
                break;
                
            case COFFEE:
                if(currentRoom.getCharacter().getWalkSpeed() - 2 < 5) {
                    currentRoom.getCharacter().setWalkSpeed();
                } else {
                    currentRoom.getCharacter().setWalkSpeed(-2);
                }
                
                if(currentRoom.getCharacter().getSearchSpeed() - 3 < 10) {
                    currentRoom.getCharacter().setSearchSpeed();
                } else {
                    currentRoom.getCharacter().setSearchSpeed(-3);
                }
                
                if(currentRoom.getCharacter().getFightSpeed() - 1 < 0) {
                    currentRoom.getCharacter().setFightSpeed();
                } else {
                    currentRoom.getCharacter().setFightSpeed(-1);
                }
                System.out.println("You feel energized. ");
                break;
                
            default:
                System.out.println("This item does not exist. ");
                break;
        }
    }
    
    private void getClue(Clue clue) {
        switch(clue) {
            case VICTIM:
                System.out.println("This is victim");
                break;
                
            case KNIFE:
                System.out.println("");
                break;
                
            case WITNESS:
                currentRoom.getCharacter().setWalkSpeed(-2);
                break;
                
            case FINGERPRINT:
                currentRoom.getCharacter().setWalkSpeed(-2);
                break;
                
            case FOOTPRINT:
                currentRoom.getCharacter().setWalkSpeed(-2);
                break;
                
            case CLOTHES:
                currentRoom.getCharacter().setWalkSpeed(-2);
                break;
                
            case LAMP:
                
                break;
                
            default:
                System.out.println("This clue does not exist. ");
                break;
        }
    }
    
}
