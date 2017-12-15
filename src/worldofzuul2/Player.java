package worldofzuul2;

/**
 * Class for the playable character in the game. This stores attributes related to 
 * the player itself.
 * @author Simon
 */
public class Player extends Character {
    
    /**
     * various attributes to describe the player.
     */
    private final Item[] inventory;
    private int lives;
    private int walkSpeed;
    private final Notes notes;
    private int fightSpeed;
    private int searchSpeed;
    private int likeability;

    /**
     * Creates the playable Character.
     * @param name - The name of the player.
     * @param damage - The damage the player deals.
     * @param hp - The amount of hp the player has.
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
     * Mutator for the amount of lives the player has.
     * @param lives - how many lives the player loses.
     */
    public void setLives(int lives) {
        this.lives -= lives;
    }

    /**
     * Setter for the players walk speed.
     * @param walkSpeed - the new walk speed for the player.
     */
    public void setWalkSpeed(int walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    /**
     * Setter for the players fight speed.
     * @param fightSpeed - The speed at which the player fights.
     */
    public void setFightSpeed(int fightSpeed) {
        this.fightSpeed = fightSpeed;
    }

    /**
     * Setter for the players Search speed.
     * @param searchSpeed - The speed of how long the player takes to search.
     */
    public void setSearchSpeed(int searchSpeed) {
        this.searchSpeed = searchSpeed;
    }

    /**
     * Accessor for the players inventory.
     * @return the player inventory.
     */
    public Item[] getInventory() {
        return this.inventory;
    }

    /**
     * Accessor for the player lives.
     * @return the amount of lives the Player has left.
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Accessor for the player walk speed.
     * @return the player walkspeed.
     */
    public int getWalkSpeed() {
        return this.walkSpeed;
    }

    /**
     * Accessor for the notes.
     * @return the notes that the player has.
     */
    public Notes getNotes() {
        return this.notes;
    }

    /**
     * Accessor for the player fight speed.
     * @return the players fight speed.
     */
    public int getFightSpeed() {
        return this.fightSpeed;
    }

    /**
     * Accessor for the player search speed.
     * @return the players search speed.
     */
    public int getSearchSpeed() {
        return this.searchSpeed;
    }

    /**
     * Method for decreasing the players hp.
     * @param i - the amount of hp that the player lost.
     */
    public void setHp(int i) {
        super.changeHp(i);
    }
    
    /**
     * Method for looting an item on a step.
     * @param item - the item that should be looted.
     * @return a string indicating whether or not the item has been added to inventory, and why if it has not.
     */
    public String lootItem(Item item) {
            for (int i = 0; i < this.inventory.length; i++) {
                if (this.inventory[i] == null) {
                    this.inventory[i] = item;
                    return item.getName() + " added to inventory";
                }
            }
            return "Inventory is full";
    }
    
    /**
     * Setter for the player hp.
     * @param hp - the amount the hp should be set to.
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
                s = s + inventory[i].getName() + "\n";
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
     * @param increment - the amount the likeability should be increased by.
     */
    public void increaseLikeability(int increment) {
        this.likeability += increment;
    }

    /**
     * Accessor for the likeability.
     * @return the likeability of the player.
     */
    public int getLikeability() {
        return this.likeability;
    }
}
