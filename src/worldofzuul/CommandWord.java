package worldofzuul2;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord
{
    GO("go"), 
    QUIT("quit"), 
    HELP("help"), 
    UNKNOWN("?"), 
    FIGHT("fight"), 
    LOOT("loot"), 
    USE("use"), MOVE("move"), 
    ACCUSE("accuse"), 
    FLEE("flee"), 
    TALK("talk"),
    SEARCH("search");
    
    private String commandString;
    
    /**
     * Constructor for the CommandWord-class
     * @param commandString 
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * Converts the CommandWord to a String
     * @return String
     */
    @Override
    public String toString()
    {
        return commandString;
    }
}
