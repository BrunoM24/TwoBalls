package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;
import org.academiadecodigo.twoballs.input.KeyboardManager;

import java.util.ArrayList;
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

        int numberOfBalls = 2;

        for(int i = 0; i < numberOfBalls; i++) {

            gameObjects.add(ObjectFactory.getNewBall(bkgRectangle));
        }

        //gameObjects.add(ObjectFactory.getNewBrick(200, 200));
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


            object.checkCollision(shapesOnTop(object));
        }
    }

    private ArrayList<Shape> shapesOnTop(GameObject object) {

        return Canvas.getInstance().getShapesInArea(object.getX(), object.getY(), object.getX() + object.getWidth(), object.getY() + object.getHeight())
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

        //Player 2 Keyboard
        if(key == KeyboardManager.P2_DOWN || key == KeyboardManager.P2_UP){

            int nDir = 1;
            if(key == KeyboardManager.P2_UP){
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

        if(key == KeyboardManager.P2_DOWN || key == KeyboardManager.P2_UP){

            player2.updateDirection(0);
        }
    }
}