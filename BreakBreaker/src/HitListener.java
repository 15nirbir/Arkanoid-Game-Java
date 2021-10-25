/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public interface HitListener {
    /**
     * hitEvent - the action that the listener need to do.
     * @param beingHit - the block that been hit.
     * @param hitter - the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
