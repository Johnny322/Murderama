/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul2;

import java.util.Hashtable;
import java.util.Enumeration;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

/**
 *
 * @author Simon
 */
public class WorldOfZuul2 {
    
    public static void doSave() {

        System.out.println();
        System.out.println("+------------------------------+");
        System.out.println("| doSave Method                |");
        System.out.println("+------------------------------+");
        System.out.println();
        
        Hashtable h = new Hashtable();
        h.put("Player", Player.class);
        h.put("Game", Game.class);
        h.put("double", new Double(Math.PI));

        try {

            System.out.println("Creating File/Object output stream...");
            
            FileOutputStream fileOut = new FileOutputStream("HTExample.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            System.out.println("Writing Hashtable Object...");
            out.writeObject(h);

            System.out.println("Closing all output streams...\n");
            out.close();
            fileOut.close();
            
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Loads the contents of a previously serialized object from a file called
     * HTExample.ser.
     */
    static void doLoad() {

        System.out.println();
        System.out.println("+------------------------------+");
        System.out.println("| doLoad Method                |");
        System.out.println("+------------------------------+");
        System.out.println();
        
        Hashtable h = null;


        try {

            System.out.println("Creating File/Object input stream...");
            
            FileInputStream fileIn = new FileInputStream("HTExample.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            System.out.println("Loading Hashtable Object...");
            h = (Hashtable)in.readObject();

            System.out.println("Closing all input streams...\n");
            in.close();
            fileIn.close();
           
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Printing out loaded elements...");
        for (Enumeration e = h.keys(); e.hasMoreElements(); ) {
            Object obj = e.nextElement();
            System.out.println("  - Element(" + obj + ") = " + h.get(obj));
        }
        System.out.println();

    }
    
    public static void main(String[] args)  {
        Game game = new Game();
        game.play();  
        
    }  
}
