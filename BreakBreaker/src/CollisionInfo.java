/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * CollisionInfo - constructor.
     * @param collisionPoint - collisionPoint.
     * @param collidable - collidable.
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    /**
     * collisionPoint - the point at which the collision occurs.
     * @return - collisionPoint.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * collisionObject - the object that we collide with.
     * @return - collidable.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}