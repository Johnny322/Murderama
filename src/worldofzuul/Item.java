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
public enum Item {
    POTION("potion"),
    BEER("beer"),
    COFFEE("coffee"),
    UNKNOWN("");
    

    private String item;

    private Item(String item) {
        this.item = item;
    }

    public String toString() {
        return this.item;
    }
}