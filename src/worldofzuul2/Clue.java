package worldofzuul2;

/**
 *
 * Since there's only a few Clues, we decided to use enum for the clues.
 */
public enum Clue implements Item {

    KNIFE("knife"),
    FINGERPRINT("fingerprint"),
    FOOTPRINT("footprint"),
    CLOTHES("bloody clothes"),
    VICTIM("victim"),
    WITNESS("witness"),
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