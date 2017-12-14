package worldofzuul2;

/**
 *
 * @author Simon
 */
public class Friendly extends Character implements NPC {

    private final String information;
    private final boolean isMurderer;
    private boolean isHostile;
    private final boolean friendly;
    private String additionalInformation;
    private int threshold;
    private String description;

    /**
     *
     * @param name - the name of the Character.
     * @param damage - the damage the character deals.
     * @param hp - the amount of health points the character has.
     * @param information - the information the character knows.
     * @param isMurderer - if the character is the murderer.
     * @param friendly - the character is friendly.
     * @param additionalInformation - if the character has addintional
     * Information.
     * @param threshold - how likely a character is to trust the player. The
     * player needs a likeability-value higher than this to be able to talk to
     * this character.
     * @param description - a description of character.
     */

    public Friendly(String name, int damage, int hp, String information, boolean isMurderer, boolean friendly, String additionalInformation, int threshold, String description) {
        super(name, damage, hp);
        this.information = information;
        this.isMurderer = isMurderer;
        this.isHostile = false;
        this.friendly = friendly;
        this.additionalInformation = additionalInformation;
        this.threshold = threshold;
        this.description = description;
    }

    /**
     * Constructor gets the characters Threshold.
     *
     * @return the Threshold of the character.
     */
    @Override
    public int getThreshold() {
        return this.threshold;
    }

    /**
     * Makes the the character hostile.
     *
     * @param isHostile - checks if the character is hostile.
     */
    @Override
    public void setHostile(boolean isHostile) {
        this.isHostile = isHostile;
    }

    /**
     * Constructor which checks if the character is hostile.
     *
     * @return if true it makes the character hostile.
     */
    @Override
    public boolean isHostile() {
        return this.isHostile;
    }

    /**
     * Constructor gets the characters description.
     *
     * @return the description of the character.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Constructor which checks if the character is Murderer.
     *
     * @return if true it makes the character Murderer.
     */
    @Override
    public boolean isMurderer() {
        return isMurderer;
    }

    /**
     * Constructor gets the characters Information.
     *
     * @return the Information of the character.
     */
    //Overrider metoden getInformation der er blevet defineret for NPC-interfacet
    @Override
    public String getInformation() {
        if (this.isHostile) {
            System.out.println(super.getName() + " is hostile and cannot be talked to");
            return null;
        }
        return this.information;
    }

    /**
     * Constructor which checks if the character is Evil.
     *
     * @return if true it makes the character Evil.
     */
    @Override
    public boolean isEvil() {
        return false;
    }

    /**
     * Constructor which checks if the character is friendly.
     *
     * @return if true it makes the character friendly.
     */
    @Override
    public boolean friendly() {
        return this.friendly;
    }

    /**
     * Constructor gets the characters Additional Information.
     *
     * @return the Additional Information of the character.
     */
    public String getAdditionalInformation() {
        return this.additionalInformation;
    }

}
