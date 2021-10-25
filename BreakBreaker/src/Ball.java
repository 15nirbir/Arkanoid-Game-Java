import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class Ball implements Sprite {
    private static final int X_START_OF_DRAW_SURFACE = 0;
    private static final int Y_START_OF_DRAW_SURFACE = 0;
    private Velocity v = new Velocity(0 , 0);
   private  GameEnvironment gameEnvironment;
    private  Point point;
    private  int radius;
    private java.awt.Color color;

    /**
     * setGameEnvironment.
     * @param g - the new game environment.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.gameEnvironment = g;
    }

    /**
     * Ball - Constructor.
     * @param gameEnvironment - gameEnvironment
     * @param point - point
     * @param radius - radius
     * @param color - color
     * @param v - velocity
     */
    public Ball(GameEnvironment gameEnvironment, Point point, int radius, Color color , Velocity v) {
        this.gameEnvironment = gameEnvironment;
        this.point = point;
        this.radius = radius;
        this.color = color;
        this.v = v;
    }

    /**
     * getGameEnvironment.
     * @return - gameEnvironment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Ball-constructor.
     * @param center - center of the ball.
     * @param r - radius of the ball.
     * @param color - coor of the ball.
     */
    public Ball(Point center , int r , java.awt.Color color) {
        this.point = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Ball-constructor.
     * @param x - x center point coordinate.
     * @param y - y center point coordinate.
     * @param r - radius of the ball.
     * @param color - color of the ball.
     */
    public Ball(double x , double y , int r , java.awt.Color color) {
        this.point = new Point(x , y);
        this.radius = r;
        this.color = color;
    }

    /**
     * getX.
     * @return - the x coordinate of the center point.
     */
    public int getX() {
       return (int) this.point.getX();
    }

    /**
     * getY.
     * @return - the y coordinate of the center point.
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * getSize.
     * @return - the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * getColor.
     * @return - the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * setVelocity.
     * @param velocity - velocity.
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * setVelocity.
     * @param dx - the speed in the x axis.
     * @param dy - the speed in the y axis.
     */
    public void setVelocity(double dx , double dy) {
        this.v = new Velocity(dx , dy);
    }

    /**
     * getVelocity.
     * @return - the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * moveOneStep-set the point to the next point in the move.
     */


    /**
     * drawOn-draw the ball in a drawSurface.
     * @param surface - the surface that we draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX() , getY() , getSize());
    }

    /**
     * timePassed - move the ball.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * moveOneStep-check if the ball exit from the surface and update is direction to the opposite direction.
     * @param height - height of the surface.
     * @param width - width of the surface.
     */
    public  void moveOneStep(int height , int width) {
        //Check if the ball gonna exit from the right.
        if (this.point.getX() + this.radius > width) {
            setVelocity(-v.getDx() , v.getDy());
        }
        //Check if the ball gonna exit from the left.
        if (this.point.getX() - this.radius < X_START_OF_DRAW_SURFACE) {
            setVelocity(-v.getDx() , v.getDy());
        }
        //Check if the ball gonna exit from the bottom.
        if (this.point.getY() + this.radius > height) {
            setVelocity(v.getDx() ,  -v.getDy());
        }
        //Check if the ball gonna exit from the top.
        if (this.point.getY() - this.radius  < Y_START_OF_DRAW_SURFACE) {
            setVelocity(v.getDx() ,  -v.getDy());
        }
        //Update the velocity.
        this.point = this.getVelocity().applyToPoint(this.point);
    }

    /**
     * moveOneStep-check if the ball exit from the frame or interface and change direction accordingly.
     * @param height - of the frame.
     * @param width - of the frame.
     * @param xStartOfFrame - x coordinate of the left top point of the frame.
     * @param yStartOfFrame - y coordinate of the left top point of the frame.
     */
    public  void moveOneStep(int height , int width , int xStartOfFrame , int yStartOfFrame) {
        //Check if the ball gonna exit from the right frame.
        if (this.point.getX() + this.radius > width + xStartOfFrame) {
            setVelocity(-v.getDx() , v.getDy());
        }
        //Check if the ball gonna exit from the left frame.
        if (this.point.getX() - this.radius < xStartOfFrame) {
            setVelocity(-v.getDx() , v.getDy());
        }
        //Check if the ball gonna exit from the bottom frame.
        if (this.point.getY() + this.radius > height + yStartOfFrame) {
            setVelocity(v.getDx() , -v.getDy());
        }
        //Check if the ball gonna exit from the top frame.
        if (this.point.getY() - this.radius < yStartOfFrame) {
            setVelocity(v.getDx(), -v.getDy());
        }
        //Update the velocity.
        this.point = this.getVelocity().applyToPoint(this.point);
    }

    /**
     * moveOneStep - calculate the next point of the ball and set the point in the ball.
     */
    public void moveOneStep() {
        // Epsilon is the Little space between the block and the ball.
         double epsilon = 0.000000001;
         //Coordinate of the end point of the trajectory.
         double endOfTrajectoryX = point.getX() + v.getDx();
         double endOfTrajectoryY = point.getY() + v.getDy();
         try {
             //Set the trajectory.
             Line trajectory = new Line(point , new Point(endOfTrajectoryX , endOfTrajectoryY));
             //get the collision info
             CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
             //Check if collision occur, if not make regular move.
             if (collisionInfo.collisionPoint() == null) {
                 this.point = this.getVelocity().applyToPoint(new Point(this.point.getX() - epsilon
                         , this.point.getY() - epsilon));
                 //There was a hit now we check where.
             } else {
                 // Set the collision point.
                 double collisionPointX = collisionInfo.collisionPoint().getX();
                 double collisionPointY = collisionInfo.collisionPoint().getY();
                 //make a space between the object and the ball.
                 if (v.getDy() > 0) {
                     collisionPointY = collisionPointY - epsilon;
                 } else {
                     collisionPointY = collisionPointY + epsilon;
                 }
                 if (v.getDx() > 0) {
                     collisionPointX = collisionPointX - epsilon;
                 } else {
                     collisionPointX = collisionPointX + epsilon;
                 }

                 Point collisionPoint = new Point(collisionPointX , collisionPointY);
                 //Change the velocity according to the collisionPoint
                 Velocity velocity = collisionInfo.collisionObject().hit(this , collisionPoint , this.v);
                 setVelocity(velocity);


             }
         } catch (Exception e) {
             System.out.println(e.getStackTrace());
         }
    }

    /**
     * addToGame.
     * @param g - game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * removeFromGame.
     * @param g - game.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

    }

