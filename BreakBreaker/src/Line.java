/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Line-constructor.
     * @param start - first point.
     * @param end - second point.
     * @throws Exception -if the points are equal there is no line that can be define.
     */
    public Line(Point start, Point end) throws Exception {
        //Check if the input is valid.
        if (end.equals(start)) {
            throw new Exception("With only one point it impossible to create line");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * Line-constructor.
     * @param x1 -the x of the first point.
     * @param y1 -the y of the first point.
     * @param x2 -the x of the second point.
     * @param y2 -the y of the second point.
     * @throws Exception -if the points are equal there is no line that can be define.
     */
    public Line(double x1, double y1, double x2, double y2)throws Exception {
        //Check if the input is valid.
        if (x1 == x2 && y1 == y2) {
            throw new Exception("With only one point it impossible to create line");
        }
        this.start = new Point(x1 , y1);
        this.end = new Point(x2 , y2);
    }

    /**
     * length.
     * @return - the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * slopeOfLine.
     * @return - the slope of the line.
     */
    public double slopeOfLine() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * constant.
     * @return -the c of the formula y=mx+c
     */
    public double constant() {
        if (this.start.getX() == this.end.getX()) {
            return this.start.getX();
        }
        double yCoordinate = start.getY();
        double xCoordinate = start.getX();
        return yCoordinate - xCoordinate * slopeOfLine();
    }

    /**
     * middle.
     * @return - the middle point of the line.
     */
    public Point middle() {
        double yCoordinate = (this.start.getY() + this.end.getY()) / 2;
        double xCoordinate = (this.start.getX() + this.end.getX()) / 2;
        Point middle = new Point(xCoordinate , yCoordinate);
        return middle;
    }

    /**
     * start.
     * @return - the start point.
     */
    public Point start() {
       return this.start;
    }

    /**
     * end.
     * @return - the end point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * isIntersecting.
     * @param other - the second line.
     * @return - true if the lines intersect,false otherwise.
     */
    public boolean isIntersecting(Line other) {
         return intersectionWith(other) != null;
    }

    /**
     * intersectionWith.
     * @param other - the second line.
     * @return - the intersecting point if exist,otherwise return null.
     */
    public Point intersectionWith(Line other) {
        //Check if the two lines are in the form of x=c.
        if (start.getX() == end.getX() && other.start.getX() == other.end.getX()) {
            return null;

            //Check if the source line is from the form of x=c, and the other line not.
        } else if (start.getX() == end.getX() && other.start.getX() != other.end.getX()) {
            //Calculate with the formula of y=mx+c.
            double yCoordinate = other.slopeOfLine() * start.getX() + other.constant();
            Point intersection = new Point(start.getX(), yCoordinate);
            //Check if the x coordinate of the source line is between the two points of the other line.
            if (this.start.getX() <= max(other.start.getX() , other.end.getX())
                    && this.start.getX() >= min(other.start.getX() , other.end.getX())
                    //check if the y coordinate is between the source line points.
             && yCoordinate <= max(start.getY() , end.getY()) && yCoordinate >= min(start.getY() , end.getY())
                    //check if the y coordinate is between the other line points.
            && yCoordinate <= max(other.start.getY()
                    , other.end.getY()) && yCoordinate >= min(other.start.getY() , other.end.getY())) {


                return intersection;

            } else {
                return null;
            }

            //Check if the other line is from the form of x=c, and the source line not.
        } else if (start.getX() != end.getX() && other.start.getX() == other.end.getX()) {
            double yCoordinate = slopeOfLine() * other.start.getX() + constant();
            Point intersection = new Point(other.start.getX(), yCoordinate);
            //Check if the x coordinate of other is between the 2 points of the source line.
            if (other.start.getX() <= max(start.getX(), end.getX())
                    && other.start.getX() >= min(start.getX(), end.getX())
                    //check if the y coordinate is between the source line points.
                    && yCoordinate <= max(start.getY() , end.getY()) && yCoordinate >= min(start.getY() , end.getY())
                    //check if the y coordinate is between the other line points.
                    && yCoordinate <= max(other.start.getY()
                    , other.end.getY()) && yCoordinate >= min(other.start.getY() , other.end.getY())) {

                return intersection;

            } else {
                return null;
            }

            //Check if the slopes are equal.
        } else if (slopeOfLine() == other.slopeOfLine()) {

            return null;
        }
        //Calculate the intersection point.
        double xCoordinate = (other.constant() - constant()) / (slopeOfLine() - other.slopeOfLine());
        double yCoordinate = slopeOfLine() * xCoordinate + constant();
        //Check if x coordinate is between the points of each line.
        if (xCoordinate <= max(start.getX() , end.getX()) && xCoordinate >= min(start.getX() , end.getX())
        && xCoordinate <= max(other.start.getX() , other.end.getX())
                && xCoordinate >= min(other.start.getX() , other.end.getX())
                //Check if y coordinate is between the points of each line.
        && yCoordinate <= max(start.getY() , end.getY()) && yCoordinate >= min(start.getY() , end.getY())
                && yCoordinate <= max(other.start.getY() , other.end.getY())
                && yCoordinate >= min(other.start.getY() , other.end.getY())) {
            Point intersection = new Point(xCoordinate, yCoordinate);
            return intersection;

        } else {
            return null;
        }
    }

    /**
     * equals.
     * @param other - the second point.
     * @return - true if the lines equal,otherwise false.
     */
    public boolean equals(Line other) {
        return start.equals(other.start) && end.equals(other.end) || start.equals(other.end) && end.equals(other.start);
    }

    /**
     * toString-override the default toString.
     * @return - the equation of the line in string form.
     */
    public String toString() {
        //The case of x=c.
        if (this.start.getX() == this.end.getX()) {
            return "x = " + constant();

            //The case of y=0.
        } else if (constant() == 0 && slopeOfLine() == 0) {
            return "y = " + "0";

            //The case of y=mx.
        } else if (constant() == 0 && slopeOfLine() != 0) {
            return "y = " + slopeOfLine() + "x";

            //The case of y=c.
        } else if (constant() != 0 && slopeOfLine() == 0) {
            return "y = " + constant();

            //The case of y=mx+c.
        } else {
            return "y = " + slopeOfLine() + "x + " + constant();
        }

    }

    /**
     * PointLocation-check where the point relatively to a line.
     * @param point - point to locate.
     * @param line - the line.
     * @return - string that tell where the point-above or under of on the line.
     */
    public String pointLocation(Point point , Line line) {
        if (point.getY() > line.slopeOfLine() * point.getX() + line.constant()) {
            return "The point is above the line";

        } else if (point.getY() < line.slopeOfLine() * point.getX() + line.constant()) {
            return "The point is under the line";

        } else {
            return "The point is on the line";
        }
    }

    /**
     * max.
     * @param x - x value.
     * @param y - y value.
     * @return - the max between x and y.
     */
    public double max(double x , double y) {
        if (x < y) {
            return y;

        } else {
            return x;
        }
    }

    /**
     * min.
     * @param x - x value.
     * @param y - y value.
     * @return - the min between x and y.
     */
    public double min(double x , double y) {
        if (x < y) {
            return x;

        } else {
            return y;
        }
    }

    /**
     * closestIntersectionToStartOfLine -  If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * @param rect - the rectangle to check with the line.
     * @return - the nearest intersection point to the start of the line.
     */
    // need to change the function to be modulare.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        try {
            //Create new line.
            Line line = new Line(this.start, this.end);
            // Create new list of intersection points.
            java.util.List<Point> list = rect.intersectionPoints(line);
            //If the list is empty return null
            if (list.isEmpty()) {
                return null;
            }
            //if there is only one intersection point return it.
            if (list.size() == 1) {
                return list.get(0);
            }
            //Return the nearest intersection point.
            if (list.size() == 2 && this.start.distance(list.get(0)) < this.start.distance(list.get(1))) {
                return list.get(0);
            } else {
                return list.get(1);
            }


        } catch (Exception e) {
            return null;
        }

    }
}
