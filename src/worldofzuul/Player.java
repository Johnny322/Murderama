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
    
    private final Item[] inventory;
    private int lives;
    private boolean isMurdererAlive = true;
    private int walkSpeed;
    private final Notes notes;
    private int fightSpeed;
    private int searchSpeed;
    private int likeability;

    /**
     * Creates the playable Character
     * @param name
     * @param damage
     * @param hp
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

    public void setLives(int lives) {
        this.lives -= lives;
    }

    public void setIsMurdererAlive(boolean isMurdererAlive) {
        this.isMurdererAlive = isMurdererAlive;
    }

    public void setWalkSpeed(int walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    public void setFightSpeed(int fightSpeed) {
        this.fightSpeed = fightSpeed;
    }

    public void setSearchSpeed(int searchSpeed) {
        this.searchSpeed = searchSpeed;
    }


    public Item[] getInventory() {
        return this.inventory;
    }

    public int getLives() {
        return this.lives;
    }

    public boolean isIsMurdererAlive() {
        return this.isMurdererAlive;
    }

    public int getWalkSpeed() {
        return this.walkSpeed;
    }

    public Notes getNotes() {
        return this.notes;
    }

    public int getFightSpeed() {
        return this.fightSpeed;
    }

    public int getSearchSpeed() {
        return this.searchSpeed;
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
    
    public void setCurrentHp (int hp) {
        super.setHP(hp);
    } 
    
    public void showInventory() {
        for(int i = 0; i < this.inventory.length; i++) {
            if(inventory[i] != null) {
                System.out.println("A " + inventory[i].getName());
            }
        }
    }  
    
    public void addInventory(Item item) {
        for (int i = 0; i < this.inventory.length; i++) {
                if (this.inventory[i] == null) {
                    this.inventory[i] = item;
                   break;
                }
            }
    }

    public void increaseLikeability(int increment) {
        this.likeability += increment;
    }

    public int getLikeability() {
        return this.likeability;
    }
}