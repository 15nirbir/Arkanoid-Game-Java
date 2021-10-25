/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class BallRemover implements HitListener {
   private Counter counter;
   private  Game game;

    /**
     * BallRemover - constructor.
     * @param counter - the ball counter.
     * @param game - the game to remove the ball.
     */
    public BallRemover(Counter counter, Game game) {
        this.counter = counter;
        this.game = game;
    }

    /**
     * hitEvent - remove the ball from game.
     * @param beingHit - the block that been hit.
     * @param hitter - the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        counter.decrease(1);
        hitter.removeFromGame(game);
    }
}
