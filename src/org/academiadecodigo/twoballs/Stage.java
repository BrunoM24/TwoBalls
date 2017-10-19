package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;

import static org.academiadecodigo.twoballs.input.KeyboardManager.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by codecadet on 16/10/17.
 */
public class Stage {

    public static final int PADDING = 10;

    private Rectangle bkgRectangle;

    private Set<GameObject> gameObjects = new HashSet<>();  //elaborate a functional SET list

    private Paddle player1;

    private Paddle player2;

    private CollisionDetector collisionDetector = new CollisionDetector();

    ScoreManager scoreManager;

    public Stage(int width, int height) {

        this.bkgRectangle = new Rectangle(PADDING, PADDING, width, height);

        bkgRectangle.draw();

        scoreManager = new ScoreManager();
        scoreManager.draw();
    }

    public void initializeObjects() {

        gameObjects.add(player1 = ObjectFactory.getLeftPaddle("blue"));
        gameObjects.add(player2 = ObjectFactory.getRightPaddle("red"));

        int numberOfBalls = 3;

        for (int i = 1; i <= numberOfBalls; i++) {

            gameObjects.add(ObjectFactory.getNewBall(100 * i, 100 * i));
        }

        //TODO SPAWN BRICKS
        int xRange = 5;
        int yRange = 7;

        for (int y = 0; y < yRange; y++) {

            for (int x = 0; x < xRange; x++) {


            }
        }

        //gameObjects.add(ObjectFactory.getNewBrick(200, 200));
    }

    public void run(float delta) {

        //TODO: kill bricks
        //kill bricks
        //spawn powers if any
        //update objects

        for (GameObject object : gameObjects) {

            if (object instanceof Movable) {

                ((Movable) object).move(delta);
            }

            //TODO Check only balls?
            //Powerup extends ball
            collisionDetector.checkCollision(gameObjects, object);
        }
    }


    void keyPressed(int key) {

        if (handleKey(player1, key, P1_UP, P1_DOWN)) {

            return;
        }

        if (handleKey(player2, key, P2_UP, P2_DOWN)) {

            return;
        }
    }

    private boolean handleKey(Paddle player, int key, int up, int down) {

        if (key == down || key == up) {

            player.updateDirection(key == up ? -1 : 1);
            return true;
        }

        return false;
    }

    void keyReleased(int key) {

        if (key == P1_DOWN || key == P1_UP) {

            player1.updateDirection(0);
        }

        if (key == P2_DOWN || key == P2_UP) {

            player2.updateDirection(0);
        }
    }
}