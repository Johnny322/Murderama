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
    private Player player = new Player("Jeff", 150, 12);

    
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
                 
//            case GO:
//                goRoom("north");
//                break;
//                
//            case MOVE:
//                move(command);
//                break;
                
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
    
    private boolean processFight(NPC npc) {
        Character character = (Character) npc;
        points.updateOnAction(player.getFightSpeed());
    	player.setHp(character.getDamage());
        System.out.println("You have " + player.getHp() + " hp");
        character.changeHp(player.getDamage());
        System.out.println(character.getName() + " has " + character.getHp() + " hp");
    	if(character.getHp() <= 0) {
    		System.out.println("Enemy is dead!");
                if(npc.isEvil()) {
                    npc.setHostile(false);
                    character.setHP(100);
                } else {
                    currentRoom.currentPosition().voidNPC();
                }
    		currentRoom.printRoom();
    		return false;
    	}
    	return true;
    }
    
    private boolean fight(Command command) {
        Room room = currentRoom;
        if (room.currentPosition().getCharacter() != null) {
            NPC npc = room.currentPosition().getNPC();
            Character character = (Character) npc;
            if(!npc.isHostile()) {
                System.out.println(character.getName() + " is not hostile");
                return false;
            }
            boolean wantsToFight = processFight(npc);
            String word = "";
            while (wantsToFight) {
                Scanner scan = new Scanner(System.in);
                word = scan.next();
                if (word.toLowerCase().equals("fight")) {
                    wantsToFight = processFight(npc);
                } else if (word.toLowerCase().equals("flee")) {
                    System.out.println("You fled");
                    wantsToFight = false;
                }
            }
            return false;
        }
        System.out.println("There is no enemy here");
        return false;
    }
    

    
    private boolean loot(Room room) {
    	if(room.currentPosition().getItem() != null) {
                points.updateOnAction(player.getFightSpeed());
    		System.out.println(player.lootItem(room.currentPosition().getItem()));
                room.currentPosition().voidItem();
    	} else { System.out.println("No item here"); }
        return false;
    }
    
    private boolean use(Command command) {
        Item[] inventory = player.getInventory();
        String secondWord = command.getSecondWord();
        
        if(!command.hasSecondWord()) {
            System.out.println("Use what?");
            player.showInventory();
            return false;
        }
        for(int i = 0; i < inventory.length; i++) {
            if(inventory[i] != null) {
                if(items.getConsumable(secondWord).equals(inventory[i])) {
                    points.updateOnAction(player.getFightSpeed());
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
    

    private void goRoom(String direction) {
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else if (!currentRoom.checkIfRoomTraversalIsOkay(direction)) {
            System.out.println("Try again!");
        } else {
            points.updateOnAction(player.getWalkSpeed());
            nextRoom.setCurrentPosition(currentRoom.currentPosition(), direction);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            currentRoom.printStep(currentRoom.currentPosition());
        }
    }

    private void move(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Invalid move!");
            return;
        }

        String direction = command.getSecondWord();

        Step nextStep = currentRoom.move(direction);

        if (nextStep != null) {
            points.updateOnAction(player.getWalkSpeed());
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
            points.updateOnAction(player.getWalkSpeed());
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
        if (room.currentPosition().getNPC() != null) {
            NPC npc = room.currentPosition().getNPC();
            Character character = room.currentPosition().getCharacter();
            npc.setHostile(true);
            if (npc.isMurderer()) {
                System.out.println("You are correct, " + character.getName() + " is the murderer. ");
                System.out.println(character.getName() + " wants to fight you. ");
                fight(new Command(CommandWord.FIGHT, "character"));
                return true;
            } else {
                System.out.println("You are not correct, " + character.getName() + " is not the murderer. ");
                System.out.println(character.getName() + " wants to fight you for believing he was a murderer. ");
                fight(new Command(CommandWord.FIGHT, "character"));
                player.setLives(1);
                System.out.println("You now have " + player.getLives() + " lives left");
                return false;
            }
        }
        System.out.println("No character here");
        return false;

    }
    
    public boolean search() {
        if(currentRoom.currentPosition().getItem() != null) {
            Item item = currentRoom.currentPosition().getItem();
            if(item.isMovable()) {
                System.out.println(getItemString(item));
                return false;
            } else {
                getClue(item);
                return false;
            }
            
        }
        System.out.println("There is no item here");
        return false;
    }
    
    public boolean talk() {
        if(currentRoom.currentPosition().getNPC() == null) {
            System.out.println("There is no one to talk to");
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
                player.setWalkSpeed(2);
                player.setFightSpeed(2);
                player.setSearchSpeed(2);
                System.out.println("You feel slower. ");
                break;
                
            case POTION:
                player.setHp(-25);
                System.out.println("Potion has been used. ");
                System.out.println("Your current hp has been increased by 25 to " + player.getHp());
                break;
                
            case COFFEE:
                if(player.getWalkSpeed() - 2 < 5) {
                    player.setWalkSpeed(0);
                } else {
                    player.setWalkSpeed(-2);
                }
                
                if(player.getSearchSpeed() - 3 < 10) {
                    player.setSearchSpeed(0);
                } else {
                    player.setSearchSpeed(-3);
                }
                
                if(player.getFightSpeed() - 1 < 0) {
                    player.setFightSpeed(0);
                } else {
                    player.setFightSpeed(-1);
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
                } else {
                    System.out.println("The key doesn't fit in any doors in this area.");
                }
                break;

                
            default:
                System.out.println("This item does not exist. ");
                break;
        }
    }
    
    private String getItemString(Item item) {
        switch((Consumable) item) {
            case BEER:
                return "Makes you slower, but more likable";
                
            case POTION:
                return "Heals you";
                
            case COFFEE:
                return "Makes you faster";
                
            default:
                return "This item does not exist";
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
                player.setWalkSpeed(-2);
                break;
                
            case FINGERPRINT:
                player.setWalkSpeed(-2);
                break;
                
            case FOOTPRINT:
                player.setWalkSpeed(-2);
                break;
                
            case CLOTHES:
                player.setWalkSpeed(-2);
                break;
                
            case LAMP:
                
                break;
                
            default:
                System.out.println("This clue does not exist. ");
                break;
        }
    }   
}