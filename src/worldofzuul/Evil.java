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
public class Evil extends Character implements NPC {
    
    private final String information;
    private final boolean isMurderer;
    private boolean isHostile;
    private int threshold;
    
    
    //Constructor, tager navn, damage og hp og bruger den i Character-constructoren, her kaldet som "super"
    public Evil (String name, int damage, int hp, String information, boolean isMurderer, String description) {
        super(name, damage, hp, description);
        this.information = information;
        this.isMurderer = isMurderer;
        this.isHostile = true;
        this.threshold = 0;
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
    //Overrider metoden getInformation fra interfacet NPC
    @Override
    public String getInformation() {
        if(this.isHostile) {
            System.out.println(super.getName() + " is hostile and cannot be talked to until you defeat them in combat");
            return null;
        }
        return this.information;
    }
    
    @Override
    public int getThreshold() {
        return this.threshold;
    }
    
    @Override
    public boolean isEvil() {
        return true;
    }
    
    public boolean friendly(){
        return false;
    }
    
    public String getAdditionalInformation() {    
        return "No more info";
    }
}
