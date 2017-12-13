
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
public class Player extends Character {
    /**
     * various atributs to describe the player.
     */
    
    private final Item[] inventory;
    private int lives;
    private boolean isMurdererAlive = true;
    private int walkSpeed;
    private final Notes notes;
    private int fightSpeed;
    private int searchSpeed;
    private int likeability;

    /**
     * Creates the playable Character.
     * @param name - The name of the player.
     * @param damage - The damage the player deals.
     * @param hp - The amout of hp the player has.
     */
    public Player(String name, int damage, int hp) {
        super(name, hp, damage);
        this.inventory = new Item[10];
        this.lives = 3;
        this.walkSpeed = 10;
        this.fightSpeed = 5;
        this.searchSpeed = 25;
        this.notes = new Notes();
        this.likeability = 0;
    }

    /**
     * Setter for the amout of lives the player has.
     * @param lives - how many lives the player loses.
     */
    
    public void setLives(int lives) {
        this.lives -= lives;
    }

    /**
     * Method for checking if the murder is alive.
     * @param isMurdererAlive - Sets if the murder is alive.
     */
    
    public void setIsMurdererAlive(boolean isMurdererAlive) {
        this.isMurdererAlive = isMurdererAlive;
    }

    /**
     * Setter for the player walk speed.
     * @param walkSpeed - Sets the walk speed for the player.
     */
    
    public void setWalkSpeed(int walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    /**
     * Setter for the player fight speed.
     * @param fightSpeed - The speed that the player fights.
     */
    
    public void setFightSpeed(int fightSpeed) {
        this.fightSpeed = fightSpeed;
    }

    /**
     * Setter for the player Search speed.
     * @param searchSpeed - The speed of how long the player takes to search.
     */
    
    public void setSearchSpeed(int searchSpeed) {
        this.searchSpeed = searchSpeed;
    }

    /**
     * Accesor for the player inventory.
     * @return Returns the player inventory.
     */

    public Item[] getInventory() {
        return this.inventory;
    }

    /**
     * Accesor for the player lives.
     * @return Returns the amount of lives the Player has left.
     */
    
    public int getLives() {
        return this.lives;
    }

    /**
     * Mehtod for checking if the murder is alive.
     * @return Returns if the Murder is alive.
     */
    
    public boolean isIsMur1dererAlive() {
        return this.isMurdererAlive;
    }

    /**
     * Accesor for the player walk speed.
     * @return Returns the player walkspeed.
     */
    
    public int getWalkSpeed() {
        return this.walkSpeed;
    }

    /**
     * Accesor for the notes.
     * @return Returns the notes.
     */
    
    public Notes getNotes() {
        return this.notes;
    }

    /**
     * Accesor for the player fight speed.
     * @return Returns the plaer fight speed.
     */
    
    public int getFightSpeed() {
        return this.fightSpeed;
    }

    /**
     * Accesor for the player search speed.
     * @return Returns the player search speed.
     */
    
    public int getSearchSpeed() {
        return this.searchSpeed;
    }

    /**
     * Setter for the Player hp.
     * @param i - the amout of hp that the player should have.
     */
    
    public void setHp(int i) {
        super.changeHp(i);
    }
    
    /**
     * Metod for looting a item on a step.
     * @param item - What item that should be looted.
     * @return Returns if the item has or has not been added to the inventory.
     */
    
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
    
    /**
     * Setter for the player hp.
     * @param hp - the amout the hp should be set to.
     */
    
    public void setCurrentHp (int hp) {
        super.setHP(hp);
    } 
    
    /**
     * Method for printing the items in the inventory.
     * @return String to be printed in GUI.
     */
    
    public String showInventory() {
        String s = "";
        for(int i = 0; i < this.inventory.length; i++) {
            if(inventory[i] != null) {
                s = s +"A " + inventory[i].getName() + "\n";
            }
        }
        return s;
    }  
    /**
     * Method for adding a item to the inventory.
     * @param item - the item being added to the inventory.
     */
    
    public void addInventory(Item item) {
        for (int i = 0; i < this.inventory.length; i++) {
                if (this.inventory[i] == null) {
                    this.inventory[i] = item;
                   break;
                }
            }
    }

    /**
     * Method for increasing the likeability of the player.
     * @param increment - the amout the likeability should be increased by.
     */
    
    public void increaseLikeability(int increment) {
        this.likeability += increment;
    }

    /**
     * Accesor for the likeability.
     * @return Returns the likeability of the player.
     */
    
    public int getLikeability() {
        return this.likeability;
    }
}

    
