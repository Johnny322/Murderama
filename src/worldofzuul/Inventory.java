/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Asmus
 */
public class Inventory {
     private item[] inventory;
     
     public inventory() {
          this.inventory = new Item[10];
     }
         /**
     * Loots the given Item and adds it to the Inventory
     * @param item 
     */
    public void lootItem(Item item) {
    	if(! isInventoryFull()){
    		for(int i = 0; i<inventory.length; i++) {
    			if(inventory[i] == null) {
    				inventory[i] = item;
    				System.out.println(item.toString() + " has been added to inventory.");
    				break;
    			}
    		}
    	} else {
    		System.out.println("Inventory is full");
    	}	
    }
    
    /**
     * Uses the Item in the designated space in the Inventory
     * @param numberInInventory 
     */
    public void useItem(int numberInInventory) {
		Item item = inventory[numberInInventory];
		if(item == Item.POTION) {
			this.damage += 2;
			System.out.println("Potion has been used. Damage has been increased to " + this.damage);
			inventory[numberInInventory] = null;
		} else {
			System.out.println("Does not work");
		}
	}
    
    /**
     * 
     * @return true if the Inventory is full
     */
    public boolean isInventoryFull() {
    	for(int i = 0; i<inventory.length; i++) {
    		if(inventory[i] == null) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Shows the entire Inventory
     */
    public void showInventory() {
    	for(int i = 0; i < inventory.length; i++) {
    		if(inventory[i] != null) {
    			System.out.println(inventory[i].toString());
    		}
    	}
    }
    
    
    //Mangler throws, returner 0 i stedet for error
    public int getItemFromInventory(int spaceInInventory)  {
		if(inventory[spaceInInventory] != null) {
			return spaceInInventory;
		} 
		System.out.println("No item in that space");
		throw new NullPointerException();
    }
    
     public void clearInventory(){
          inventory.clear;
          system.out.println("Inventory cleared")
     }
          
}

