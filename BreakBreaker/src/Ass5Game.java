/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 2
 * @since 2020-05-06
 */
public class Ass5Game {
    /**
     * main - create a new game init the game and then run the game.
     * @param args - none.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
