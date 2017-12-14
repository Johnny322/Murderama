
package worldofzuul2;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Parser 
{
    private CommandWords commands;
    private Scanner reader;

    /**
     * Constructor for the Parser-class.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * Accessor to get the Command.
     * @return Command depending on the String given by the user.
     */
    public Command getCommand() 
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }
        tokenizer.close();

        return new Command(commands.getCommandWord(word1), word2);
    }

    /**
     * Shows all the valid commands.
     * @return String to be printed in GUI
     */
    public String showCommands()
    {
        return commands.showAll();
    }
}
