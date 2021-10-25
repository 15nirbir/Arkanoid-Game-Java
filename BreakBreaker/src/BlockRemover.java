/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * BlockRemover.
     * @param game - the game of the block.
     * @param removedBlocks - number of blocks.
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * hitEvent - remove the block from the game and update the counter.
     * @param beingHit - the block that been hit.
     * @param hitter - the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        game.removeSprite(beingHit);
        game.removeCollidable(beingHit);
        remainingBlocks.decrease(1);

    }
}