package worldofzuul2;

public abstract class Character {
	
	private final String name;
	private int hp;
	private int damage;
    
	/**
         * Creates the playable Character
         */
	public Character() {
		this.name = "Jeff";
		this.hp = 100;
		this.damage = 10;
	}
	
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
    public void setHp(int damageTaken) 
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
    
    
}
