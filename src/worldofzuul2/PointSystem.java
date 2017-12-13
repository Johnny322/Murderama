
//@Jeanjohnsen Jeanjohnsen Har lavede nogle updates for pointsystem classen og worldofzuul2
package worldofzuul2;
import java.util.*;

/**
 * 
 * @author Asmus
 */

public class PointSystem
{
/**
 * Private int points, used to describe how many points the player has.
 */
    private int points;

/**
 * Constructer for Pointsystem.
 */
    public PointSystem()
    {
        this.points = 0;
    }

    /**
     * Method for updating after every move.
     * @param update - Points that is being added to the score.
     */
    //Updaterer efter hvert move
    public void updateOnAction(int update)
    {
        this.points += update;
    }
    
    /**
     * Setter for resting the score.
     */
    
    public void setScoreZero() {
        this.points = 10000;
    }
    
    /**
     * Accesor for the acumed points.
     * @return Returns the acumed points.
     */

    public int getPoints()
    {
        return (10000 - this.points);
    }
    
    /**
     * Setter for points.
     * @param points - what number the score should be.
     */
    
    public void setPoints(int points) {
        this.points = points;
    }
}
