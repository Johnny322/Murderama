package worldofzuul2;
import java.util.ArrayList;

/**
* This is the class responsible for handling the notes that the player has available.
*/
public class Notes {

   /**
    * ArrayList containing all notes.
    */
    private ArrayList<String> notes = new ArrayList<>();
    
    /**
     * Constructor for the notes.
     */
    public Notes() {
        this.notes = new ArrayList<>();
    }
    
    /**
     * Constructor for the notes, with a note already attached.
     * @param note - a string representing the information that needs to be stored.
     */
    public Notes(String note) {
        Notes notes = new Notes();
        this.notes.add(note);
    }
    
    /**
     * Adds a new note to the list, with the name of who gave the player this information.
     * @param name - the name of the character who gave this information to the player.
     * @param note - the information to be added to the list of notes.
     */
    public void addNote(String name, String note) {
        this.notes.add(name + " said: " + note);
    }
    
    /**
     * Adds a new note to the list.
     * @param note - the information to be added to the list of notes.
     */
    public void addNote(String note) {
        this.notes.add(note);
    }
    
    /**
     * Prints the contents of the notes-list in the GUI.
     * @return a String that is printed in the GUI.
     */
    public String showNotes() {
        if (this.notes.isEmpty()) {
            return "You currently have no notes!";
        }
        String s = "";
        for (int i = 0; i < this.notes.size(); i++) {
            s = s + (i+1) + ". " + this.notes.get(i) + "\n";
        }
        return s;
    }
    
    /**
     * Accessor-method for the ArrayList containing the notes.
     * @return the list of notes.
     */
    public ArrayList<String> getNotes() {
        return this.notes;
    }
    
    /**
     * Mutator-method for the list of notes.
     * @param notes - the new list that should  be saved in the class.
     */
    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }
   
}