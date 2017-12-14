package worldofzuul2;

import java.util.HashMap;

public class Consumables {

    private HashMap<String, Consumable> listOfConsumables;
/**
 * Constructor which is used instantiate Consumable.
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
 * Accessor which accesses the Consumable from listOfConsumables
 * @param Consumable - it is a Consumable from the list.
 * @return it is the Consumable from the list.
 * 
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
 * It clue is in the list by checking for key(Consumable).
 * @param aString it is the key.
 * @return it returns the Consumable.
 */
    public boolean isItem(String aString) {
        return listOfConsumables.containsKey(aString);
    }
}