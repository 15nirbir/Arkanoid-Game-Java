import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class Block implements Collidable , Sprite , HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Block - Constructor.
     * @param rectangle - rectangle.
     * @param color - color.
     */
    public Block(Rectangle rectangle , Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * getCollisionRectangle - get the block rectangle.
     * @return - rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * hit - check in each axes if we need to change the direction of the speed.
     * @param collisionPoint - collisionPoint.
     * @param currentVelocity - currentVelocity.
     * @param hitter - the ball that hit the block.
     * @return - return the new velocity.
     */
    @Override
    public Velocity hit(Ball hitter , Point collisionPoint , Velocity currentVelocity) {
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        int flag = 1;
        //if the collision point is on one of the horizontal lines then reversed the vertical speed direction.
        if (!rectangle.isInsideTheRectangle(collisionPoint) //Check if the first point is inside and the second not.
                && rectangle.isInsideTheRectangle(new Point(collisionPoint.getX() , collisionPoint.getY() + dy))) {
            dy = -dy;
            flag = 0;
        }
        //if the collision point is on one of the vertical lines then reversed the horizontal speed direction.
        if (!rectangle.isInsideTheRectangle(collisionPoint) //Check if the first point is inside and the second not.
                && rectangle.isInsideTheRectangle(new Point(collisionPoint.getX() + dx , collisionPoint.getY()))) {
            dx = -dx;
            flag = 0;
        }
        // The case of a corner.
        if (!rectangle.isInsideTheRectangle(collisionPoint)
                && rectangle.isInsideTheRectangle(new Point(collisionPoint.getX() + dx , collisionPoint.getY() + dy))
         && flag == 1) {
            dx = -dx;
            dy = -dy;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);

    }

    /**
     * drawOn - draw the block.
     * @param drawSurface - the area to draw on.
     */
    public void drawOn(DrawSurface drawSurface) {
        //Set the color of the block
        drawSurface.setColor(this.color);
        //Fill the block.
        drawSurface.fillRectangle((int) rectangle.getUpperLeft().getX() , (int) rectangle.getUpperLeft().getY()
                , (int) rectangle.getWidth() , (int) rectangle.getHeight());
        //Set the frame color.
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawRectangle((int) rectangle.getUpperLeft().getX() , (int) rectangle.getUpperLeft().getY()
                , (int) rectangle.getWidth() , (int) rectangle.getHeight());
    }

    @Override
    public void timePassed() {

    }

    /**
     * addToGame.
     * @param g - game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * addHitListener.
     * @param hl - hit listener.
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * removeHitListener.
     * @param hl - hit listener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notifyHit.
     * @param hitter - the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
