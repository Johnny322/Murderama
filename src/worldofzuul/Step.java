package worldofzuul2;


/**
 * 
 * @author Asmus
 */

public class Step {
	/**
         * Various atributs to describe a step.
         */
	private Item item;
	private String information;
	private Monster monster;
        private NPC npc;
	
        /**
         * Empty constructor method for Step.
         * Creates an empty Step.
         */
	public Step() {
		this.item = null;
		this.npc = null;
		this.information = null;
		this.monster = null;
	}
	
        /**
         * Constructor for a Step with an Item on it.
         * @param _item - Item on this step.
         */
	public Step(Item _item) {
		this.item = _item;

	}
	
        /**
         * Constructor for a Step with a NPC on it.
         * @param _npc - The NPC on this step.
         */
	public Step(NPC _npc) {
		this.npc = _npc;
	}
	
        /**
         * Constructor for a Step with an Monster on it.
         * @param _monster - The monster on this step. 
         */
	public Step(Monster _monster) {
		this.monster = _monster;
	}

	
        /**
         * Accessor for NPC
         * @return Returns the NPC on a step
         */
	public NPC getNPC() {
		return this.npc;
	}
        /**
         * Accesor for a Character
         * @return Returns the Character on a step
         */
        public Character getCharacter() {
            return (Character) this.npc;
        }
	
        /**
         * Method to void an Item at the Step
         */
	// Temp shitty setup
        public void voidItem() {
            this.item = null;
        }
        
        /**
         * Method to void an Item at the Step.
         */
	// Temp shitty setup
        public void voidNPC() {
            this.npc = null;
        }
	
        /**
         * Accesor for the item on this step.
         * @return Item on Step.
         */
	public Item getItem() {
		return this.item;
	}
	
        /**
         * Accesor for the information on this step.
         * @return information on Step.
         */
	public String getInformation() {
		return this.information;
	}
	
        /**
         * Accesor for the Monster on this step.
         * @return Monster on Step.
         */
	public Monster getMonster() {
		return this.monster;
	}
}
