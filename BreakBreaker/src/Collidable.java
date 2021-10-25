/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 2
 * @since 2020-05-06
 */
public interface Collidable {
    /**
     * getCollisionRectangle.
     * @return - "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit -  Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint - collisiontPoint.
     * @param currentVelocity - currentVelocity.
     * @param hitter - the ball that hit.
     * @return - the new velocity.
     */
        Velocity hit(Ball hitter , Point collisionPoint, Velocity currentVelocity);
    }

