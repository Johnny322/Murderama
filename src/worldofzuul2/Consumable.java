package worldofzuul2;


/**
 * Enum describing the limited possibilities of consumable-type Items.
 */
public enum Consumable implements Item {
    POTION("potion"),
    BEER("beer"),
    COFFEE("coffee"),
    KEY("key"),
    KNIFE("knife"),
    UNKNOWN("");

    /**
     * All consumables have a string containing the name of the item.
     */
    public String name;

    /**
     * Constructor for the Consumables.
     * @param item - the name of the constructed Consumable.
     */
    private Consumable(String item) {
        this.name = item;
    }

    /**
     * Overrides toString from the Object-class. Describes how a Consumable is converted to a String.
     * @return the name.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Accessor for the name of the Consumable
     * @return the name of item.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * If the consumable is movable it returns true.
     * @return true - all consumable items are movable.
     */
    @Override
    public boolean isMovable() {
        return true;
    }
}