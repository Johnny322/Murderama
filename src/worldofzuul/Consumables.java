package worldofzuul2;

import java.io.Serializable;
import java.util.HashMap;

public class Consumables implements Serializable{

    private HashMap<String, Consumable> listOfConsumables;

    public Consumables() {
        listOfConsumables = new HashMap<String, Consumable>();
        for (Consumable consumable : Consumable.values()) {
            if (consumable != Consumable.UNKNOWN) {
                listOfConsumables.put(consumable.toString(), consumable);
            }
        }
    }

    public Consumable getConsumable(String consumable) {
        Consumable temp = listOfConsumables.get(consumable.toLowerCase());
        if (temp != null) {
            return temp;
        } else {
            return Consumable.UNKNOWN;
        }
    }

    public boolean isItem(String aString) {
        return listOfConsumables.containsKey(aString);
    }
}
