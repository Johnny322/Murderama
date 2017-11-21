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
public class Friendly extends Character implements NPC {

    private final String information;
    private final boolean isMurderer;
    private boolean isHostile;
    private final boolean friendly;
    private String additionalInformation;
    private int threshold;

    
    //Constructor, tager name, damage og hp til Character-constructoren
    public Friendly (String name, int damage, int hp, String information, boolean isMurderer, boolean friendly, String additionalInformation, int threshold) {
        super(name, damage, hp);
        this.information = information;
        this.isMurderer = isMurderer;
        this.isHostile = false;
        this.friendly = friendly;
        this.additionalInformation = additionalInformation;
        this.threshold = threshold;
    }

    @Override
    public int getThreshold() {
        return this.threshold;
    }
    
    @Override
    public void setHostile(boolean isHostile) {
        this.isHostile = isHostile;
    }

    @Override
    public boolean isHostile() {
        return this.isHostile;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isMurderer() {
        return isMurderer;
    }
    
    /**
     *
     * @return
     */
    //Overrider metoden getInformation der er blevet defineret for NPC-interfacet
    @Override
    public String getInformation() {
        if(this.isHostile) {
            System.out.println(super.getName() + " is hostile and cannot be talked to");
            return null;
        }
        return this.information;
    }

    @Override
    public boolean isEvil() {
        return false;
    }
    
    @Override
    public boolean friendly(){
        return this.friendly;
    }
    
    public String getAdditionalInformation() {    
        return this.additionalInformation;
    }
    
}
