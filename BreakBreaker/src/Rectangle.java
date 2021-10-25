import java.util.LinkedList;
/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class Rectangle {
    private static final int NUMBER_OF_LINES_IN_RECTANGLE = 4;
    private  Point upperLeftPoint;
    private double width;
    private  double height;


    /**
     * Rectangle - constructor Create a new rectangle with location and width/height.
     * @param upperLeft - the upper left point of the rectangle.
     * @param width - the width of the rectangle.
     * @param height - the height of the rectangle.
     */
    public Rectangle(Point upperLeft ,  double width ,  double height) {
        this.upperLeftPoint = upperLeft;
        this.width = width;
        this.height = height;

    }

    /**
     * intersectionPoints - Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line - the line to check where the intersection points with the rectangle.
     * @return - list of the intersection points between the rectangle and the line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //Create new linkedlList of points.
        java.util.List<Point> list = new LinkedList<>();
        //Set the Point of the rectangle.
        Point lowerLeftPoint = new Point(this.upperLeftPoint.getX() , this.upperLeftPoint.getY() + height);
        Point upperRightPoint = new Point(this.upperLeftPoint.getX() + width , this.upperLeftPoint.getY());
        Point lowerRightPoint = new Point(this.upperLeftPoint.getX() + width , this.upperLeftPoint.getY() + height);
        try {
            //Set the lines of the rectangle.
            Line upperOrizontalLine = new Line(this.upperLeftPoint , upperRightPoint);
            Line lowerOrizontalLine = new Line(lowerLeftPoint , lowerRightPoint);
            Line verticalLeftLine = new Line(this.upperLeftPoint , lowerLeftPoint);
            Line verticalRightLine = new Line(upperRightPoint , lowerRightPoint);
            //Array of the lines of the rectangle.
            Line [] lines = {upperOrizontalLine , lowerOrizontalLine , verticalLeftLine , verticalRightLine};
            //Iterate the lines of the rectangle.
            for (int i = 0; i < NUMBER_OF_LINES_IN_RECTANGLE; i++) {
                //Flag sign if the point is already in the list.
                int flag = 1;
                //run on the list to check if the point is already in the list.
                for (int j = 0; j < list.size(); j++) {
                    if (lines[i].isIntersecting(line) && list.get(j).equals(lines[i].intersectionWith(line))) {
                        flag = 0;
                        break;
                    }
                }
                //If there is an intersection point and it not exist in the list add the point to the list.
                if (lines[i].isIntersecting(line) && flag == 1) {
                    list.add(lines[i].intersectionWith(line));
                }
            }
            return list;
        } catch (Exception e) {
            System.out.println("problem with the line set");
            return null;
        }

    }

    /**
     * getWidth.
     * @return - width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getHeight.
     * @return - the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getUpperLeft.
     * @return - the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return  this.upperLeftPoint;
    }
    /**
     * getUpperRight.
     * @return - the upper right point of the rectangle.
     */
    public Point getUpperRight() {
         return new Point(this.upperLeftPoint.getX() + width , this.upperLeftPoint.getY());
    }
    /**
     * getLowerRight.
     * @return - the lower right point of the rectangle.
     */
    public Point getLowerRight() {
        return new Point(this.upperLeftPoint.getX() + width , this.upperLeftPoint.getY() + height);
    }
    /**
     * getLowerLeft.
     * @return - the lower left point of the rectangle.
     */
    public Point getLowerLeft() {
        return new Point(this.upperLeftPoint.getX() , this.upperLeftPoint.getY() + height);
    }

    /**
     * isInsideTheRectangle - check if a point is inside the rectangle.
     * @param point - point.
     * @return - true if the point is inside the rectangle and false otherwise.
     */
    public boolean isInsideTheRectangle(Point point) {
        double x = point.getX();
        double y = point.getY();
        //if one of those conditions happen then the point is outside the rectangle.
        if (x < upperLeftPoint.getX() || x > upperLeftPoint.getX() + this.width
                || y < upperLeftPoint.getY() || y > upperLeftPoint.getY() + this.height) {
            return false;
        }
        return true;
    }
}
