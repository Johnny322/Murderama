
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul2;

/**
 * This is an interface. It's implemented by Evil, Friendly and Monster.
 *
 * @author Simon
 */
public interface NPC {

    public String getInformation();

    public void setHostile(boolean isHostile);

    public boolean isHostile();

    public boolean isMurderer();

    public boolean isEvil();

    public boolean friendly();

    public String getAdditionalInformation();

    public int getThreshold();

    public String getDescription();

}
