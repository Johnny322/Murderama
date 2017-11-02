package worldofzuul2;

/** 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class Command
{
    private CommandWord commandWord;
    private String secondWord;

    /**
     * Constructor for the Command-class
     * @param commandWord The commandword that tells the switch-case which command is chosen
     * @param secondWord Second word of command, to tell directions
     */
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }
    
    /**
     * Accessor
     * @return CommandWord
     */
    public CommandWord getCommandWord()
    {
        return commandWord;
    }
    
    /**
     * Accessor
     * @return secondWord for the 2nd word of the command
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * Returns true if the command is not known
     * @return boolean
     */
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     * 
     * @return boolean true if the word has a second word
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

