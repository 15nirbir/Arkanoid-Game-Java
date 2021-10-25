/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Velocity.
     * @param dx - speed in x axis.
     * @param dy - speed in y axis.
     */
    public Velocity(double dx , double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * getDx.
     * @return - dx.
     */
    public double getDx() {
        return dx;
    }

    /**
     * getDy.
     * @return - dy.
     */
    public double getDy() {
        return dy;
    }

    /**
     * applyToPoint-Take a point with position (x,y) and
     * return a new point with position (x+dx, y+dy).
     * @param p - point we want to change.
     * @return - the changed point.
     */
    public Point applyToPoint(Point p) {
        double xCoordinate = p.getX();
        double yCoordinate = p.getY();
        return new Point(xCoordinate + this.dx  , yCoordinate + this.dy);
    }

    /**
     * fromAngleAndSpeed-take velocity in form of angle and speed and return the velocity as (dx,dy).
     * @param angle -of the speed.
     * @param speed - of the object.
     * @return - the velocity in dx dy.
     */
    public static Velocity fromAngleAndSpeed(double angle , double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx , dy);
    }

    /**
     * equals-check if two velocities are equal.
     * @param other - the second velocity.
     * @return - true if the velocities are equal and false otherwise.
     */
    public boolean equals(Velocity other) {
        if (this.dx == other.dx && this.dy == other.dy) {
            return true;
        }
        return false;
    }

    /**
     * getSpeed.
     * @return - the absolute speed of the object.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

}