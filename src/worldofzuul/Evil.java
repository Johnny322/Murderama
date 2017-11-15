/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul2;


/**
 *
 * @author aqibsajjad
 */
public class Evil extends Character implements NPC {
    
    private final String information;
    private final boolean isMurderer;
    private boolean isHostile;
    private boolean friendly;
    
    //Constructor, tager navn, damage og hp og bruger den i Character-constructoren, her kaldet som "super"
    public Evil (String name, int damage, int hp, String information, boolean isMurderer) {
        super(name, damage, hp);
        this.information = information;
        this.isMurderer = isMurderer;
        this.isHostile = true;
        this.friendly = false;
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
    
    public boolean friendly(){
        return friendly;
    }
    /**
     *
     * @return
     */
    //Overrider metoden getInformation fra interfacet NPC
    @Override
    public String getInformation() {
        if(this.isHostile) {
            System.out.println(super.getName() + " is hostile and cannot be talked to until you defeat them in combat");
            return null;
        }
        return this.information;
    }
    
}