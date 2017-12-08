package worldofzuul2;

import java.io.Serializable;
import java.util.HashMap;

public class Clues implements Serializable {
	
    private final HashMap<String, Clue> listOfClues;
	
	public Clues() {
        listOfClues = new HashMap<String, Clue>();
        for(Clue clue : Clue.values()) {
            if(clue != Clue.UNKNOWN) {
                listOfClues.put(clue.toString(), clue);
            }
        }
	}
	
	public Clue getClue(String clue)
    {
        Clue temp = listOfClues.get(clue);
        if(temp != null) {
            return temp;
        }
        else {
            return Clue.UNKNOWN;
        }
    }
	
	public boolean isClue(String aString)
    {
        return listOfClues.containsKey(aString);
    }
}