package worldofzuul2;

/**
 * Class for the individual Steps within Rooms.
 */
public class Step {
    
	/**
         * Various attributes to describe what a Step contains.
         */
	private Item item;  
	private String information;
        private NPC npc;
        
        /**
         * Empty constructor method for Step.
         * Creates an empty Step.
         */
	public Step() {
		this.item = null;
		this.npc = null;
		this.information = null;
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
         * Accessor for NPC.
         * @return the NPC on this step.
         */
	public NPC getNPC() {
		return this.npc;
	}
        /**
         * Accessor for a Character.
         * @return the Character on this step.
         */
        public Character getCharacter() {
            return (Character) this.npc;
        }
	
        /**
         * Method to void an Item at the Step.
         */
        public void voidItem() {
            this.item = null;
        }
        
        /**
         * Method to void an Item at the Step.
         */
        public void voidNPC() {
            this.npc = null;
        }
	
        /**
         * Accessor for the item on this step.
         * @return Item on Step.
         */
	public Item getItem() {
		return this.item;
	}
	
        /**
         * Accessor for the information on this step.
         * @return information on Step.
         */
	public String getInformation() {
		return this.information;
	}

}
