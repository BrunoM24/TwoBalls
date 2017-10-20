package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.gameobjects.Brick;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;
import org.academiadecodigo.twoballs.manage.CollisionDetector;
import org.academiadecodigo.twoballs.manage.ObjectFactory;
import org.academiadecodigo.twoballs.manage.ScoreManager;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.academiadecodigo.twoballs.input.KeyboardManager.*;

/**
 * Created by codecadet on 16/10/17.
 */
public class Stage {

    public static final int PADDING = 10;

    ScoreManager scoreManager;

    //private Rectangle bkgRectangle;
    private Picture backGround;

    private Set<GameObject> gameObjects = new HashSet<>();  //elaborate a functional SET list

    private Paddle player1;

    private Paddle player2;

    private CollisionDetector collisionDetector = new CollisionDetector();

    Stage(int width, int height) {

        //this.bkgRectangle = new Rectangle(PADDING, PADDING, width, height);
        this.backGround = new Picture(PADDING, PADDING, "assets/background.jpg");
        //bkgRectangle.draw();
        this.backGround.draw();

        scoreManager = new ScoreManager();
        scoreManager.draw();

        new ObjectFactory(this);
    }

    void test() {


    }

    void initializeObjects() {

        gameObjects.add(player1 = ObjectFactory.getLeftPaddle("blue"));
        gameObjects.add(player2 = ObjectFactory.getRightPaddle("red"));
        gameObjects.add(ObjectFactory.getNewBall(GameScreen.getWidth() / 2 + 200, GameScreen.getHeight() / 2, 1, 0));
        //gameObjects.add(ObjectFactory.getNewBall(GameScreen.getWidth() / 2 - 200, GameScreen.getHeight() / 2, -1, 0));

        /*
        int numberOfBalls = 0;

        for(int i = 1; i <= numberOfBalls; i++) {

            gameObjects.add(ObjectFactory.getNewBall(250, 250, -1, -1));
        }*/

        //TODO SPAWN BRICKS
        int xRange = 5;
        int yRange = 8;
        int brickWidth = 32;
        int brickHeight = 64;
        int brickSpacing = 0;//TODO

        int initialX = (GameScreen.getWidth() - (xRange * (brickWidth + brickSpacing))) / 2;
        for(int y = 0; y < yRange; y++) {

            for(int x = 0; x < xRange; x++) {

                int dur = 0;
                if(x == 1 || x == 3) {

                    dur = 1;
                }
                else if(x == 2) {

                    dur = 2;
                }

                gameObjects.add(ObjectFactory.getNewBrick(initialX + x * (brickWidth + brickSpacing), 26 + y * (brickHeight + brickSpacing), dur));
            }
        }

        /*
        for(int y = 0; y < yRange; y++) {

            gameObjects.add(new Brick(400 + brickWidth * y + brickSpacing * y, 58, 0));

            int dur = 0;
            if(y == 1 || y == 3) {

                dur = 1;
            }
            else if(y == 2) {

                dur = 2;
            }

            for(int x = 0; x < xRange; x++) {

                gameObjects.add(new Brick(400 + brickWidth * y + brickSpacing * y, 58 + brickHeight * x + brickSpacing * x, dur));
            }
        }
*/
        //gameObjects.add(ObjectFactory.getNewBrick(200, 200));
    }

    public void run(float delta) {

        //gameObjects: WHo died?
        //remove him
        Iterator<GameObject> copy = gameObjects.iterator();
        while(copy.hasNext()) {

            GameObject object = copy.next();

            if(!object.isDead()) {

                if(object instanceof Movable) {

                    ((Movable) object).move(delta);
                }

                continue;
            }

            copy.remove();
            removeObject(object);
        }

        collisionDetector.checkCollision(gameObjects);
    }


    void keyPressed(int key) {

        if(handleKey(player1, key, P1_UP, P1_DOWN)) {

            return;
        }

        if(handleKey(player2, key, P2_UP, P2_DOWN)) {

            //TODO Return if more keys
        }
    }

    private boolean handleKey(Paddle player, int key, int up, int down) {

        if(key == down || key == up) {

            player.updateDirection(key == up ? -1 : 1);
            return true;
        }

        return false;
    }

    void keyReleased(int key) {

        if(key == P1_DOWN || key == P1_UP) {

            for(GameObject gameObject : gameObjects){
                if(gameObject instanceof Brick){
                    ((Brick) gameObject).deleteBrick();
                }
            }

            player1.updateDirection(0);
        }

        if(key == P2_DOWN || key == P2_UP) {

            player2.updateDirection(0);
        }
    }

    public void removeObject(GameObject object) {

        Canvas.getInstance().hide(object.getShape());
        gameObjects.remove(object);
    }

    public Set<GameObject> getGameObjects() {

        return gameObjects;
    }
}