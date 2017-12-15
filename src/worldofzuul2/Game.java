package worldofzuul2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for handling most of the things that happen in the game.
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game {
    
     /**
     * Various attributes for describing the current situation in the game.
     */
    private Room currentRoom;
    private final Consumables items = new Consumables();
    private final PointSystem points = new PointSystem();
    private final Notes notes = new Notes();
    private Room outside, cafeteria, U55, basement, library, hallway, TEK, U183, projectRoom, nedenunder;
    private final Player player = new Player("Jeff", 150, 12);
    private boolean hasWon = false;
    private boolean hasLost = false;
    private final CommandWords commands = new CommandWords();
    private String information;
    private int murdererAccused = 0;
    
    

    /**
     * This is the method that is called when the game is played. It begins the
     * game itself.
     */
    public Game() {
        printHighscore();
        createRooms();
        setInformation(printWelcome());
    }
    
    /**
     * This is where the Rooms are built. Constructors for every room 
     * used in the story will be called here, and the appropriate exits will be set
     * for each of the rooms. Since the player starts outside, the current room 
     * is set to outside by default.
     */
    private void createRooms() {

        outside = new Room("outside the main entrance of the university.");
        outside.buildOutside();
        cafeteria = new Room("in the cafeteria. You can always get coffee here.");
        cafeteria.buildCafeteria();
        U55 = new Room("in lecture room U55.");
        U55.buildU55();
        hallway = new Room("in the long hallway in front of room U55.");
        hallway.buildHallway();
        library = new Room("in the university's library.");
        library.buildLibrary();
        TEK = new Room("in front of the bronze stairs at TEK.");
        TEK.buildTEK();
        U183 = new Room("inside room U183."); 
        U183.buildU183();
        projectRoom = new Room("inside a project room at TEK.");
        projectRoom.buildProjectRoom();
        nedenunder = new Room("in the bar of the university. You can always get beer here.");
        nedenunder.buildNedenunder();
        

        outside.setExit("west", TEK);
        outside.setExit("south", projectRoom);
        projectRoom.setExit("north", outside);

        hallway.setExit("east", U55);
        hallway.setExit("south", cafeteria);
        hallway.setExit("north", library);
        outside.setExit("east", hallway);
        U55.setExit("west", hallway);
        TEK.setExit("east", outside);
        TEK.setExit("north", U183);
        U183.setExit("south", TEK);
        cafeteria.setExit("north", hallway);
        library.setExit("south", hallway);
        hallway.setExit("west", outside);
        nedenunder.setExit("north", U55);
        U55.setExit("south", nedenunder);

        currentRoom = outside;
    }
    
    /**
     * Prints a welcome-message for the player, and shows the room for the first time.
     * @return a String that is printed at the start of the game.
     */
    private String printWelcome() {
        return "Welcome to Murderama \n This is a game where nothing happens by chance, everything is a mystery and nobody is trustworthy. Do you dare to play?"
                + " \n Someone has been murdered! Find the murderer and make him pay for what he did! \n "
                + printHelp();
    }
    
    /**
     * Method to convert String from GUI to command, which is usable in the processCommand-method
     * @param s String parsed from GUI
     * @return boolean
     */
    public boolean convertToCommand(String s) {
        String word1 = null;
        String word2 = null;
        try (Scanner tokenizer = new Scanner(s)) {
            if(tokenizer.hasNext()) {
                word1 = tokenizer.next();
                if(tokenizer.hasNext()) {
                    word2 = tokenizer.next();
                }
            }
        }
        
        Command command = new Command(commands.getCommandWord(word1), word2);
        return processCommand(command);
    }

    /**
     * This switch-case is used to determine what the user wants to do next.
     * @param command - the command given by the user.
     * @return the appropriate method call for the given command.
     */
    private boolean processCommand(Command command) {
        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            setInformation("I don't know what you mean...");
            return false;
        }
        switch (commandWord) {
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
                setInformation(notes.showNotes());
                return false;
            
            case SAVE:
                SaveData(); 
                break;
            
            case LOAD:
                LoadData();   
                break;
                
            case HELP:
                setInformation(printHelp());
                break;
                
            default:
                return false;
        }
        return false;
    }

    /**
     * Prints out a helping message for the player. Is called when the player uses 
     * the help-command.
     */
    private String printHelp() {
        return "\nYou walk around by pressing the W, A, S and D buttons to the left. Below you will find buttons for various actions. To the left is a map of the current room, which shows you the following things:" +
"                \nX is where you are currently at\nO indicates an empty field\nC indicates a character\nI indicates an item\nd marks the available doors\n\nHave fun!";
    }
    
    /**
     * Processes a fight between the player and a NPC, called when the fight-command is used. 
     * Handles changing the hp when a character is damaged, and removing a character from the 
     * game if it is killed. If the player defeats the murderer, the hasWon-boolean is set to 
     * true, resulting in the player winning the game.
     * @param npc - the non-player character that the player is fighting.
     * @return a boolean that indicates whether the fight is over.
     */
    private boolean processFight(NPC npc) {
        Character character = (Character) npc;
        points.updateOnAction(player.getFightSpeed());
        player.increaseLikeability(player.getFightSpeed());
        player.setHp(character.getDamage());
        character.changeHp(player.getDamage());
        setInformation("You have " + player.getHp() + " hp" + "\n" + character.getName() + " has " + character.getHp() + " hp");
        if (character.getHp() <= 0) {
            if(npc.isMurderer() && murdererAccused >= 1) {
                setInformation(getInformation() + "\nYou beat the murderer! You have won the game!");
                hasWon = true;
                return true;
            }
            setInformation(getInformation() + "\n" + "Enemy has been defeated!");
            if (npc.isEvil() && murdererAccused < 1) {
                npc.setHostile(false);
                character.setHP(100);
                if (npc.isMurderer()) {
                    murdererAccused++;
                }
            } else {
                currentRoom.currentPosition().voidNPC();
            }
            return false;
        }
        if (player.getHp() <= 0) {
            setInformation(getInformation() + "\n" + "You got knocked out.");
            player.setLives(1);
            if (player.getLives() <= 0){
            setInformation(getInformation() + "\n" + "You have no more lives left! You have lost the game!");
            hasLost = true;
            return false;
            }
            if (player.getLives() == 1) {
                setInformation(getInformation() + "\n" + "You now have " + player.getLives() + " life left");
            }
            else {
                setInformation(getInformation() + "\n" + "You now have " + player.getLives() + " lives left");
            }
            player.setHP(150);
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
                setInformation(character.getName() + " is not hostile.");
                return false;
            }
            processFight(npc);
            return false;
        }
        setInformation("There is no enemy here.");
        return false;
    }

    /**
     * Adds an item to the inventory when a player uses the loot-command, and 
     * removes the item from the room. 
     * @param room - the room that the player is currently in.
     * @return a boolean which is always false.
     */
    private boolean loot(Room room) {
        if (room.currentPosition().getItem() != null && room.currentPosition().getItem().isMovable()) {
            points.updateOnAction(player.getFightSpeed());
            player.increaseLikeability(player.getFightSpeed());
            setInformation(player.lootItem(room.currentPosition().getItem()));
            if (room.currentPosition().getItem() != Consumable.COFFEE && room.currentPosition().getItem() != Consumable.BEER) {
                room.currentPosition().voidItem();
            }
        }
        else if (room.currentPosition().getItem() != null) {
            setInformation("This item cannot be picked up.");
        }
        else {
            setInformation("There is no item here.");
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
            setInformation("Use what? Your inventory contains:");
            setInformation(getInformation() + "\n" + player.showInventory());
            return false;
        }
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null) {
                if (items.getConsumable(secondWord).equals(inventory[i])) {
                    points.updateOnAction(player.getFightSpeed());
                    player.increaseLikeability(player.getFightSpeed());
                    getItemEffect(items.getConsumable(secondWord));
                    if(!secondWord.equals("key")) {
                    	inventory[i] = null;
                    }
                    return false;
                }
            }

        }
        setInformation("There is no " + secondWord + " in your inventory. ");
        return false;
    }

    /**
     * This method is used to move the player between rooms. It is called when it 
     * has been determined that the player is not trying to move to a new step.
     * @param direction - the direction in which the player wants to move.
     */
    private void goRoom(String direction) {
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom != null && currentRoom.checkIfRoomTraversalIsOkay(direction)) {
            points.updateOnAction(player.getWalkSpeed());
            player.increaseLikeability((player.getWalkSpeed()));
            nextRoom.setCurrentPosition(currentRoom.currentPosition(), direction);
            currentRoom = nextRoom;
            setInformation(getInformation() + "You are " + currentRoom.getShortDescription());
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
        } else {
            goRoom(direction);
        } 
        if(currentRoom.currentPosition().getNPC() != null) {
            setInformation("NPC: " + currentRoom.currentPosition().getCharacter().getName() + ".");
            if(currentRoom.currentPosition().getNPC().friendly()) {
                talk();
            }
//        } else if (currentRoom.currentPosition().getNPC()!= null) {
//            setInformation("Monster");
        } else if(currentRoom.currentPosition().getItem() != null ) {
            setInformation("Item: " + currentRoom.currentPosition().getItem().getName() + ".");
        } else {
            setInformation("You are " + currentRoom.getShortDescription());
        }
    }

    /**
     * Method to parse String of room from class Room to GUI. 
     * @return String for printing in GUI
     */
    public String getRoom() {
        return currentRoom.printRoom();
    }

    /**
     * Used when the player gives the accuse-command. It handles checking whether 
     * the player is correct, starting a fight with the accused character, and 
     * changing the amount of remaining attempts. 
     * @param room - the room that the player is currently in.
     * @return a boolean that is true if the game is over - either won or lost.
     */
    private boolean accuse(Room room) {
        if (room.currentPosition().getNPC() != null) {
            NPC npc = room.currentPosition().getNPC();
            Character character = room.currentPosition().getCharacter();
            npc.setHostile(true);
            if (npc.isMurderer()) {
                setInformation("You are correct, " + character.getName() + " is the murderer.");
                setInformation(getInformation() + "\n" + character.getName() + " wants to fight you. Defeat him to win the game!");
                return false;
            }
            player.setLives(1);
            if (player.getLives() <= 0) {
                setInformation("You accused the wrong person! You have lost the game!");
                hasLost = true;
                return true;
            }
            setInformation("You are incorrect, " + character.getName() + " is not the murderer. ");
            if (player.getLives() == 1) {
                setInformation(getInformation() + "\n" + "You now have " + player.getLives() + " life left.");
            } else {
                setInformation(getInformation() + "\n" + "You now have " + player.getLives() + " lives left.");
            }
            player.increaseLikeability(-100);
            setInformation(getInformation() + "\n" + "People will like you less because of your mistake.");
            setInformation(getInformation() + "\n" + character.getName() + " wants to fight you for believing he was a murderer. ");
            return false;
        }
        setInformation("Nobody is here. ");
        return false;
    }

    /**
     * This method is called when the player uses the Inspect-command.
     * @return a boolean which is always false.
     */
    public boolean search() {
        if (currentRoom.currentPosition().getItem() != null) {
            Item item = currentRoom.currentPosition().getItem();
            if (item.isMovable()) {
                setInformation(getItemString(item));
                return false;
            } else {
                getClue(item);
                points.updateOnAction(player.getSearchSpeed());
                player.increaseLikeability(player.getSearchSpeed());
                return false;
            }

        }
        
        if(currentRoom.currentPosition().getNPC() != null) {
            setInformation(currentRoom.currentPosition().getNPC().getDescription());
            return false;
        }
        setInformation("There is nothing here.");
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
            setInformation("There is no one to talk to. ");
            return false;
        }
        if (currentRoom.currentPosition().getNPC().isHostile()) {
            setInformation(currentRoom.currentPosition().getCharacter().getName() + " is hostile and cannot be talked to until you defeat them in combat.");
            return false;
        }
        if (player.getLikeability() < currentRoom.currentPosition().getNPC().getThreshold()) {
            setInformation(getInformation() + "\n This person does not trust you yet.");
            return false;
        }
        String s = currentRoom.currentPosition().getNPC().getInformation();
        setInformation(currentRoom.currentPosition().getCharacter().getName() + " says: " + s);
        points.updateOnAction(player.getWalkSpeed());
        player.increaseLikeability(20);
        boolean hasNote = false;
        for (String note : notes.getNotes()) {
            if (note.equals(currentRoom.currentPosition().getCharacter().getName() + " said: " + s)) {
        		hasNote = true;
        	}
        } 
        if (!hasNote) {
            notes.addNote(currentRoom.currentPosition().getCharacter().getName(), s);
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
                setInformation(getInformation() + "\nYou feel slower, but more talkative. ");
                break;

            case POTION:
                player.setHp(-25);
                setInformation("Potion has been used. ");
                setInformation(getInformation() + "\nYour current hp has been increased by 25. Your hp is now " + player.getHp() + ".");
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
                setInformation(getInformation() + "\nYou feel energized! ");
                break;

            case KEY:
                if (currentRoom == outside || currentRoom == library) {
                    basement = new Room("in the basement of the university.");
                    basement.buildBasement();
                    library.setExit("west", basement);
                    basement.setExit("east", library);
                    basement.setExit("south", outside);
                    outside.setExit("north", basement);
                    setInformation("The key has opened a door to the basement!"); //denne bliver ikke vist
                } else {
                    setInformation("The key doesn't fit in any doors in this area."); //denne er også et problem
                }
                break;
                
            case KNIFE:
                player.changeDamage(3);
                setInformation("You will now use this knife as a weapon! You hit harder.");
                break;

            default:
                setInformation("This item does not exist. ");
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
                return "Makes you slower, but more likeable.";

            case POTION:
                return "Heals you.";

            case COFFEE:
                return "Makes you faster.";
                
            case KEY:
                return "Can open hidden doors.";
                
            case KNIFE:
                return "Can make you hit harder.";

            default:
                return "This item does not exist.";
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
                setInformation("This is the victim. He seems to have been strangled by someone with small hands.");
                break;

            case FINGERPRINT:
                setInformation("Small bloody fingerprints.");
                break;

            case FOOTPRINT:
                setInformation("Large footprints.");
                break;

            default:
                break;
        }
    }
    
    /**
     * This method is used for saving a current game. It writes several
     * important attributes to data.ser, so these can later be loaded back in to
     * the game, in case the player wants to continue from this point in the
     * game.
     */
    public void SaveData() {

        File file = new File("/Murderama-master/src/Data/data.txt");
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
            setInformation("Game has been saved");
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
        File file = new File("Murderama-master/src/Data/data.txt");
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
            
            s = "";
            s = s + "Notes: \n" + notes.showNotes() + "\n";
            s = s + "Your score is: " + (points.getPoints()) + "\n";
            s = s +  "You currently have " + player.getHp() + " Hp" + "\n";
            s = s + "Your walking speed is currently: " + player.getWalkSpeed() + "\n";
            s = s + "Your fighting speed is currently: " + player.getFightSpeed() + "\n";
            s = s + " " + "\n";
            s = s + "You're currently at " + roomName + "\n";
            setInformation(s);

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
    public void printHighscore() {
        File file = new File("Murderama-master/src/Data/Highscore.txt");
        try {
            Scanner scanner = new Scanner(file);
            String s = "Current highscore is: " + scanner.nextInt();
            setInformation(s);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    /**
     * The method responsible for updating the highscore whenever it is
     * necessary. It is called at the end of the game, and it checks whether the
     * players current score is higher than the one saved in Highscore.txt. If
     * it is, then the current score is saved as the new highscore.
     * @return String to print from GUI.
     */
    public String updateHighscore() {
        File file = new File("Murderama-master/src/Data/Highscore.txt");
        String s = "";
        int currentScore = points.getPoints();
        try {
            Scanner scanner = new Scanner(file);
            int currentHighscore = scanner.nextInt();
            if(currentScore > currentHighscore) {
                PrintStream writer = new PrintStream(file);
                writer.println(Integer.toString(currentScore));
                s = s + "You now have the current highscore with: " + currentScore;
            } else {
                s = s + "You dont have the highest score. ";
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    /**
     * The method to get information from the game-class to the GUI.
     * @return String to pass to GUI.
     */
    public String getInformation() {
        return information;
    }
    
    /**
     * The method to set information from the game-class to the GUI.
     * @param s parsed information from class Game.
     */
    public void setInformation(String s) {
        information = s;
    }
    
    /**
     * Method to check if the player has won.
     * @return boolean, true if the player has defeated the murderer.
     */
    public boolean hasWon() {
        return hasWon;
    }
    
    /**
     * Method to check if the player has lost.
     * @return boolean, true if the player has lost all three lives.
     */
    public boolean hasLost() {
        return hasLost;
    }

}