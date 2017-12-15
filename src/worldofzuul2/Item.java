/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul2;

/**
 * This is an interface, used for describing shared attributes of Items.
 * It's implemented by Consumables and Clues.
 */
public interface Item {
    
    /**
     * Accessor for the name of the Item.
     * @return the name of the item.
     */
    public String getName();
    
    /**
     * Method for checking if the player can loot this item.
     * @return boolean indicating if the Item can be picked up.
     */
    public boolean isMovable();
    
}