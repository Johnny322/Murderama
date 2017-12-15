package worldofzuul2;

import java.util.HashMap;

/**
 * Class for the commandwords.
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class CommandWords
{
    /**
     * Hashmap for containing the commands.
     */
    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor for CommandWords.
     */
    public CommandWords() {
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Accessor method based on a String commandWord.
     * @param commandWord - String used to find the wanted commandWord.
     * @return the String CommandWord.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Checks if the String is a Command.
     * @param aString - the given command as a string.
     * @return true if the Command is present in validCommands.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * Shows all valid Commands.
     * @return String of all valid commands
     */
    public String showAll() 
    {
        String s = "";
        for(String command : validCommands.keySet()) {
            s = s + command + ",  ";
        }
        return s;
    }
}
