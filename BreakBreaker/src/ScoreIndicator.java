import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class ScoreIndicator implements  Sprite {
    private Counter counter;
    private Color color;

    /**
     * scoreIndicator - constructor.
     * @param counter - the score counter.
     * @param color - the color of the text.
     */
    public ScoreIndicator(Counter counter , Color color) {
        this.counter = counter;
        this.color = color;
    }

    /**
     * drawOn - draw the score in the screen.
     * @param d - draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        String score = String.valueOf(counter.getValue());
       d.drawText(370 , 25 , "score: " + score  , 30);
    }

    /**
     * timePassed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * addToGame - add the score text to game.
     * @param g - the game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
