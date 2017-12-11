/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul2;

import java.io.Serializable;

/**
 *
 * @author Simon
 */
public class Player extends Character implements Serializable {
    
    private final Item[] inventory;
    private int lives;
    private boolean isMurdererAlive = true;
    private int walkSpeed;
    private final Notes notes;
    private int fightSpeed;
    private int searchSpeed;
    private Item item;


    /**
     * Creates the playable Character
     * @param name
     * @param damage
     * @param hp
     */
    public Player(String name, int damage, int hp) {
        super(name, damage, hp);
        this.inventory = new Item[10];
        this.lives = 3;
        this.walkSpeed = 10;
        this.fightSpeed = 5;
        this.searchSpeed = 25;
        this.notes = new Notes();
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setIsMurdererAlive(boolean isMurdererAlive) {
        this.isMurdererAlive = isMurdererAlive;
    }

    public void setWalkSpeed(int walkSpeed) {
        this.walkSpeed = walkSpeed;
    }
    public void setCurrentHp (int hp) {
        super.currentHp(hp);
    } 

    public void setFightSpeed(int fightSpeed) {
        this.fightSpeed = fightSpeed;
    }

    public void setSearchSpeed(int searchSpeed) {
        this.searchSpeed = searchSpeed;
    }


    public Item[] getInventory() {
        return inventory;
    }
    

    public int getLives() {
        return lives;
    }

    public boolean isIsMurdererAlive() {
        return isMurdererAlive;
    }

    public int getWalkSpeed() {
        return walkSpeed;
    }

    public Notes getNotes() {
        return notes;
    }

    public int getFightSpeed() {
        return fightSpeed;
    }

    public int getSearchSpeed() {
        return searchSpeed;
    }

    public void setHp(int i) {
        super.changeHp(i);
    }
    
    public String lootItem(Item item) {
        if (item.isMovable()) {
            for (int i = 0; i < this.inventory.length; i++) {
                if (this.inventory[i] == null) {
                    this.inventory[i] = item;
                    return item.getName() + " added to inventory";
                }
            }
            return "Inventory is full";

        } else {
            return item.getName() + " is not movable";
        }

    }
    
    public void showInventory() {
        for(int i = 0; i < this.inventory.length; i++) {
            if(inventory[i] != null) {
                System.out.println("A " + inventory[i].getName());
            }
        }
    }
    
}
