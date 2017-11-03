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
public class NPC extends Character {
    
    private String information;
    private final Character character;
    private boolean isHostile;
    private final boolean isMurderer;
    
    public NPC (Character character, String information, boolean isHostile, boolean isMurderer) {
        this.character = character;
        this.isHostile = isHostile;
        this.isMurderer = isMurderer;
        this.information = information;
    }
    
    public void setStatus() {
        this.isHostile = true;
    }

    /**
     * 
     * @return true if the NPC is hostile
     */
    public boolean getStatus() {
        return this.isHostile;
    }
    
    public void test() {
        super.setHp(10);
        super.alive();
    }

        /**
     * 
     * @return true if the NPC checked is the murderer
     */
    public boolean isMurderer() {
    	return this.isMurderer;
    }
    
    public String getInformation() {
        return this.information;
    }
    
    
}
