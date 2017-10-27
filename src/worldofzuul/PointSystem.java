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
import java.util.*;

public class PointSystem
{

    private int points;


    public PointSystem()
    {
        this.points = 0;
    }

    //Updaterer efter hvert move
    public void updateOnAction(int update)
    {
        this.points += update;
    }
    

    //Void da det sparer kode
    void getPoints()
    {
        System.out.println("Your final score is: " + (10000 - this.points));
    }
}
