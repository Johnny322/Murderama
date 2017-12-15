package worldofzuul2;

import java.util.HashMap;

/**
 * Class for describing Consumable-type Items.
 */
public class Consumables {

    /**
     * HashMap containing the consumables.
     */
    private HashMap<String, Consumable> listOfConsumables;
    
/**
 * Constructor which is used to instantiate Consumable.
 */
    public Consumables() {
        listOfConsumables = new HashMap<String, Consumable>();
        for (Consumable consumable : Consumable.values()) {
            if (consumable != Consumable.UNKNOWN) {
                listOfConsumables.put(consumable.toString(), consumable);
            }
        }
    }
    
/**
 * Accessor which accesses the Consumable from listOfConsumables.
 * @param consumable - a String indicating the wanted Consumable.
 * @return it is the Consumable from the list.
 */
    public Consumable getConsumable(String consumable) {
        Consumable temp = listOfConsumables.get(consumable.toLowerCase());
        if (temp != null) {
            return temp;
        } else {
            return Consumable.UNKNOWN;
        }
    }
    
/**
 * Checks if the given key for a Consumable is contained on the list of existing Consumables.
 * @param aString it is the key.
 * @return true if the String represents an existing Consumable.
 */
    public boolean isItem(String aString) {
        return listOfConsumables.containsKey(aString);
    }
}