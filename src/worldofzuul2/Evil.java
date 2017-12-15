package worldofzuul2;


/**
 * Class for Evil characters - this implements the NPC-interface.
 * @author Simon
 */
public class Evil extends Character implements NPC {
    
    /**
     * Various attributes used to describe an Evil-type NPC.
     */
    private final String information;
    private final boolean isMurderer;
    private boolean isHostile;
    private final int threshold;
    private final String description;
    
    /**
     * Constructor for Evil.
     * @param name the name of the Character.
     * @param damage the damage the character deals.
     * @param hp the amount of health points the character has.
     * @param information the information the character knows.
     * @param isMurderer boolean, true if the character is the murderer.
     * @param description a description of the character.
     */
    public Evil (String name, int damage, int hp, String information, boolean isMurderer, String description) {
        super(name, damage, hp);
        this.information = information;
        this.isMurderer = isMurderer;
        this.isHostile = true;
        this.threshold = 0;
        this.description = description;
    }
    
    /**
     * Mutator which sets the hostility of the character.
     * @param isHostile the boolean we want to set isHostile to.
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
     * Method which checks if the character is the Murderer.
     * @return true if the character is the Murderer.
     */
    @Override
    public boolean isMurderer() {
        return isMurderer;
    }
    
    /**
     * Accessor which gets the characters description.
     * @return the description of the character.
     */
    @Override
    public String getDescription() {
        return description;
    }
    
    /**
     * Accessor which gets the characters Information.
     * @return the Information of the character.
     */
    @Override
    public String getInformation() {
        return this.information;
    }
    /**
     * Getter-method for the characters Threshold.
     * @return the Threshold of the character.
     */
    @Override
    public int getThreshold() {
        return this.threshold;
    }
    
    /**
     * Method which checks if the character is evil.
     * @return always true, since Evil-type characters are always Evil.
     */
    @Override
    public boolean isEvil() {
        return true;
    }
    
    /**
     * Method which checks if the character is friendly.
     * @return always false, since Evil-type characters are never Friendly.
     */
    @Override
    public boolean friendly(){
        return false;
    }
}