package worldofzuul2;



public class Character {
	
	private String name;
	private int hp;
	private Item[] inventory;
	private int damage;
        private int lives;
	private String information;
        private boolean isHostile;
        private final boolean isMurderer;
        private boolean isMurdererAlive = true;
        private int walkSpeed;
        private Notes notes;
        private int fightSpeed;
        private int searchSpeed;
    
	/**
         * Creates the playable Character
         */
	public Character() {
		this.name = "Jeff";
		this.hp = 100;
		this.inventory = new Item[10];
		this.damage = 11;
                this.isHostile = false;
                this.isMurderer = false;
                this.lives = 3;
                this.walkSpeed = 10;
                this.fightSpeed = 5;
                this.searchSpeed = 25;
                this.notes = new Notes();
	}
	
        /**
         * Constructor for NPC with Item(s)
         * @param name Name of the NPC
         * @param hp hp of the NPC
         * @param inventory size of Inventory for NPC
         */
	Character(String name, int hp, int inventory) {
		this.name = name;
		this.hp = hp;
		this.damage = 10;
		this.inventory = new Item[inventory];
                this.isHostile = false;
                this.isMurderer = false;

	}
	
        /**
         * Constructor for NPC with information
         * @param name
         * @param hp
         * @param information 
         */
	Character(String name, int hp, String information) {
		this.name = name;
		this.hp = hp;
		this.damage = 10;
        	this.information = information;
                this.isHostile = false;
                this.isMurderer = false;
	}
	
        /**
         * Constructor for the murderer
         * @param murderer 
         */
	Character(boolean murderer) {
		this.name = "Bob";
		this.hp = 150;
		this.damage = 111;
		this.isHostile = false;
                this.isMurderer = true;
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
     * Called if the NPC is accused of the crime. Sets the NPC to be hostile
     */
    public void setStatus() {
        this.isHostile = true;
    }

    /**
     * 
     * @return true if the NPC is hostile
     */
    public boolean getStatus() {
        return this.isHostile;
    }

    /**
     * 
     * @return true if the monster har move than 0 hp
     */
    public boolean alive() {
        return this.hp > 0;
    }
    
    /**
     * 
     * @return true if the NPC checked is the murderer
     */
    public boolean isMurderer() {
    	return this.isMurderer;
    }
    
    public void setMurdererAlive() {
        isMurdererAlive = false;
    }
    
    public boolean isMurdererAlive() {
        return isMurdererAlive;
    }

    public void setLives() {
        this.lives -= 1;
    }
    
    public int getLives() {
        return this.lives;
    }
    
    public void setWalkSpeed(int mutator) {
        this.walkSpeed += mutator;
    }
    
    public void setWalkSpeed() {
        this.walkSpeed = 5;
    }
    
    public int getWalkSpeed() {
        return this.walkSpeed;
    }
    
    public void setSearchSpeed(int mutator) {
        this.searchSpeed += mutator;
    }
    
    public void setSearchSpeed() {
        this.searchSpeed = 10;
    }
    
    public int getSearchSpeed() {
        return this.searchSpeed;
    }
    
    public void setFightSpeed(int mutator) {
        this.fightSpeed += mutator;
    }
    
    public void setFightSpeed() {
        this.fightSpeed = 1;
    }
    
    public int getFightSpeed() {
        return this.fightSpeed;
    }
    
    public Item[] getInventory() {
        return this.inventory;
    }
    
    public Notes getNotes() {
        return this.notes;
    }
    
    public String getInformation() {
        return this.information;
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
            for (Item inventory1 : inventory) {
                if (inventory1 == null) {
                    return false;
                }
            }
    	return true;
    }
    
    /**
     * Shows the entire Inventory
     */
    public void showInventory() {
            for (Item inventory1 : inventory) {
                if (inventory1 != null) {
                    System.out.println(inventory1.toString());
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
    
    /**
     * Deals damage to the player and the monster
     * @param monster 
     */
    public void fight(Monster monster) {
        if(monster.alive()) {
        	monster.setHp(this.damage);
        	this.hp -= monster.getDamage();
        	if(monster.alive()) {
            	System.out.println("Monster has " + monster.getHp() + " hp left");
            	System.out.println("You have " + this.hp + " hp left");
        	} else {
            	System.out.println("Monster is dead");
            	System.out.println("You have " + this.hp + " hp left");
        	}
        } else { 
        	System.out.println("Monster is already dead. ");
        }
    }

    /**
     * Deals damage to the player and the Character
     * @param character 
     */
    public void fight(Character character) {
        if(character.getStatus()) {
            character.setHp(this.damage);
            this.hp -= character.getDamage();
            if(character.alive()) {
                System.out.println(character.getName() + " has " + character.getHp() + " hp left");
                System.out.println("You have " + this.hp + " hp left");
                if(isMurderer()) {
                    
                }
            } else {
                System.out.println(character.getName() + " is dead");
                System.out.println("You have " + this.hp + " hp left");
            }
        } else {
            System.out.println(character.getName() + " is not hostile. You must accuse the character before you can fight");
        }
    }
    
}
