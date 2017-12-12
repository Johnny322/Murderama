package worldofzuul2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game implements Serializable {
    private final Parser parser;
    private Room currentRoom;
    private final Consumables items = new Consumables();
    private final PointSystem points = new PointSystem();
    private final Notes notes = new Notes();
    private Room outside, cafeteria, U55, basement, library, hallway, TEK, U183, projectRoom;
    private final Player player = new Player("Jeff", 150, 12);
    private boolean hasWon = false;
    
    

    /**
     *
     */
    public Game() {
        printHighscore();
        createRooms();
        parser = new Parser();
    }
    public void SaveData() {

        File file = new File("src/worldofzuul2/data.ser");
        try {
            Scanner scanner = new Scanner(file);

            PrintStream writer = new PrintStream(file);
            writer.println(currentRoom.getPosition() + "\n");  //1
            writer.println(player.getLives() + "\n"); //2
            writer.println(10000 - points.getPoints() + "\n"); //3

            writer.println(player.getFightSpeed() + "\n"); //4
            writer.println(player.getWalkSpeed() + "\n"); //5
            writer.println(player.getHp() + "\n"); //6
            writer.println(player.getSearchSpeed() + "\n"); //7

            if (currentRoom == U55) {
                writer.println("U55" + "\n"); //8
            } else if (currentRoom == outside) {
                writer.println("outside" + "\n"); //8
            } else if (currentRoom == cafeteria) {
                writer.println("cafeteria" + "\n"); //8
            } else if (currentRoom == basement) {
                writer.println("basement" + "\n"); //8
            } else if (currentRoom == library) {
                writer.println("library" + "\n"); //8
            } else if (currentRoom == hallway) {
                writer.println("hallway" + "\n"); //8
            } else if (currentRoom == TEK) {
                writer.println("TEK" + "\n"); //8
            } else if (currentRoom == U183) {
                writer.println("U183" + "\n"); //8
            } else if (currentRoom == projectRoom) {
                writer.println("projectRoom" + "\n"); //8
            }

            Item[] tempInventory = player.getInventory();
            for (int i = 0; i < tempInventory.length; i++) {
                if (tempInventory[i] != null) {
                    writer.print(tempInventory[i].getName() + " ");//9
                }
            }
            writer.print(" qqq \n");

            //Save Funktionen
            ArrayList<String> tempNotes = notes.getNotes();
            for (String s : tempNotes) {
                writer.println(s + " " + "xxx"); //10

            }
            writer.print(" www");

            scanner.close();
            System.out.println("Spillet er nu saved");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LoadData() {
        File file = new File("src/worldofzuul2/data.ser");
        try {
            Scanner scanner = new Scanner(file);

            currentRoom.setCurrentPosition1(scanner.nextInt()); //1
            currentRoom.setCurrentPosition2(scanner.nextInt());//1

            player.setLives(scanner.nextInt()); //2 

            points.setPoints(scanner.nextInt()); //3
            player.setFightSpeed(scanner.nextInt()); //4
            player.setWalkSpeed(scanner.nextInt()); //5
            player.setCurrentHp(scanner.nextInt()); //6
            player.setSearchSpeed(scanner.nextInt()); //7

            String roomName = scanner.next();
            switch (roomName) {
                case "U55":
                    currentRoom = U55;
                    break;
                case "outside":
                    currentRoom = outside;
                    break;

                case "cafeteria":
                    currentRoom = cafeteria;
                    break;

                case "basement":
                    currentRoom = basement;
                    break;

                case "library":
                    currentRoom = library;
                    break;

                case "hallway":
                    currentRoom = hallway;
                    break;

                case "TEK":
                    currentRoom = TEK;
                    break;

                case "U183":
                    currentRoom = U183;
                    break;

                case "projectRoom":
                    currentRoom = projectRoom;
                    break;

            }

            String setInventory = scanner.next();
            while (!"qqq".equals(setInventory) && scanner.hasNext()) {
                switch (setInventory) {
                    case "potion":
                        player.addInventory(Consumable.POTION);
                        break;
                    case "coffee":
                        player.addInventory(Consumable.COFFEE);
                        break;
                    case "beer":
                        player.addInventory(Consumable.BEER);
                        break;
                    case "key":
                        player.addInventory(Consumable.KEY);
                        break;
                    //case " ": continue;
                    default:
                        break;
                }
                setInventory = scanner.next();

            }

            String s = "";
            String temp = scanner.next();
            while (!"www".equals(temp)) {
                while (!"xxx".equals(temp)) {
                    s = s + " " + temp;
                    temp = scanner.next();

                }
                notes.addNote(s);
                s = "";
                temp = scanner.next();
            }

            System.out.println("Dine gemte notes: \n" + notes.showNotes());
            System.out.println("Din score er: " + points.getPoints());
            System.out.println("Du har " + player.getHp() + " Hp");
            System.out.println("Din walk-speed er: " + player.getWalkSpeed());
            System.out.println("Din fight-speed er: " + player.getFightSpeed());
            System.out.println(" ");
            System.out.println("You're currently at " + roomName);
            currentRoom.printRoom();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void createRooms() {

        outside = new Room("outside the main entrance of the university");
        outside.buildOutside();
        cafeteria = new Room("in the cafeteria");
        cafeteria.buildCafeteria();
        U55 = new Room("in lecture room U55");
        U55.buildU55();
        basement = new Room("in the basement of the university");
        hallway = new Room("in the long hallway in front of room U55");
        hallway.buildHallway();
        library = new Room(" in the university's library");
        library.buildLibrary();
        TEK = new Room("in front of the bronze stairs at TEK");
        TEK.buildTEK();
        U183 = new Room("inside room U183"); 
        U183.buildU183();
        projectRoom = new Room("inside a project room at TEK");
        projectRoom.buildProjectRoom();
        

        outside.setExit("west", TEK);
        outside.setExit("south", projectRoom);
        projectRoom.setExit("north", outside);

        hallway.setExit("east", U55);
        hallway.setExit("south", cafeteria);
        hallway.setExit("north", library);
        outside.setExit("east", hallway);
        U55.setExit("east", hallway);
        TEK.setExit("east", outside);
        TEK.setExit("north", U183);
        U183.setExit("south", TEK);
        cafeteria.setExit("north", hallway);
        library.setExit("south", hallway);
        hallway.setExit("west", outside);

        currentRoom = outside;
    }
    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Your score was: " + points.getPoints());
        updateHighscore(points.getPoints());
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        currentRoom.printRoom();
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN || commandWord == CommandWord.FLEE) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        switch (commandWord) {
            case HELP:
                printHelp();
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
                move(commandWord);
                break;

            case A:
                move(commandWord);
                break;

            case S:
                move(commandWord);
                break;

            case D:
                move(commandWord);
                break;

            case SHOWNOTES:
                return notes.showNotes();
            
            case SAVE:
                SaveData(); 
                break;
            
            case LOAD:
                LoadData();   
                break;

            default:
                return wantToQuit;
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    
    private boolean processFight(NPC npc) {
        Character character = (Character) npc;
        points.updateOnAction(player.getFightSpeed());
        player.increaseLikeability(player.getFightSpeed());
        player.setHp(character.getDamage());
        System.out.println("You have " + player.getHp() + " hp");
        character.changeHp(player.getDamage());
        System.out.println(character.getName() + " has " + character.getHp() + " hp");
        if (character.getHp() <= 0) {
            System.out.println("Enemy is dead!");
            if (npc.isEvil()) {
                npc.setHostile(false);
                character.setHP(100);
            } else if(npc.isMurderer()) {
                hasWon = true;
            } else {
                currentRoom.currentPosition().voidNPC();
            }
            currentRoom.printRoom();
            return false;
        }
        if (player.getHp() <= 0) {
            System.out.println("You got knocked out");
            player.setLives(1);
            System.out.println("You now have " + player.getLives() + " live(s left");
            player.setHP(150);
        } if (player.getLives() <= 0){
            System.out.println("You are dead");
            return false;
        }
        return true;
    }
    
    private boolean fight(Command command) {
        Room room = currentRoom;
        if (room.currentPosition().getCharacter() != null) {
            NPC npc = room.currentPosition().getNPC();
            Character character = (Character) npc;
            if (!npc.isHostile()) {
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
        if (room.currentPosition().getItem() != null) {
            points.updateOnAction(player.getFightSpeed());
            player.increaseLikeability(player.getFightSpeed());
            System.out.println(player.lootItem(room.currentPosition().getItem()));
            room.currentPosition().voidItem();
        } else {
            System.out.println("No item here");
        }
        return false;
    }

    private boolean use(Command command) {
        Item[] inventory = player.getInventory();
        String secondWord = command.getSecondWord();

        if (!command.hasSecondWord()) {
            System.out.println("Use what?");
            player.showInventory();
            return false;
        }
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                if (items.getConsumable(secondWord).equals(inventory[i])) {
                    points.updateOnAction(player.getFightSpeed());
                    player.increaseLikeability(player.getFightSpeed());
                    getItemEffect(items.getConsumable(secondWord));
                    System.out.println("Used " + inventory[i].getName());
                    if(!secondWord.equals("key")) {
                    	inventory[i] = null;
                    }
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
            player.increaseLikeability((player.getWalkSpeed()));
            nextRoom.setCurrentPosition(currentRoom.currentPosition(), direction);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            currentRoom.printStep(currentRoom.currentPosition());
        }
    }

    private void move(CommandWord commandword) {
        String direction = "";
        switch(commandword) {
            case W:
                direction = "north";
                break;
                
            case A:
                direction = "west";
                break;
                
            case S:
                direction = "south";
                break;
                
            case D:
                direction = "east";
                break;
        }
        Step nextStep = currentRoom.move(direction);

        if (nextStep != null) {
            points.updateOnAction(player.getWalkSpeed());
            currentRoom.printStep(nextStep);
        } else {
            goRoom(direction);
        } 
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    private boolean accuse(Room room) {
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
                if (player.getLives() <= 0) {
                    System.out.println("you lost, you accused to many people wrongly");
                    return true;
                }
                System.out.println("You now have " + player.getLives() + " lives left");
                player.increaseLikeability(-100);
                System.out.println("People will like you less because of your mistake.");
                return false;
            }
        }
        System.out.println("No character here");
        return false;

    }

    // Vi havde vidst slet ikke brugt SearchSpeed til at tælle tiden op. Jeg har tilføjet den her, men kun når det faktisk er en clue, der searches. /P
    public boolean search() {
        if (currentRoom.currentPosition().getItem() != null) {
            Item item = currentRoom.currentPosition().getItem();
            if (item.isMovable()) {
                System.out.println(getItemString(item));
                return false;
            } else {
                getClue(item);
                points.updateOnAction(player.getSearchSpeed());
                player.increaseLikeability(player.getSearchSpeed());
                return false;
            }

        }
        System.out.println("There is no item here");
        return false;
    }

    // Tiden tælles vidst ikke op, når man snakker med folk. Bør dette ændres? /P
    public boolean talk() {
        if (currentRoom.currentPosition().getNPC() == null) {
            System.out.println("There is no one to talk to");
            return false;
        }
        String information = currentRoom.currentPosition().getNPC().getInformation();
        if (information == null) {
            return false;
        }
        if (player.getLikeability() < currentRoom.currentPosition().getNPC().getThreshold()) {
            System.out.println("This person does not trust you yet.");
            return false;
        }
        System.out.println(information);
        player.increaseLikeability(20);
        boolean hasNote = false;
        if (!currentRoom.currentPosition().getNPC().friendly()) {
        	for (String note : notes.getNotes()) {
        		if (note.equals(currentRoom.currentPosition().getCharacter().getName() + " said: " + information)) {
        			hasNote = true;
        		}
        	} 
        }
        if (!hasNote) {
            notes.addNote(currentRoom.currentPosition().getCharacter().getName(), information);
        }
        Scanner scan = new Scanner(System.in);
        String word = scan.next();
        if (word.toLowerCase().equals("talk")) {
            System.out.println(currentRoom.currentPosition().getNPC().getAdditionalInformation());
        }
        return false;
    }

    private void getItemEffect(Item item) {
        switch ((Consumable) item) {
            case BEER:
                player.setWalkSpeed(2);
                player.setFightSpeed(2);
                player.setSearchSpeed(2);
                player.increaseLikeability(25);
                System.out.println("You feel slower, but more talkative. ");
                break;

            case POTION:
                player.setHp(-25);
                System.out.println("Potion has been used. ");
                System.out.println("Your current hp has been increased by 25 to " + player.getHp());
                break;

            case COFFEE:
                if (player.getWalkSpeed() - 2 < 5) {
                    player.setWalkSpeed(0);
                } else {
                    player.setWalkSpeed(-2);
                }

                if (player.getSearchSpeed() - 3 < 10) {
                    player.setSearchSpeed(0);
                } else {
                    player.setSearchSpeed(-3);
                }

                if (player.getFightSpeed() - 1 < 0) {
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
        switch ((Consumable) item) {
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
        switch ((Clue) item) {
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
