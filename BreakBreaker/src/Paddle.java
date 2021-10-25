import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class Paddle implements Sprite , Collidable {
    private biuoop.KeyboardSensor keyboard;
    private static final boolean MOVE_LEFT = true;
    private static final double PADDLE_SPEED = 7;
    private Block block;
    private  Rectangle rectangle;
    private GameEnvironment gameEnvironment;
    private Color color;

    /**
     * Paddle - constructor.
     * @param rectangle - rectangle
     * @param gui - gui.
     * @param gameEnvironment - gameEnvironment.
     * @param color - color.
     */
    public Paddle(Rectangle rectangle, biuoop.GUI gui , GameEnvironment gameEnvironment , Color color) {
        this.keyboard = gui.getKeyboardSensor();
        this.gameEnvironment = gameEnvironment;
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * moveLeft - move the paddle left.
     */
    public void moveLeft() {
        //Set the new upperLeft point of the paddle.
        Point upperLeft = new Point(this.rectangle.getUpperLeft().getX() - PADDLE_SPEED
                , this.rectangle.getUpperLeft().getY());
        //Change the location of the paddle to the left.
        this.rectangle = new Rectangle(upperLeft , this.rectangle.getWidth() , this.rectangle.getHeight());
        }
    /**
     * moveRight - move the paddle right.
     */
    public void moveRight() {
        //Set the new upperLeft point of the paddle.
        Point upperLeft = new Point(this.rectangle.getUpperLeft().getX() + PADDLE_SPEED
                , this.rectangle.getUpperLeft().getY());
        //Change the location of the paddle to the right.
        this.rectangle = new Rectangle(upperLeft , this.rectangle.getWidth() , this.rectangle.getHeight());

    }

    /**
     * isValidStep - check if the paddle can move to the direction the user pressed.
     * @param direction - true is left false is right.
     * @return - true if the move is possible else return false.
     */
    public boolean isValidStep(boolean direction) {
        //the point that the the paddle will start from after the move
        Point nextPoint;
        //if the user pressed left arrow set the nextPoint to the left.
        if (direction) {
             nextPoint = new Point(this.rectangle.getUpperLeft().getX() - PADDLE_SPEED
                     , this.rectangle.getUpperLeft().getY());
            ////if the user pressed right arrow set the nextPoint to the right.
        } else {
             nextPoint = new Point(this.rectangle.getUpperRight().getX() + PADDLE_SPEED
                     , this.rectangle.getUpperLeft().getY());
        }
        //check if the paddle hit the sides ,  if yes then return false.
        for (Collidable collidable: this.gameEnvironment.getCollidables()) {
            Rectangle newRectangle = collidable.getCollisionRectangle();
            if (newRectangle.isInsideTheRectangle(nextPoint)) {
                return false;
            }
        }
        //The paddle dont touch the sides.
        return true;
    }

    /**
     * movePaddle - check if the user pressed the right or the left arrow.
     * if the answer is yes and the paddle can stay in the area of the game then move the paddle accordingly.
     */
    public void movePaddle() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && isValidStep(MOVE_LEFT)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY) && isValidStep(!MOVE_LEFT)) {
            moveRight();
        }
    }

    /**
     * timePassed - move the paddle.
     */
    public void timePassed() {
        movePaddle();
    }

    /**
     * drawOn - draw the paddle.
     * @param d - draw surface.
     */
    public void drawOn(DrawSurface d) {
        //Set the color of the paddle
        d.setColor(this.color);
        //Fill the paddle.
        d.fillRectangle((int) rectangle.getUpperLeft().getX() , (int) rectangle.getUpperLeft().getY()
                , (int) rectangle.getWidth() , (int) rectangle.getHeight());
        //Set the frame color.
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rectangle.getUpperLeft().getX() , (int) rectangle.getUpperLeft().getY()
                , (int) rectangle.getWidth() , (int) rectangle.getHeight());

    }

    /**
     * getCollisionRectangle.
     * @return - return the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * hit - calculate the new velocity after collision.
     * @param collisionPoint - collisionPoint.
     * @param currentVelocity - currentVelocity.
     * @param hitter - the ball that hit the block.
     * @return - return the new velocity.
     */
    public Velocity hit(Ball hitter , Point collisionPoint, Velocity currentVelocity) {
        double xUpperLeft = this.rectangle.getUpperLeft().getX();
        double width = this.rectangle.getWidth();
        //in case the ball hit the first part of the paddle change is angle to 300 degree.
        if (collisionPoint.getX() >= xUpperLeft && collisionPoint.getX() < xUpperLeft + (width / 5)) {
            return  Velocity.fromAngleAndSpeed(300 , currentVelocity.getSpeed());
            //in case the ball hit the second part of the paddle change is angle to 330 degree.
        } else if (collisionPoint.getX() >= xUpperLeft
                + ((width / 5)) && collisionPoint.getX() < xUpperLeft + ((2 * width / 5))) {
            return  Velocity.fromAngleAndSpeed(330 , currentVelocity.getSpeed());
            //in case the ball hit the third part of the paddle change is direction like collision with any collidable.
        } else if (collisionPoint.getX() >= xUpperLeft
                + ((2 * width / 5) * width) && collisionPoint.getX() < xUpperLeft + ((3 * width / 5))) {
            return this.block.hit(hitter , collisionPoint , currentVelocity);
            //in case the ball hit the 4 part of the paddle change is angle to 30 degree.
        } else if (collisionPoint.getX() >= xUpperLeft
                + ((3 * width / 5)) && collisionPoint.getX() < xUpperLeft + ((4 * width / 5))) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            //in case the ball hit the 5 part of the paddle change is angle to 60 degree.
        } else {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
    }


    /**
     * addToGame - add the paddle to the game.
     * @param g - game.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
