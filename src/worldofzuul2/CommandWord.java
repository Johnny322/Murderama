package worldofzuul2;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord {
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    UNKNOWN("?"),
    FIGHT("fight"),
    LOOT("loot"),
    USE("use"),
    MOVE("move"),
    ACCUSE("accuse"),
    FLEE("flee"),
    TALK("talk"),
    SHOWNOTES("show"),
    W("w"),
    A("a"),
    S("s"),
    D("d"),
    SEARCH("search"),
    LOAD("load"),
    SAVE("save");

    private String commandString;

    /**
     * Constructor for the CommandWord-class.
     *
     * @param commandString.
     */
    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Converts the CommandWord to a String.
     *
     * @return the CommandWord as a String.
     */
    @Override
    public String toString() {
        return commandString;
    }
}