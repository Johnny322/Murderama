package worldofzuul2;

import java.util.HashMap;

public class Items {
	
    private HashMap<String, Item> listOfItems;
	
	public Items() {
		listOfItems = new HashMap<String, Item>();
        for(Item item : Item.values()) {
            if(item != Item.UNKNOWN) {
                listOfItems.put(item.toString(), item);
            }
        }
	}
	
	public Item getItem(String item)
    {
        Item temp = listOfItems.get(item);
        if(temp != null) {
            return temp;
        }
        else {
            return Item.UNKNOWN;
        }
    }
	
	public boolean isItem(String aString)
    {
        return listOfItems.containsKey(aString);
    }
}