
package worldofzuul2;

/** 
 * Class for commands in the game.
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Command
{
    /**
     * Attributes used to describe a command.
     */
    private CommandWord commandWord;
    private String secondWord;

    /**
     * Constructor for the Command-class.
     * @param commandWord The commandword that tells us which command is chosen.
     * @param secondWord Second word of command, used to tell things like directions.
     */
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }
    
    /**
     * Accessor for the CommandWord.
     * @return the accessed commandWord.
     */
    public CommandWord getCommandWord()
    {
        return commandWord;
    }
    
    /**
     * Accessor for the secondWord.
     * @return the 2nd word of the command.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * Method to check if the given command is unknown.
     * @return true if the command is not known.
     */
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     * Checks if the given command has a secondWord.
     * @return boolean, true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}