
import biuoop.DrawSurface;
/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public interface Sprite {
    /**
     * drawOn - draw the sprite to the screen.
     * @param d - draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed -notify the sprite that time has passed.
     */
    void timePassed();
}