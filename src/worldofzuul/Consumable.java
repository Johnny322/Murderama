
package worldofzuul2;

public enum Consumable implements Item {
    POTION("potion"),
    BEER("beer"),
    COFFEE("coffee"),
    KEY("key"),
    UNKNOWN("");
    

    public String name;

    private Consumable(String item) {
        this.name = item;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isMovable() {
        return true;
    }
}
