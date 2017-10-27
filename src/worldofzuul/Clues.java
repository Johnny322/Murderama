/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author aqibsajjad
 */
import java.util.HashMap;

public class Clues {
	
    private HashMap<String, Clue> listOfClues;
	
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
