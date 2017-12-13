package worldofzuul2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains most of the functionality in the game.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game {

    /**
     * Various attributes for describing the current situation in the game.
     */
    private final Parser parser;
    private Room currentRoom;
    private final Consumables items = new Consumables();
    private final PointSystem points = new PointSystem();
    private final Notes notes = new Notes();
    private Room outside, cafeteria, U55, basement, library, hallway, TEK, U183, projectRoom;
    private final Player player = new Player("Jeff", 150, 12);
    private boolean hasWon = false;

    /**
     * This is the method that is called when the game is played. It begins the
     * game itself.
     */
    public Game() {
        printHighscore();
        createRooms();
        parser = new Parser();
    }

    /**
     * This method is used for saving a current game. It writes several
     * important attributes to data.ser, so these can later be loaded back in to
     * the game, in case the player wants to continue from this point in the
     * game.
     */
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

            ArrayList<String> tempNotes = notes.getNotes();
            for (String s : tempNotes) {
                writer.println(s + " "); //10
            }
            writer.println(" www");

            scanner.close();
            System.out.println("Spillet er nu saved");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method loads the game data that has been saved by SaveData. It reads
     * the values from data.ser, and uses these to set the appropriate
     * attributes to the correct values.
     */
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
                if (scanner.hasNext()) {
                    s = s + " " + temp;
                    temp = scanner.next();

                } else {
                    break;
                }
            }
            notes.addNote(s); //10

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

    /**
     * This method is used to read a value representing a highscore from
     * Highscore.txt, and to print this, so the player knows what the current
     * highscore is before the game begins. It is called whenever the game is
     * started.
     */
    public static void printHighscore() {
        File file = new File("src/worldofzuul2/Highscore.txt");
        try {
            Scanner scanner = new Scanner(file);
            System.out.println("Current highscore is: " + scanner.nextInt());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * The method responsible for updating the highscore whenever it is
     * necessary. It is called at the end of the game, and it checks whether the
     * players current score is higher than the one saved in Highscore.txt. If
     * it is, then the current score is saved as the new highscore.
     *
     * @param currentScore - the players current score.
     */
    public void updateHighscore(int currentScore) {
        File file = new File("src/worldofzuul2/Highscore.txt");
        try {
            Scanner scanner = new Scanner(file);
            int currentHighscore = scanner.nextInt();
            if (currentScore > currentHighscore) {
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

    /**
     * This is where the Rooms are built. Constructors for every room 
     * used in the story will be called here, and the appropriate exits will be set
     * for each of the rooms. Since the player starts outside, the current room 
     * is set to outside by default.
     */
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

    /**
     * This method is used to actually play the game. It is a loop that keeps running 
     * for as long as the game is not finished, and keeps processing the players commands.
     * Additionally, it prints messages to the player at the beginning and end of the game, 
     * and manages the highscore.
     */
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

    /**
     * Prints a welcome-message for the player, and shows the room for the first time.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to Murderama!");
        System.out.println("This is a game where nothing happens by chance, everything is a mystery and nobody is trustworthy. Do you dare to play?
.");
        System.out.println("Someone has been murdered! Find the murderer and make him pay for what he did!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        currentRoom.printRoom();
    }

    /**
     * This switch-case is used to determine what the user wants to do next.
     * @param command - the command given by the user.
     * @return the appropriate method call for the given command.
     */
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
                return fight();

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

    /**
     * Prints out a helping message for the player. Is called when the player uses 
     * the help-command.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. Be aware");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Processes a fight between the player and a NPC, called when the fight-command is used. 
     * Handles changing the hp when a character is damaged, and removing a character from the 
     * game if it is killed. If the player defeats the murderer, the hasWon-boolean is set to 
     * true, resulting in the player winning the game.
     * @param npc - the non-player character that the player is fighting.
     * @return a boolean that represents whether or not the fight is over.
     */
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
            } else if (npc.isMurderer()) {
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
        }
        if (player.getLives() <= 0) {
            System.out.println("You are dead");
            return false;
        }
        return true;
    }

    /**
     * Starts a fight with a character. This is called when the player gives the 
     * fight-command. This method also handles the flee-option.
     * @return a boolean which is always false. This saves code by removing the 
     * need for else-statements.
     */
    private boolean fight() {
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

    /**
     * Adds an item to the inventory when a player uses the loot-command, and 
     * removes the item from the room. 
     * @param room - the room that the player is currently in.
     * @return a boolean which is always false.
     */
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

    /**
     * Handles using an item, which involves getting the effect of the specified item 
     * and applying it, and then removing the item from the inventory.
     * @param command - the command given by the player, indicating which item the 
     * player wants to use.
     * @return a boolean which is always false.
     */
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
                    if (!secondWord.equals("key")) {
                        inventory[i] = null;
                    }
                    return false;
                }
            }

        }
        System.out.println("There is no " + secondWord + " in your inventory. ");
        return false;
    }

    /**
     * This method is used to move the player between rooms. It is called when it 
     * has been determined that the player is not trying to move to a new step.
     * @param direction - the direction in which the player wants to move.
     */
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

    /**
     * Whenever the player wants to move, this method is called. A  
     * switch-case determines which of the possible directions the player 
     * wants to move, and then checks whether the player is trying to move to 
     * another step, or something else - possibly a new room.
     * @param commandword - command given by the player, which indicates the
     * direction in which the player wants to move.
     */
    private void move(CommandWord commandword) {
        String direction = "";
        switch (commandword) {
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

    /**
     * This method is called when the player uses the quit-command. It checks 
     * whether there is a second word in the command, to make sure that it 
     * was not a mistake.
     * @param command - the command given by the player.
     * @return a boolean indicating whether or not the player actually wants to quit.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Used when the player gives the accuse-command. It handles checking whether 
     * the player is correct, starting a fight with the accused character, and 
     * changing the amount of remaining attempts. Additionally, this method is 
     * used to check if the game is over.
     * @param room - the room that the player is currently in.
     * @return a boolean that is true if the game is over - either won or lost.
     */
    private boolean accuse(Room room) {
        if (room.currentPosition().getNPC() != null) {
            NPC npc = room.currentPosition().getNPC();
            Character character = room.currentPosition().getCharacter();
            npc.setHostile(true);
            if (npc.isMurderer()) {
                System.out.println("You are correct, " + character.getName() + " is the murderer. ");
                System.out.println(character.getName() + " wants to fight you. ");
                fight();
                return true;
            } else {
                System.out.println("You are not correct, " + character.getName() + " is not the murderer. ");
                System.out.println(character.getName() + " wants to fight you for believing he was a murderer. ");
                fight();
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
    
    /**
     * This method is called when the player uses the search-command.
     * @return a boolean which is always false.
     */
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
    
    /**
     * Called when the player talks to another character. Handles giving the player 
     * the correct information from the character, and automatically adding this to 
     * the list of notes. Also checks whether the character trusts the player enough 
     * to talk to them.
     * @return a boolean that is always false.
     */
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

    /**
     * Executes the changes that happen whenever the player uses an item.
     * @param item - the item the player wants to use.
     */
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

    /**
     * Describes what a consumable item does. This method is called when the player wants to 
     * inspect an item closer.
     * @param item - the item that is currently being handled.
     * @return a description of the item
     */
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

    /**
     * Accessor for a clue that the player can inspect. A switch-case is used to identify
     * which of the possible clues is currently being handled.
     * @param item - the item that the player is currently looking at.
     * @return the information of the appropriate clue.
     */
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
