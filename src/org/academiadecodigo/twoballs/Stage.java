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

    public Stage(int width, int height) {

        this.bkgRectangle = new Rectangle(PADDING, PADDING, width, height);

        bkgRectangle.draw();
    }

    public void initializeObjects() {

        gameObjects.add(player1 = ObjectFactory.getLeftPaddle(bkgRectangle, "blue"));
        gameObjects.add(player2 = ObjectFactory.getRightPaddle(bkgRectangle, "red"));
        gameObjects.add(ObjectFactory.getNewBall(bkgRectangle, 90, 90));
        gameObjects.add(ObjectFactory.getNewBall(bkgRectangle, 190, 190));
    }

    public void run(float delta) {

        for(GameObject object : gameObjects) {

            if(object instanceof Movable) {

                ((Movable) object).move();
            }

            object.checkCollision();
        }
    }


    void keyPressed(int key) {

        //paddle.keyUP
        if(key == KeyboardManager.P1_DOWN || key == KeyboardManager.P1_UP) {

            if(key == KeyboardManager.P1_UP) {

                player1.updateDirection(-1);
                return;
            }

            player1.updateDirection(1);
            return;
        }
    }

    void keyReleased(int key) {

        //paddle.keyReleased
        if(key == KeyboardManager.P1_DOWN || key == KeyboardManager.P1_UP) {

            player1.updateDirection(0);
        }
    }
}