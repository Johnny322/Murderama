/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul2;

/**
 * This is an interface
 * It's implemented by Consumables and Clues
 */
public interface Item {
    
    public String getName();
    public boolean isMovable();
    
}