import java.util.ArrayList;
import java.util.List;
/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class GameEnvironment {
    private java.util.List<Collidable> collidables;

    /**
     * GameEnvironment - constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();

    }

    /**
     * getCollidables.
     * @return - collidables.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * addCollidable -  add the given collidable to the environment.
     * @param c - collidable.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * getClosestCollision - Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory - trajectory line.
     * @return - closest line to the to the start of the trajectory.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //keep the nearest distance to the start of the line.
        Double min = null;
        //save the closest colidable.
        Collidable closesetColidable = null;
        Point collisionPoint = null;
        //iterate collidables.
        for (Collidable c : collidables) {
            //if there is an intersection point and the min has no value put the nearest intersection point there.
             if (trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()) != null && min == null) {
                  collisionPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                 min = collisionPoint.distance(trajectory.start());
                 closesetColidable = c;
            }
             //check if we get a point that closer to the start of the point.
             if (trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()) != null && min != null) {
                 Point point = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
                 if (min > point.distance(trajectory.start())) {
                     min = point.distance(trajectory.start());
                     collisionPoint = point;
                     closesetColidable = c;
                 }
             }
        }
        return new CollisionInfo(collisionPoint , closesetColidable);
    }

    /**
     * removeColidable - remove from the list.
     * @param collidable - the collidable to remove.
     */
    public void removeColidable(Collidable collidable) {
        collidables.remove(collidable);
    }

}
