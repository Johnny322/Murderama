
package worldofzuul2;

/**
 * Class for handling the players points.
 * @author Asmus
 */
public class PointSystem {

    /**
     * Private int points, used to describe how many points the player has.
     */
    private int points;

    /**
     * Constructor for Pointsystem.
     */
    public PointSystem() {
        this.points = 0;
    }

    /**
     * Method for updating points after every move.
     * @param update - Points that is being added to the score.
     */
    //Updaterer efter hvert move
    public void updateOnAction(int update) {
        this.points += update;
    }

    /**
     * Setter for resetting the score.
     */
    public void setScoreZero() {
        this.points = 10000;
    }

    /**
     * Accessor for the accumulated points.
     * @return the accumulated points.
     */
    public int getPoints() {
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
