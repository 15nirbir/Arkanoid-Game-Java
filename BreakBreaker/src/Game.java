import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
/**
 * @author Nir Birnboim <nirbirnboim@gmail.com>
 * @version 3
 * @since 2020-06-04
 */
public class Game {
    private static final int WIDTH_OF_GUI = 800;
    private static final int HEIGHT_OF_GUI = 600;
    private static final Point UPPER_LEFT_POINT_OF_GUI = new Point(0 , 0);
    private static final Point UPPER_LEFT_POINT_OF_RIGHT_SIDE = new Point(750 , 50);
    private static final Point UPPER_LEFT_POINT_OF_THE_BOTTOM = new Point(50 , 598);
    private static final Point START_POINT_OF_PADDLE = new Point(400 , 565);
    private static final Point POINT_OF_FIRST_BALL = new Point(400 , 400);
    private static final Point POINT_OF_SECOND_BALL = new Point(500 , 500);
    private static final int HEIGHT_OF_CEILING = 50;
    private static final int WIDTH_OF_THE_BOTTOM = 700;
    private static final int WIDTH_OF_SIDES = 50;
    private static final int HEIGHT_OF_THE_SIDES = 600;
    private static final int HEIGHT_OF_THE_BOTTOM = 1;
    private static final int X_LEFT_BLOCK = 700;
    private static final int Y_LEFT_BLOCK = 300;
    private static final int WIDTH_OF_BLOCK_IN_ROW = 50;
    private static final int HEIGHT_OF_BLOCK_IN_ROW = 20;
    private static final int WIDTH_OF_PADDLE = 80;
    private static final int HEIGHT_OF_PADDLE = 2;
    private static final int RADIUS = 5;
    private static final Velocity VELOCITY = new Velocity(-6 , -2);
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    /**
     * Game - Constructor.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        gui = new biuoop.GUI("game" , WIDTH_OF_GUI , HEIGHT_OF_GUI);
        blockCounter = new Counter();
        ballCounter = new Counter();
        score = new Counter();
    }

    /**
     * addCollidable.
     * @param c - the collidable to add to the list.
     */
    public void addCollidable(Collidable c) {
        this.environment.getCollidables().add(c);
    }

    /**
     * addSprite.
     * @param s - the sprite to add to the list.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * initialize - Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        BlockRemover blockRemover = new BlockRemover(this , blockCounter);
        BallRemover ballRemover = new BallRemover(ballCounter , this);
        ScoreIndicator scoreIndicator = new ScoreIndicator(score , Color.black);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);

        //Set the frame.
        this.setFrame(ballRemover);
        //Set the yellow line.
        this.setFirstRow(blockRemover , scoreTrackingListener);
        //Set the green line.
        this.setSecondRow(blockRemover , scoreTrackingListener);
        //Set the blue line.
        this.setThirdRow(blockRemover , scoreTrackingListener);
        //Set the red line.
        this.setRow4(blockRemover , scoreTrackingListener);
        //Set the purple line.
        this.setRow5(blockRemover , scoreTrackingListener);
        //Set the pink line.
        this.setRow6(blockRemover , scoreTrackingListener);
        //Set the paddle.
        blockCounter.increase(57);
        ballCounter.increase(2);
        Rectangle recOfPaddle = new Rectangle((START_POINT_OF_PADDLE) , WIDTH_OF_PADDLE , HEIGHT_OF_PADDLE);
        Paddle paddle = new Paddle(recOfPaddle , gui , environment , Color.BLUE);
        paddle.addToGame(this);
        //Set the balls.

        Ball ball1 = new Ball(this.environment , POINT_OF_FIRST_BALL , RADIUS , Color.BLACK , VELOCITY);
        ball1.addToGame(this);
        Ball ball2 = new Ball(this.environment , POINT_OF_SECOND_BALL , RADIUS , Color.BLACK , VELOCITY);
        ball2.addToGame(this);
        scoreIndicator.addToGame(this);

    }

    /**
     * setFrame - put the blocks of the frame in the game.
     * @param ballRemover - listen to the death block.
     */
    public void setFrame(BallRemover ballRemover) {
       Rectangle rectangle = new Rectangle(UPPER_LEFT_POINT_OF_GUI , WIDTH_OF_GUI , HEIGHT_OF_CEILING);
        Block block1 = new Block(rectangle , Color.GRAY);
        Rectangle rectangle2 = new Rectangle(UPPER_LEFT_POINT_OF_GUI , WIDTH_OF_SIDES , HEIGHT_OF_THE_SIDES);
        Block block2 = new Block(rectangle2 , Color.GRAY);
        Rectangle rectangle3 = new Rectangle(UPPER_LEFT_POINT_OF_RIGHT_SIDE ,  WIDTH_OF_SIDES , HEIGHT_OF_THE_SIDES);
        Block block3 = new Block(rectangle3 , Color.GRAY);
        Rectangle rectangle4 = new Rectangle(UPPER_LEFT_POINT_OF_THE_BOTTOM , WIDTH_OF_THE_BOTTOM
                , HEIGHT_OF_THE_BOTTOM);
        Block block4 = new Block(rectangle4 , Color.white);
        block4.addHitListener(ballRemover);
        block1.addToGame(this);
        block2.addToGame(this);
        block3.addToGame(this);
        block4.addToGame(this);
    }
    /**
     * setFirstRow - put the blocks of the first row in the game.
     * @param blockRemover - listen to all blocks.
     * @param scoreTrackingListener - listen to all blocks.
     */
    public void setFirstRow(BlockRemover blockRemover , ScoreTrackingListener scoreTrackingListener) {

        Rectangle rectangle5 = new Rectangle(new Point(X_LEFT_BLOCK, Y_LEFT_BLOCK)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block5 = new Block(rectangle5 , Color.YELLOW);
        block5.addHitListener(blockRemover);
        block5.addHitListener(scoreTrackingListener);
        Rectangle rectangle6 = new Rectangle(new Point(X_LEFT_BLOCK - WIDTH_OF_BLOCK_IN_ROW , Y_LEFT_BLOCK)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block6 = new Block(rectangle6 , Color.YELLOW);
        block6.addHitListener(blockRemover);
        block6.addHitListener(scoreTrackingListener);
        Rectangle rectangle7 = new Rectangle(new Point(X_LEFT_BLOCK - 2 * WIDTH_OF_BLOCK_IN_ROW , Y_LEFT_BLOCK)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block7 = new Block(rectangle7 , Color.YELLOW);
        block7.addHitListener(blockRemover);
        block7.addHitListener(scoreTrackingListener);
        Rectangle rectangle8 = new Rectangle(new Point(X_LEFT_BLOCK - 3 * WIDTH_OF_BLOCK_IN_ROW , Y_LEFT_BLOCK)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block8 = new Block(rectangle8 , Color.YELLOW);
        block8.addHitListener(blockRemover);
        block8.addHitListener(scoreTrackingListener);
        Rectangle rectangle9 = new Rectangle(new Point(X_LEFT_BLOCK - 4 * WIDTH_OF_BLOCK_IN_ROW , Y_LEFT_BLOCK)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block9 = new Block(rectangle9 , Color.YELLOW);
        block9.addHitListener(blockRemover);
        block9.addHitListener(scoreTrackingListener);
        Rectangle rectangle10 = new Rectangle(new Point(X_LEFT_BLOCK - 5 * WIDTH_OF_BLOCK_IN_ROW , Y_LEFT_BLOCK)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block10 = new Block(rectangle10 , Color.YELLOW);
        block10.addHitListener(blockRemover);
        block10.addHitListener(scoreTrackingListener);
        Rectangle rectangle11 = new Rectangle(new Point(X_LEFT_BLOCK - 6 * WIDTH_OF_BLOCK_IN_ROW , Y_LEFT_BLOCK)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block11 = new Block(rectangle11 , Color.YELLOW);
        block11.addHitListener(blockRemover);
        block11.addHitListener(scoreTrackingListener);
        block5.addToGame(this);
        block6.addToGame(this);
        block7.addToGame(this);
        block8.addToGame(this);
        block9.addToGame(this);
        block10.addToGame(this);
        block11.addToGame(this);
    }
    /**
     * setSecondRow - put the blocks of the second row in the game.
     * @param blockRemover - listen to all blocks.
     * @param scoreTrackingListener - listen to all blocks.
     */
    public void setSecondRow(BlockRemover blockRemover , ScoreTrackingListener scoreTrackingListener) {
        Rectangle rectangle12 = new Rectangle(new Point(X_LEFT_BLOCK , Y_LEFT_BLOCK - HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block12 = new Block(rectangle12 , Color.GREEN);
        block12.addHitListener(blockRemover);
        block12.addHitListener(scoreTrackingListener);
        Rectangle rectangle13 = new Rectangle(new Point(X_LEFT_BLOCK - WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block13 = new Block(rectangle13 , Color.GREEN);
        block13.addHitListener(blockRemover);
        block13.addHitListener(scoreTrackingListener);
        Rectangle rectangle14 = new Rectangle(new Point(X_LEFT_BLOCK - 2 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block14 = new Block(rectangle14 , Color.GREEN);
        block14.addHitListener(blockRemover);
        block14.addHitListener(scoreTrackingListener);
        Rectangle rectangle15 = new Rectangle(new Point(X_LEFT_BLOCK - 3 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block15 = new Block(rectangle15 , Color.GREEN);
        block15.addHitListener(blockRemover);
        block15.addHitListener(scoreTrackingListener);
        Rectangle rectangle16 = new Rectangle(new Point(X_LEFT_BLOCK - 4 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block16 = new Block(rectangle16 , Color.GREEN);
        block16.addHitListener(blockRemover);
        block16.addHitListener(scoreTrackingListener);
        Rectangle rectangle17 = new Rectangle(new Point(X_LEFT_BLOCK - 5 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block17 = new Block(rectangle17 , Color.GREEN);
        block17.addHitListener(blockRemover);
        block17.addHitListener(scoreTrackingListener);
        Rectangle rectangle18 = new Rectangle(new Point(X_LEFT_BLOCK - 6 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block18 = new Block(rectangle18 , Color.GREEN);
        block18.addHitListener(blockRemover);
        block18.addHitListener(scoreTrackingListener);
        Rectangle rectangle19 = new Rectangle(new Point(X_LEFT_BLOCK - 7 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block19 = new Block(rectangle19 , Color.GREEN);
        block19.addHitListener(blockRemover);
        block19.addHitListener(scoreTrackingListener);
        block12.addToGame(this);
        block13.addToGame(this);
        block14.addToGame(this);
        block15.addToGame(this);
        block16.addToGame(this);
        block17.addToGame(this);
        block18.addToGame(this);
        block19.addToGame(this);
    }

    /**
     * setThirdRow - put the blocks of the third row in the game.
     * @param blockRemover - listen to all blocks.
     * @param scoreTrackingListener - listen to all blocks.
     */
    public void setThirdRow(BlockRemover blockRemover , ScoreTrackingListener scoreTrackingListener) {
        Rectangle rectangle20 = new Rectangle(new Point(X_LEFT_BLOCK , Y_LEFT_BLOCK - 2 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block20 = new Block(rectangle20 , Color.BLUE);
        block20.addHitListener(blockRemover);
        block20.addHitListener(scoreTrackingListener);
        Rectangle rectangle21 = new Rectangle(new Point(X_LEFT_BLOCK - WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 2 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block21 = new Block(rectangle21 , Color.BLUE);
        block21.addHitListener(blockRemover);
        block21.addHitListener(scoreTrackingListener);
        Rectangle rectangle22 = new Rectangle(new Point(X_LEFT_BLOCK - 2 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 2 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block22 = new Block(rectangle22 , Color.BLUE);
        block22.addHitListener(blockRemover);
        block22.addHitListener(scoreTrackingListener);
        Rectangle rectangle23 = new Rectangle(new Point(X_LEFT_BLOCK - 3 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 2 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block23 = new Block(rectangle23 , Color.BLUE);
        block23.addHitListener(blockRemover);
        block23.addHitListener(scoreTrackingListener);
        Rectangle rectangle24 = new Rectangle(new Point(X_LEFT_BLOCK - 4 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 2 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block24 = new Block(rectangle24 , Color.BLUE);
        block24.addHitListener(blockRemover);
        block24.addHitListener(scoreTrackingListener);
        Rectangle rectangle25 = new Rectangle(new Point(X_LEFT_BLOCK - 5 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 2 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block25 = new Block(rectangle25 , Color.BLUE);
        block25.addHitListener(blockRemover);
        block25.addHitListener(scoreTrackingListener);
        Rectangle rectangle26 = new Rectangle(new Point(X_LEFT_BLOCK - 6 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 2 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block26 = new Block(rectangle26 , Color.BLUE);
        block26.addHitListener(blockRemover);
        block26.addHitListener(scoreTrackingListener);
        Rectangle rectangle27 = new Rectangle(new Point(X_LEFT_BLOCK - 7 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 2 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block27 = new Block(rectangle27 , Color.BLUE);
        block27.addHitListener(blockRemover);
        block27.addHitListener(scoreTrackingListener);
        Rectangle rectangle28 = new Rectangle(new Point(X_LEFT_BLOCK - 8 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 2 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block28 = new Block(rectangle28 , Color.BLUE);
        block28.addHitListener(blockRemover);
        block28.addHitListener(scoreTrackingListener);
        block20.addToGame(this);
        block21.addToGame(this);
        block22.addToGame(this);
        block23.addToGame(this);
        block24.addToGame(this);
        block25.addToGame(this);
        block26.addToGame(this);
        block27.addToGame(this);
        block28.addToGame(this);
    }
    /**
     * setRow4 - put the blocks of row 4 in the game.
     * @param blockRemover - listen to all blocks.
     * @param scoreTrackingListener - listen to all blocks.
     */
    public void setRow4(BlockRemover blockRemover , ScoreTrackingListener scoreTrackingListener) {
        Rectangle rectangle29 = new Rectangle(new Point(X_LEFT_BLOCK , Y_LEFT_BLOCK - 3 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block29 = new Block(rectangle29 , Color.RED);
        block29.addHitListener(blockRemover);
        block29.addHitListener(scoreTrackingListener);
        Rectangle rectangle30 = new Rectangle(new Point(X_LEFT_BLOCK - WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 3 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block30 = new Block(rectangle30 , Color.RED);
        block30.addHitListener(blockRemover);
        block30.addHitListener(scoreTrackingListener);
        Rectangle rectangle31 = new Rectangle(new Point(X_LEFT_BLOCK - 2 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 3 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block31 = new Block(rectangle31 , Color.RED);
        block31.addHitListener(blockRemover);
        block31.addHitListener(scoreTrackingListener);
        Rectangle rectangle32 = new Rectangle(new Point(X_LEFT_BLOCK - 3 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 3 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block32 = new Block(rectangle32 , Color.RED);
        block32.addHitListener(blockRemover);
        block32.addHitListener(scoreTrackingListener);
        Rectangle rectangle33 = new Rectangle(new Point(X_LEFT_BLOCK - 4 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 3 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block33 = new Block(rectangle33 , Color.RED);
        block33.addHitListener(blockRemover);
        block33.addHitListener(scoreTrackingListener);
        Rectangle rectangle34 = new Rectangle(new Point(X_LEFT_BLOCK - 5 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 3 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block34 = new Block(rectangle34 , Color.RED);
        block34.addHitListener(blockRemover);
        block34.addHitListener(scoreTrackingListener);
        Rectangle rectangle35 = new Rectangle(new Point(X_LEFT_BLOCK - 6 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 3 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block35 = new Block(rectangle35 , Color.RED);
        block35.addHitListener(blockRemover);
        block35.addHitListener(scoreTrackingListener);
        Rectangle rectangle36 = new Rectangle(new Point(X_LEFT_BLOCK - 7 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 3 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block36 = new Block(rectangle36 , Color.RED);
        block36.addHitListener(blockRemover);
        block36.addHitListener(scoreTrackingListener);
        Rectangle rectangle37 = new Rectangle(new Point(X_LEFT_BLOCK - 8 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 3 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block37 = new Block(rectangle37 , Color.RED);
        block37.addHitListener(blockRemover);
        block37.addHitListener(scoreTrackingListener);
        Rectangle rectangle38 = new Rectangle(new Point(X_LEFT_BLOCK - 9 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 3 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block38 = new Block(rectangle38 , Color.RED);
        block38.addHitListener(blockRemover);
        block38.addHitListener(scoreTrackingListener);
        block29.addToGame(this);
        block30.addToGame(this);
        block31.addToGame(this);
        block32.addToGame(this);
        block33.addToGame(this);
        block34.addToGame(this);
        block35.addToGame(this);
        block36.addToGame(this);
        block37.addToGame(this);
        block38.addToGame(this);
    }
    /**
     * setRow5 - put the blocks of row 5 in the game.
     * @param blockRemover - listen to all blocks.
     * @param scoreTrackingListener - listen to all blocks.
     */
    public void setRow5(BlockRemover blockRemover , ScoreTrackingListener scoreTrackingListener) {
        Rectangle rectangle39 = new Rectangle(new Point(X_LEFT_BLOCK , Y_LEFT_BLOCK - 4 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block39 = new Block(rectangle39 , Color.CYAN);
        block39.addHitListener(blockRemover);
        block39.addHitListener(scoreTrackingListener);
        Rectangle rectangle40 = new Rectangle(new Point(X_LEFT_BLOCK - WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 4 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block40 = new Block(rectangle40 , Color.CYAN);
        block40.addHitListener(blockRemover);
        block40.addHitListener(scoreTrackingListener);
        Rectangle rectangle41 = new Rectangle(new Point(X_LEFT_BLOCK - 2 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 4 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block41 = new Block(rectangle41 , Color.CYAN);
        block41.addHitListener(blockRemover);
        block41.addHitListener(scoreTrackingListener);
        Rectangle rectangle42 = new Rectangle(new Point(X_LEFT_BLOCK - 3 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 4 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block42 = new Block(rectangle42 , Color.CYAN);
        block42.addHitListener(blockRemover);
        block42.addHitListener(scoreTrackingListener);
        Rectangle rectangle43 = new Rectangle(new Point(X_LEFT_BLOCK - 4 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 4 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block43 = new Block(rectangle43 , Color.CYAN);
        block43.addHitListener(blockRemover);
        block43.addHitListener(scoreTrackingListener);
        Rectangle rectangle44 = new Rectangle(new Point(X_LEFT_BLOCK - 5 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 4 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block44 = new Block(rectangle44 , Color.CYAN);
        block44.addHitListener(blockRemover);
        block44.addHitListener(scoreTrackingListener);
        Rectangle rectangle45 = new Rectangle(new Point(X_LEFT_BLOCK - 6 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 4 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block45 = new Block(rectangle45 , Color.CYAN);
        block45.addHitListener(blockRemover);
        block45.addHitListener(scoreTrackingListener);
        Rectangle rectangle46 = new Rectangle(new Point(X_LEFT_BLOCK - 7 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 4 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block46 = new Block(rectangle46 , Color.CYAN);
        block46.addHitListener(blockRemover);
        block46.addHitListener(scoreTrackingListener);
        Rectangle rectangle47 = new Rectangle(new Point(X_LEFT_BLOCK - 8 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 4 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block47 = new Block(rectangle47 , Color.CYAN);
        block47.addHitListener(blockRemover);
        block47.addHitListener(scoreTrackingListener);
        Rectangle rectangle48 = new Rectangle(new Point(X_LEFT_BLOCK - 9 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 4 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block48 = new Block(rectangle48 , Color.CYAN);
        block48.addHitListener(blockRemover);
        block48.addHitListener(scoreTrackingListener);
        Rectangle rectangle49 = new Rectangle(new Point(X_LEFT_BLOCK - 10 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 4 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block49 = new Block(rectangle49 , Color.CYAN);
        block49.addHitListener(blockRemover);
        block49.addHitListener(scoreTrackingListener);
        block39.addToGame(this);
        block40.addToGame(this);
        block41.addToGame(this);
        block42.addToGame(this);
        block43.addToGame(this);
        block44.addToGame(this);
        block45.addToGame(this);
        block46.addToGame(this);
        block47.addToGame(this);
        block48.addToGame(this);
        block49.addToGame(this);
    }

    /**
     * setRow6 - put the blocks of row 6 in the game.
     * @param blockRemover - listen to all blocks.
     * @param scoreTrackingListener - listen to all blocks.
     */
    public void setRow6(BlockRemover blockRemover , ScoreTrackingListener scoreTrackingListener) {

        Rectangle rectangle50 = new Rectangle(new Point(X_LEFT_BLOCK , Y_LEFT_BLOCK - 5 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block50 = new Block(rectangle50 , Color.PINK);
        block50.addHitListener(blockRemover);
        block50.addHitListener(scoreTrackingListener);
        Rectangle rectangle51 = new Rectangle(new Point(X_LEFT_BLOCK - WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 5 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block51 = new Block(rectangle51 , Color.PINK);
        block51.addHitListener(blockRemover);
        block51.addHitListener(scoreTrackingListener);
        Rectangle rectangle52 = new Rectangle(new Point(X_LEFT_BLOCK - 2 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 5 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block52 = new Block(rectangle52 , Color.PINK);
        block52.addHitListener(blockRemover);
        block52.addHitListener(scoreTrackingListener);
        Rectangle rectangle53 = new Rectangle(new Point(X_LEFT_BLOCK - 3 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 5 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block53 = new Block(rectangle53 , Color.PINK);
        block53.addHitListener(blockRemover);
        block53.addHitListener(scoreTrackingListener);
        Rectangle rectangle54 = new Rectangle(new Point(X_LEFT_BLOCK - 4 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 5 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block54 = new Block(rectangle54 , Color.PINK);
        block54.addHitListener(blockRemover);
        block54.addHitListener(scoreTrackingListener);
        Rectangle rectangle55 = new Rectangle(new Point(X_LEFT_BLOCK - 5 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 5 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block55 = new Block(rectangle55 , Color.PINK);
        block55.addHitListener(blockRemover);
        block55.addHitListener(scoreTrackingListener);
        Rectangle rectangle56 = new Rectangle(new Point(X_LEFT_BLOCK - 6 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 5 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block56 = new Block(rectangle56 , Color.PINK);
        block56.addHitListener(blockRemover);
        block56.addHitListener(scoreTrackingListener);
        Rectangle rectangle57 = new Rectangle(new Point(X_LEFT_BLOCK - 7 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 5 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block57 = new Block(rectangle57 , Color.PINK);
        block57.addHitListener(blockRemover);
        block57.addHitListener(scoreTrackingListener);
        Rectangle rectangle58 = new Rectangle(new Point(X_LEFT_BLOCK - 8 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 5 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block58 = new Block(rectangle58 , Color.PINK);
        block58.addHitListener(blockRemover);
        block58.addHitListener(scoreTrackingListener);
        Rectangle rectangle59 = new Rectangle(new Point(X_LEFT_BLOCK - 9 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 5 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block59 = new Block(rectangle59 , Color.PINK);
        block59.addHitListener(blockRemover);
        block59.addHitListener(scoreTrackingListener);
        Rectangle rectangle60 = new Rectangle(new Point(X_LEFT_BLOCK - 10 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 5 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block60 = new Block(rectangle60 , Color.PINK);
        block60.addHitListener(blockRemover);
        block60.addHitListener(scoreTrackingListener);
        Rectangle rectangle61 = new Rectangle(new Point(X_LEFT_BLOCK - 11 * WIDTH_OF_BLOCK_IN_ROW
                , Y_LEFT_BLOCK - 5 * HEIGHT_OF_BLOCK_IN_ROW)
                , WIDTH_OF_BLOCK_IN_ROW , HEIGHT_OF_BLOCK_IN_ROW);
        Block block61 = new Block(rectangle61 , Color.PINK);
        block61.addHitListener(blockRemover);
        block61.addHitListener(scoreTrackingListener);
        block50.addToGame(this);
        block51.addToGame(this);
        block52.addToGame(this);
        block53.addToGame(this);
        block54.addToGame(this);
        block55.addToGame(this);
        block56.addToGame(this);
        block57.addToGame(this);
        block58.addToGame(this);
        block59.addToGame(this);
        block60.addToGame(this);
        block61.addToGame(this);
    }

    /**
     * run - Run the game -- start the animation loop.
     */
    public void run() {
        //Set the sleeper.
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        //Convert form second to millisecond.
        int millisecondsPerFrame = 1000 / framesPerSecond;
        //Run he game until exit the window.
        while (true) {
            if (blockCounter.getValue() == 0 || ballCounter.getValue() == 0) {
                gui.close();
                return;
            }
            long startTime = System.currentTimeMillis(); // timing
            // Set the window
            DrawSurface d = gui.getDrawSurface();
            //put in all the sprites and show them
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            //Recover the time that the program need to run all the processes.
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * removeCollidable.
     * @param c - the collidable to remove.
     */
   public void removeCollidable(Collidable c) {
        this.environment.removeColidable(c);
   }

    /**
     * removeSprite.
     * @param s - the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


}