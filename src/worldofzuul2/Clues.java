package worldofzuul2;

import java.util.HashMap;

/**
 * Class describing the clues that can be found in the game.
 */
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
     * Accessor which accesses the clue from listOfClues.
     *
     * @param clue - the clue that we want to access.
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
     * Checks if the clue is in the list by checking for key(clue).
     *
     * @param aString it is the key.
     * @return true if the tested key is on the list.
     */
    public boolean isClue(String aString) {
        return listOfClues.containsKey(aString);
    }
}