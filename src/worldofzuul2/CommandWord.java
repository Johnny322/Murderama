package worldofzuul2;

/**
 * Since there is a limited number of possible commands, an enum is used.
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public enum CommandWord {
    HELP("help"),
    UNKNOWN("?"),
    FIGHT("fight"),
    LOOT("loot"),
    USE("use"),
    MOVE("move"),
    ACCUSE("accuse"),
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
     * @param commandString the string used in this command.
     */
    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Converts the CommandWord to a String. Override from the Object-class.
     * @return the CommandWord as a String.
     */
    @Override
    public String toString() {
        return commandString;
    }
}