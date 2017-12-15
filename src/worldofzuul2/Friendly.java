package worldofzuul2;

/**
 * Class for Friendly-type characters - this implements the NPC-interface.
 * @author Simon
 */
public class Friendly extends Character implements NPC {

    /**
     * Various attributes for describing a Friendly NPC.
     */
    private final String information;
    private final boolean isMurderer;
    private boolean isHostile;
    private final boolean friendly;
    private final int threshold;
    private final String description;

    /**
     * Constructor for Friendly-type characters.
     * @param name - the name of the Character.
     * @param damage - the damage the character deals.
     * @param hp - the amount of health points the character has.
     * @param information - the information the character knows.
     * @param friendly - boolean, true if the character is willing to initiate contacts with the player.
     * @param threshold - how likely a character is to trust the player.
     * @param description - a description of the character.
     */
    public Friendly(String name, int damage, int hp, String information, boolean friendly, int threshold, String description) {
        super(name, damage, hp);
        this.information = information;
        this.isMurderer = false;
        this.isHostile = false;
        this.friendly = friendly;
        this.threshold = threshold;
        this.description = description;
    }

    /**
     * Gets the characters threshold.
     * @return the Threshold of the character.
     */
    @Override
    public int getThreshold() {
        return this.threshold;
    }

    /**
     * Setter method for the characters hostility.
     * @param isHostile - boolean, true if the character should be hostile.
     */
    @Override
    public void setHostile(boolean isHostile) {
        this.isHostile = isHostile;
    }

    /**
     * Method which checks if the character is hostile.
     * @return true if the character is hostile.
     */
    @Override
    public boolean isHostile() {
        return this.isHostile;
    }

    /**
     * Gets the characters description.
     * @return the description of the character.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Method which checks if the character is the Murderer.
     * @return true if the character is the Murderer.
     */
    @Override
    public boolean isMurderer() {
        return isMurderer;
    }

    /**
     * Accessor for the characters Information.
     * @return the Information of the character.
     */
    @Override
    public String getInformation() {
        return this.information;
    }

    /**
     * Method for checking if the character is Evil.
     * @return always false, since Friendly-type characters are never Evil.
     */
    @Override
    public boolean isEvil() {
        return false;
    }

    /**
     * Method for checking if the character is friendly.
     * @return boolean, true if the character is friendly.
     */
    @Override
    public boolean friendly() {
        return this.friendly;
    }
}
