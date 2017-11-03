
package worldofzuul2;



public enum Clue {
    KNIFE("knife"),
    FINGERPRINT("fingerprint"),
    FOOTPRINT("footprint"),
    CLOTHES("bloody clothes"),
    VICTIM("victim"),
    WITNESS("witness"),
    UNKNOWN(""),
    LAMP("lamp");
    
    private String clue;

    private Clue(String clue) {
        this.clue = clue;
    }

    public String toString() {
        return this.clue;
    }
}
