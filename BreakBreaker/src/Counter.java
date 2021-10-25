/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class Counter {
    private int counter;

    /**
     *Counter - constructor.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * increase.
     * @param number - add this number.
     */
   public void increase(int number) {
        counter += number;
   }

    /**
     * decrease.
     * @param number - the numbeer to decrease.
     */
   public void decrease(int number) {
        counter -= number;
   }

    /**
     * getValue - getter.
     * @return - counter.
     */
   public int getValue() {
        return counter;
   }
}