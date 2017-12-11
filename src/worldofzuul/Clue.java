
package worldofzuul2;


public enum Clue implements Item {
    KNIFE("knife"),
    FINGERPRINT("fingerprint"),
    FOOTPRINT("footprint"),
    CLOTHES("bloody clothes"),
    VICTIM("victim"),
    WITNESS("witness"),
    UNKNOWN(""),
    LAMP("lamp");
    
    private final String clue;

    private Clue(String clue) {
        this.clue = clue;
    }

    @Override
    public String toString() {
        return this.clue;
    }
    
    @Override
    public String getName() {
        return clue;
    }

    @Override
    public boolean isMovable() {
        return false;
    }

}
