package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;
import org.academiadecodigo.twoballs.input.KeyboardManager;

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

    public Stage(int width, int height) {

        this.bkgRectangle = new Rectangle(PADDING, PADDING, width, height);

        bkgRectangle.draw();
    }

    public void initializeObjects() {

        gameObjects.add(player1 = ObjectFactory.getLeftPaddle("blue"));
        gameObjects.add(player2 = ObjectFactory.getRightPaddle("red"));

        int numberOfBalls = 3;

        for(int i = 1; i <= numberOfBalls; i++) {

            gameObjects.add(ObjectFactory.getNewBall(100 * i, 100 * i));
        }

        //TODO SPAWN BRICKS
        int xRange = 1;
        int yRange = 1;

        for(int y = 0; y < yRange; y++) {

            for(int x = 0; x < xRange; x++) {


            }
        }
    }

    public void run(float delta) {

        //TODO: kill bricks
        //kill bricks
        //spawn powers if any
        //update objects

        for(GameObject object : gameObjects) {

            if(object instanceof Movable) {

                ((Movable) object).move(delta);
            }

            //TODO Check only balls?
            //Powerup extends ball
            collisionDetector.checkCollision(gameObjects, object);
        }
    }


    void keyPressed(int key) {

        //PLAYER 1 KEYBOARD
        if(key == KeyboardManager.P1_DOWN || key == KeyboardManager.P1_UP) {

            int nDir = 1;
            if(key == KeyboardManager.P1_UP) {

                nDir = -1;
            }

            player1.updateDirection(nDir);
            return;
        }
        //TODO Unify this into 1 method handleKey(player1, key, P1_DOWN, P1_UP)
        //Player 2 Keyboard
        if(key == KeyboardManager.P2_DOWN || key == KeyboardManager.P2_UP) {

            int nDir = 1;
            if(key == KeyboardManager.P2_UP) {
                nDir = -1;
            }

            player2.updateDirection(nDir);
            return;
        }

    }

    void keyReleased(int key) {

        //paddle.keyReleased
        if(key == KeyboardManager.P1_DOWN || key == KeyboardManager.P1_UP) {

            player1.updateDirection(0);
        }

        if(key == KeyboardManager.P2_DOWN || key == KeyboardManager.P2_UP) {

            player2.updateDirection(0);
        }
    }
}