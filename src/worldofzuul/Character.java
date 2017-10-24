package worldofzuul;

public class Character {
	
	private String name;
	private int hp;
	private Item[] inventory;
	private int damage;
	private String information;
    private boolean isHostile;
    private boolean isMurderer;
    
	
	Character() {
		this.name = "Jeff";
		this.hp = 100;
		this.inventory = new Item[10];
		this.damage = 11;
        this.isHostile = false;
	    this.isMurderer = false;

	}
	
	Character(String name, int hp, int inventory) {
		this.name = name;
		this.hp = hp;
		this.damage = 10;
		this.inventory = new Item[inventory];
        this.isHostile = false;
	    this.isMurderer = false;

	}
	
	Character(String name, int hp, String information) {
		this.name = name;
		this.hp = hp;
		this.damage = 10;
		this.information = information;
        this.isHostile = false;
	    this.isMurderer = false;

	}
	
	Character(boolean murderer) {
		this.name = "Bob";
		this.hp = 150;
		this.damage = 15;
		this.isHostile = false;
	    this.isMurderer = true;

		
	}
	
    public int getDamage() {
        return this.damage;
    }
	
	public void setHp(int damageTaken) 
    {
    	this.hp = hp - damageTaken;
    }
    
    public int getHp() {
        return hp;
    }
    
    
    public String getName() {
        return name;
    }
    
    public void setStatus() {
        this.isHostile = true;
    }

    public boolean getStatus() {
        return this.isHostile;
    }

    public boolean alive() {
        return this.hp > 0;
    }
    
    public boolean isMurderer() {
    	return this.isMurderer;
    }

    
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
    
    public boolean isInventoryFull() {
    	for(int i = 0; i<inventory.length; i++) {
    		if(inventory[i] == null) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public void showInventory() {
    	for(int i = 0; i < inventory.length; i++) {
    		if(inventory[i] != null) {
    			System.out.println(inventory[i].toString());
    		}
    	}
    }
    
    
    //Mangler throws, returner 0 i stedet for error
    public int getItemFromInventory(int spaceInInventory) {
		if(inventory[spaceInInventory] != null) {
			return spaceInInventory;
		} 
		System.out.println("No item in that space");
		return 0;
    }
    
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

    public void fight(Character character) {
        if(character.getStatus()) {
            character.setHp(this.damage);
            this.hp -= character.getDamage();
            if(character.alive()) {
                System.out.println(character.getName() + " has " + character.getHp() + " hp left");
                System.out.println("You have " + this.hp + " hp left");
            } else {
                System.out.println(character.getName() + " is dead");
                System.out.println("You have " + this.hp + " hp left");
            }
        } else {
            System.out.println(character.getName() + " is not hostile. You must accuse the character before you can fight");
        }
    }
    
}
