
package worldofzuul2;

public enum Item {
    POTION("potion"),
    BEER("beer"),
    COFFEE("coffee"),
    UNKNOWN("");
    

    private String item;

    private Item(String item) {
        this.item = item;
    }

    public String toString() {
        return this.item;
    }
}
