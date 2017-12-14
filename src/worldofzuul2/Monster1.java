
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul2;

/**
 *
 * @author Simon
 */
public class Monster1 extends Character implements NPC {

    private final String information;
    private boolean isHostile;
    private int threshold;
    private String description;

    /**
     *
     * @param name the name of the Character.
     * @param damage the damage the character deals.
     * @param hp the amount of health points the character has.
     * @param information the information the character knows.
     * @param description a description of character.
     */
    //Constructor for monster, tager name, damage og hp og bruger dem i Character-constructoren. 
    public Monster1(String name, int damage, int hp, String information, String description) {
        super(name, damage, hp);
        this.information = information;
        this.isHostile = true;
        this.threshold = 0;
        this.description = description;
    }

    /**
     * Constructor gets the characters Information.
     *
     * @return the Information of the character.
     */
    @Override
    public String getInformation() {
        if (this.isHostile) {
            System.out.println(super.getName() + " is hostile and cannot be talked to");
            return null;
        }
        return this.information;
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
     * Constructor gets the characters description.
     *
     * @return the description of the character.
     */
    public String getDescription() {
        return this.description;
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
     * Constructor which checks if the character is Murderer.
     *
     * @return if true it makes the character Murderer.
     */
    @Override
    public boolean isMurderer() {
        return false;
    }

    /**
     * Constructor which checks if the character is Evil.
     *
     * @return if true it makes the character Evil.
     */
    @Override
    public boolean isEvil() {
        return true;
    }

    /**
     * Constructor which checks if the character is friendly.
     *
     * @return if true it makes the character friendly.
     */
    public boolean friendly() {
        return false;
    }

    /**
     * Constructor gets the characters Additional Information.
     *
     * @return the Additional Information of the character.
     */
    public String getAdditionalInformation() {
        return "No more info";
    }
}
