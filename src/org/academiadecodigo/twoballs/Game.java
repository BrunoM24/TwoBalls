package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.keyboard.VKeyboard;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Movable;
import org.academiadecodigo.twoballs.gameobjects.Paddle;

import java.awt.event.KeyEvent;
import java.util.Set;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */


public class Game implements KeyboardHandler {

    private Set<GameObject> gameObjects;  //elaborate a functional SET list


    private static final int PADDING = 10;

    private Rectangle backgroungImage;
    private Paddle bluePaddle;
    private Paddle pinkPaddle;


    Game() {


        init();
    }

    private void init() {
        int width = 960;
        int height = 544;

        backgroungImage = new Rectangle(PADDING, PADDING, width, height);
        backgroungImage.draw();

        //make factory and do gameobject.add both paddles
        bluePaddle = new Paddle(backgroungImage, Color.BLUE);
        pinkPaddle = new Paddle(backgroungImage, Color.PINK, backgroungImage.getWidth() - PADDING, backgroungImage.getY());


        VKeyboard keyboard = new VKeyboard(this);


        keyboard.listenToKey(KeyEvent.VK_ENTER); //Keys are here

        Canvas.getInstance().addKeyListener(keyboard);

    }

    public void start() throws InterruptedException {

        while (true) {
            Thread.sleep(10);
            bluePaddle.move();
            pinkPaddle.move();
            // run();        //commented for test purpose while SET list is not functional
        }

    }

    private void run() {

        for (GameObject object : gameObjects) {

            if (object instanceof Movable) {
                ((Movable) object).move();
            }

            object.checkCollision();

        }

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void keyTyped(KeyboardEvent keyboardEvent) {

    }
}
