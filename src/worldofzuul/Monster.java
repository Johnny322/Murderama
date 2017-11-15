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
public class Monster extends Character implements NPC {

    private final String information;
    private boolean isHostile;
    
    /**
     *
     * @param name
     * @param damage
     * @param hp
     * @param information
     */
    //Constructor for monster, tager name, damage og hp og bruger dem i Character-constructoren. 
    public Monster (String name, int damage, int hp, String information) {
        super(name, damage, hp);
        this.information = information;
        this.isHostile = true;
    }
    
    /**
     *
     * @return
     */
    //Overrider funktionen getInformation, der er blevet defineret for NPC-interfacet
    @Override
    public String getInformation() {
        if(this.isHostile) {
            System.out.println(super.getName() + " is hostile and cannot be talked to");
            return null;
        }
        return this.information;
    }

    @Override
    public void setHostile(boolean isHostile) {
        this.isHostile = isHostile;
    }

    @Override
    public boolean isHostile() {
        return this.isHostile;
    }
    
    @Override
    public boolean isMurderer() {
        return false;
    }
    
    @Override
    public boolean isEvil() {
        return true;
    }
}
