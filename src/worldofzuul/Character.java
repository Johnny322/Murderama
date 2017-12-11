package worldofzuul2;

import java.io.Serializable;

public abstract class Character implements Serializable {
	
	private final String name;
	private int hp;
	private int damage;
    
        /**
         * Constructor for NPC with Item(s)
         * @param name Name of the NPC
         * @param hp hp of the NPC
         */
	Character(String name, int hp, int damage) {
		this.name = name;
		this.hp = hp;
		this.damage = damage;
	}
    
        /**
         * Accessor for damage of Character
         * @return 
         */
   
    public int getDamage() {
        return this.damage;
    }
	
    /**
     * Mutator for hp of the Character
     * @param damageTaken 
     */
    public void changeHp(int damageTaken) 
    {
    	this.hp = hp - damageTaken;
    }
    
    
    
    /**
     * Accessor for the hp of a Character
     * @return 
     */
    public int getHp() {
        return hp;
    }
    
    /**
     * Accessor for the Name of the Character
     * @return 
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return true if the monster har move than 0 hp
     */
    public boolean alive() {
        return this.hp > 0;
    }
    
    //Standard setter for damage
    public void changeDamage(int increase) {
        this.damage += increase;
    }
	public void currentHp(int hp){
    this.hp = hp;
    }
}
