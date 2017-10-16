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
     private List<Items> Inventory = new ArrayList<>();
    
    private int controlVariable = 0;
    
    public void addItem(){
        if (controlVariable >= 16) {
            System.out.println("Your inventory is full, you can't carry any more items");
        }
        else Inventory.add(get.Item);
                controlVariable++;
    }
    
    public void showInventory(){
        for (int i = 0 ; i < this.Inventory.size(); i++){
            System.out.println((i+1) + ". " + this.Inventory.getClass(i));
        }
    }
    
    public void dropItem(){
        Inventory.remove();
        controlVariable--;
    }
    
    public void clearInventory(){
        Inventory.clear();
    }
}
