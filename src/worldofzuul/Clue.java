package worldofzuul2;

/**
 *
 * Since there's only a few Clues, we decided to use enum for the clues.
 */
public enum Clue implements Item {

    KNIFE("You can use the knife to attack and fight other players"),
    FINGERPRINT("This is a bloody fingerprint and there is dirt all around it"),
    FOOTPRINT("That is a large footprint and it is fresh. It may be of someone who have just entered the university"),
    CLOTHES("There are several bloodstains on these clothes, they may help you find the murderer"),
    VICTIM("This is a victim"),
    WITNESS("You can ask the witness for any anomalies around the university"),
    UNKNOWN(""),
    LAMP("lamp");
    /**
     *
     * This shows Clue has a String. Clues contains information needed to play
     * the game.
     */
    private final String clue;

    /**
     * Accessor for clue.
     *
     * @param clue - accesses the clue.
     */
    private Clue(String clue) {
        this.clue = clue;
    }

    /**
     * It is a method which overrides a clue.
     *
     * @return clue.
     */
    @Override
    public String toString() {
        return this.clue;
    }

    /**
     * Accessor to the name of the clue in play.
     *
     * @return clue - name of the clue.
     */
    @Override
    public String getName() {
        return clue;
    }

    /**
     * It checks if the clue is Movable
     *
     * @return false - if it is, it should return false.
     */
    @Override
    public boolean isMovable() {
        return false;
    }

}
