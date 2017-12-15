
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul2;

/**
 * This is an interface used to describe non-player characters. It's implemented by Evil, Friendly and Monster.
 * @author Simon
 */
public interface NPC {

    /**
     * Getter-method for the information of the NPC.
     * @return the information the NPC has.
     */
    public String getInformation();

    /**
     * Sets the hostility of the NPC.
     * @param isHostile - the state we want to set the hostility to.
     */
    public void setHostile(boolean isHostile);

    /**
     * Checks if the character is hostile.
     * @return true of the character is hostile.
     */
    public boolean isHostile();

    /**
     * Checks if the NPC is the murderer.
     * @return true if the NPC is the murderer.
     */
    public boolean isMurderer();

    /**
     * Checks if the NPC is Evil.
     * @return true if the NPC is Evil.
     */
    public boolean isEvil();

    /**
     * Checks if the NPC is Friendly.
     * @return true if the NPC is Friendly.
     */
    public boolean friendly();

    /**
     * Gets the Threshold of the NPC.
     * @return the threshold of the NPC.
     */
    public int getThreshold();

    /**
     * Accesses a description of the NPC.
     * @return a description of the NPC.
     */
    public String getDescription();

}
