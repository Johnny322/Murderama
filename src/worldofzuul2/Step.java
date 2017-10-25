package worldofzuul2;

public class Step {
	
	private Item item;
	private Character character;
	private String information;
	private Monster monster;
	
        /**
         * Empty constructor method for Step
         * Creates an empty Step
         */
	public Step() {
		this.item = null;
		this.character = null;
		this.information = null;
		this.monster = null;
	}
	
        /**
         * Constructor for a Step with an Item on it
         * @param _item 
         */
	public Step(Item _item) {
		this.item = _item;

	}
	
        /**
         * Constructor for a Step with a Character on it
         * @param _character 
         */
	public Step(Character _character) {
		this.character = _character;
	}
	
        /**
         * Constructor for a Step with an information on it
         * @param _information 
         */
	public Step(String _information) {
		this.information = _information;
	}
	
        /**
         * Constructor for a Step with an Monster on it
         * @param _monster 
         */
	public Step(Monster _monster) {
		this.monster = _monster;
	}
	
        /**
         * Accessor for Character
         * @return Character
         */
	public Character getCharacter() {
		return this.character;
	}
	
        /**
         * Method to void an Item at the Step
         */
	// Temp shitty setup
        public void voidItem() {
            this.item = null;
        }
	
        /**
         * 
         * @return Item on Step
         */
	public Item getItem() {
		return this.item;
	}
	
        /**
         * 
         * @return information on Step
         */
	public String getInformation() {
		return this.information;
	}
	
        /**
         * 
         * @return Monster on Step
         */
	public Monster getMonster() {
		return this.monster;
	}

}
