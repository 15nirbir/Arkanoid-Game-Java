/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public interface HitNotifier {
    /**
     * addHitListener - add listener.
     * @param hl - hit listener.
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener - remove listener.
     * @param hl - hit listener.
     */
    void removeHitListener(HitListener hl);
}