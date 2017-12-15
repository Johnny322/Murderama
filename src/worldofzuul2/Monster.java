
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul2;

/**
 * Class for a Monster-type NPC.
 * @author Simon
 */
public class Monster extends Character implements NPC {

    private final String information;
    private boolean isHostile;
    private final int threshold;
    private final String description;

    /**
     * Constructor used for instantiating a Monster.
     * @param name the name of the Character.
     * @param damage the damage the character deals.
     * @param hp the amount of health points the character has.
     * @param information the information the character knows.
     * @param description a description of character.
     */
    public Monster(String name, int damage, int hp, String information, String description) {
        super(name, damage, hp);
        this.information = information;
        this.isHostile = true;
        this.threshold = 0;
        this.description = description;
    }

    /**
     * Gets the characters Information.
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
     * Gets the characters Threshold.
     * @return the Threshold of the character.
     */
    @Override
    public int getThreshold() {
        return this.threshold;
    }

    /**
     * Gets the characters description.
     * @return the description of the character.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Mutator for the isHostile-attribute of the Monster.
     * @param isHostile - the state the characters hostility should be set to.
     */
    @Override
    public void setHostile(boolean isHostile) {
        this.isHostile = isHostile;
    }

    /**
     * Method which checks the characters hostility.
     * @return true if the character is hostile.
     */
    @Override
    public boolean isHostile() {
        return this.isHostile;
    }

    /**
     * Checks if the character is Murderer.
     * @return true if the character is the Murderer.
     */
    @Override
    public boolean isMurderer() {
        return false;
    }

    /**
     * Method which checks if the character is Evil.
     * @return always true, since Monsters are always Evil.
     */
    @Override
    public boolean isEvil() {
        return true;
    }

    /**
     * Method which checks if the character is friendly.
     * @return always false, since a Monster is never friendly.
     */
    @Override
    public boolean friendly() {
        return false;
    }
}
