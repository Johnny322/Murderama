package worldofzuul2;

public enum Consumable implements Item {
    POTION("potion"),
    BEER("beer"),
    COFFEE("coffee"),
    KEY("key"),
    UNKNOWN("");

    public String name;

    /**
     * Accessor for the Consumables.
     *
     * @param item.
     * @returns Item name.
     */
    private Consumable(String item) {
        this.name = item;
    }

    /**
     * Overrides the previous string.
     *
     * @return the name.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Accessor for the name of the Consumable
     *
     * @return the name of item.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * If the consumable is movable it returns true.
     *
     * @return true.
     */
    @Override
    public boolean isMovable() {
        return true;
    }
}