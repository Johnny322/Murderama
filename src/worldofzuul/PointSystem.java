//@Jeanjohnsen Jeanjohnsen Har lavede nogle updates for pointsystem classen og worldofzuul2
package worldofzuul2;

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
    
    public void setScoreZero (){
        this.points = 10000;
    }

    //Void da det sparer kode
    public int getPoints()
    {
        return (10000 - this.points);
    }
}