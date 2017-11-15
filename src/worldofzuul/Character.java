package worldofzuul2;

public abstract class Character {
	
	private final String name;
	private int hp;
	private int damage;
    
        /**
         * Constructor for NPC with Item(s)
         * @param name Name of the NPC
         * @param hp hp of the NPC
         */
	Character(String name, int damage, int hp) {
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
    
    public void setHP(int hp) {
        this.hp = hp;
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
}
