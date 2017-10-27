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
import java.util.ArrayList;
import java.util.Scanner;

/*
This is based on an ArrayList of notes. It might be practical to make it a (Hash)Map, but we had not yet heard about Maps when I wrote this.
@author Patrick
*/
public class Notes {
    private ArrayList<String> notes = new ArrayList<>();
//    
//    /*
//    This should be called when the user wants to write a new note. The method reads what the user writes, then adds it to the list of notes.
//    @param
//    @returns
//    @throws
//     */
//    public boolean writeNewNote() {
//        Scanner in = new Scanner(System.in);
//        System.out.println("Write a new note: ");
//        this.notes.add(in.nextLine());
//        in.close();
//        return false;
//    }
//    
    /*
    Adds a new note to the notes-list.
    @param
    @returns
    @throws
     */
    public void addNote(String name, String note) {
        this.notes.add(name + " said: " + note);
    }
    
    /*
    Prints the contents of the notes-list in the console.
    @param
    @returns
    @throws
     */
    public boolean showNotes() {
        for (int i = 0; i < this.notes.size(); i++) {
            System.out.println((i+1) + ". " + this.notes.get(i));
        }
        return false;
    }
    
    /*
    Accessor-method for the notes-list.
    @param
    @returns ArrayList<String> notes.
    @throws
     */
    public ArrayList<String> getNotes() {
        return this.notes;
    }
    
    /*
    Removes the note at index i from the notes-list.
    @param
    @returns
    @throws
     */
    public void removeNote(int i) {
        this.notes.remove(i);
    }
    
    /*
    Clears the entire notes-list.
    @param
    @returns
    @throws
     */
    public void clearNotes() {
        this.notes.clear();
    }
} 

