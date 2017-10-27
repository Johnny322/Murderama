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
public enum Clue {

    KNIFE("knife"),
    FINGERPRINT("fingerprint"),
    FOOTPRINT("footprint"),
    CLOTHES("bloody clothes"),
    VICTIM("victim"),
    WITNESS("witness"),
    UNKNOWN(""),
    LAMP("lamp");
    
    private String clue;

    private Clue(String clue) {
        this.clue = clue;
    }

    public String toString() {
        return this.clue;
    }
} 

