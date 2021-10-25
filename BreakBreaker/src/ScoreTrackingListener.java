/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * ScoreTrackingListener - constructor.
     * @param scoreCounter - the score of the player.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent - add points to the player after hitting a block.
     * @param beingHit - the block that been hit.
     * @param hitter - the ball the hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
