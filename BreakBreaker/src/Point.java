/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */

public class Point {
    //Coordinates of the point.
    private double x;
    private double y;

    /**
     * Point-constructor.
     * @param x -The first coordinate.
     * @param y -The second coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance - calculate the distance of this point to the other point.
     * @param other -the second point.
     * @return - the distance between the points.
     */
    public double distance(Point other) {
        return Math.sqrt(((other.x - this.x) * (other.x - this.x)) + ((other.y - this.y) * (other.y - this.y)));
    }

    /**
     * equals- check if two points are identical.
     * @param other - the second point.
     * @return - true if the points equal.otherwise return false.
     */
    public boolean equals(Point other) {
        if (other.x == this.x && other.y == this.y) {
            return true;
        }
        return false;
    }

    /**
     * getX.
     * @return -the x value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY.
     * @return -the y value of the point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * toString-override the default toString.
     * @return -the string form of a point.
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
