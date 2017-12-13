package worldofzuul2;


/**
 *
 * @author Simon
 */
public class Evil extends Character implements NPC {
    
    private final String information;
    private final boolean isMurderer;
    private boolean isHostile;
    private int threshold;
    private String description;
    
    /**
     * 
     * @param name the name of the Character.
     * @param damage the damage the character deals.
     * @param hp the amount of health points the character has.
     * @param information the information the character knows.
     * @param isMurderer if the character is the murderer.
     * @param description a description of character.
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
     * @param isHostile 
     */
    @Override
    public void setHostile(boolean isHostile) {
        this.isHostile = isHostile;
    }
/**
 * Constructor which checks if the character is hostile.
 * @return if true it makes the character hostile.
 */
    @Override
    public boolean isHostile() {
        return this.isHostile;
    }

/**
 * Constructor which checks if the character is Murderer.
 * @return if true it makes the character the Murderer.
 */
    @Override
    public boolean isMurderer() {
        return isMurderer;
    }
    
    /**
     * Constructor gets the characters description.
     * @return the description of the character.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Constructor gets the characters Information.
     * @return the Information of the character.
     */
    
    @Override
    public String getInformation() {
        if(this.isHostile) {
            System.out.println(super.getName() + " is hostile and cannot be talked to until you defeat them in combat");
            return null;
        }
        return this.information;
    }
    /**
     * Constructor gets the characters Threshold.
     * @return the Threshold of the character.
     */
    @Override
    public int getThreshold() {
        return this.threshold;
    }
    /**
     * Constructor which checks if the character is evil. If it is then it returns true.
     * @return
     */
    @Override
    public boolean isEvil() {
        return true;
    }
        /**
     * Constructor checks if the character is friendly. If it is then it returns false.
     * @return
     */
    public boolean friendly(){
        return false;
    }
    /**
     * Constructor gets the characters Additional Information.
     * @return the Additional Information of the character.
     */
    public String getAdditionalInformation() {    
        return "No more info";
    }
}