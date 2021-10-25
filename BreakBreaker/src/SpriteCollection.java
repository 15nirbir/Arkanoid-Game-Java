import biuoop.DrawSurface;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class SpriteCollection {
    private java.util.List<Sprite> sprites;

    /**
     * SpriteCollection - constructor.
     */
    public SpriteCollection() {
        this.sprites = new LinkedList<>();
    }

    /**
     * addSprite - add sprite to the game.
     * @param s - sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * notifyAllTimePassed - call all the sprite timePassed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteCopy = new LinkedList<Sprite>(this.sprites);
        for (Sprite sprite : spriteCopy) {
            sprite.timePassed();
        }
    }

    /**
     * drawAllOn - call drawOn(d) on all sprites.
     * @param d - draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spriteCopy = new LinkedList<Sprite>(this.sprites);
        for (Sprite sprite : spriteCopy) {
            sprite.drawOn(d);
        }
    }

    /**
     * removeSprite - remove a sprite from the list.
     * @param sprite - the sprite to be remove.
     */
    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }

}