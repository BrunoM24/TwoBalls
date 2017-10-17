package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.keyboard.VKeyboard;
import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Movable;
import org.academiadecodigo.twoballs.gameobjects.Paddle;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */


public class Game {

    private Set<GameObject> gameObjects = new HashSet<>();  //elaborate a functional SET list

    private static final int PADDING = 10;

    private Rectangle bkgImage;
    private Paddle bluePaddle;
    private Paddle pinkPaddle;
    private Ball ball;

    Game() {

        init();
    }

    private void init() {

        int width = 960;
        int height = 544;

        bkgImage = new Rectangle(PADDING, PADDING, width, height);
        bkgImage.draw();

        //make factory and do gameobject.add both paddles
        bluePaddle = new Paddle(bkgImage, Color.BLUE);
        pinkPaddle = new Paddle(bkgImage, Color.PINK, bkgImage.getWidth() - PADDING, bkgImage.getY());
        ball = new Ball(bkgImage, 150, 190);

        gameObjects.add(bluePaddle);
        gameObjects.add(pinkPaddle);
        gameObjects.add(ball);
    }

    public void start() throws InterruptedException {

        while (true) {

            Thread.sleep(10);

            run();
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
}