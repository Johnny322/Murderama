package worldofzuul2;

import java.util.HashMap;

public class Clues {

    /**
     * Creates a HashMap with Strings and clues, which is called listOfClues.
     */
    private final HashMap<String, Clue> listOfClues;

    /**
     * Constructor which is used instantiate clues.
     */
    public Clues() {
        listOfClues = new HashMap<String, Clue>();
        for (Clue clue : Clue.values()) {
            if (clue != Clue.UNKNOWN) {
                listOfClues.put(clue.toString(), clue);
            }
        }
    }

    /**
     * Accessor which accesses the clue from listOfClues
     *
     * @param clue - it is a clue from the list.
     * @return it is the clue from the list.
     *
     */
    public Clue getClue(String clue) {
        Clue temp = listOfClues.get(clue);
        if (temp != null) {
            return temp;
        } else {
            return Clue.UNKNOWN;
        }
    }

    /**
     * It clue is in the list by checking for key(clue).
     *
     * @param aString it is the key.
     * @return it returns the clue.
     */
    public boolean isClue(String aString) {
        return listOfClues.containsKey(aString);
    }
}