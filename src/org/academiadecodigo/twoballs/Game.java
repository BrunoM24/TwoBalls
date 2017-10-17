package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;
import org.academiadecodigo.twoballs.gameobjects.Paddle;

import javax.tools.Tool;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */


public class Game {

    private Set<GameObject> gameObjects = new HashSet<>();  //elaborate a functional SET list

    private static final int PADDING = 10;

    private Rectangle bkgImage;
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
        gameObjects.add(ball = new Ball(bkgImage, 150, 190)); //TODO: to remove


        gameObjects.add(ObjectFactory.getNewPaddle(bkgImage, Color.BLUE, bkgImage.getWidth() - PADDING, bkgImage.getY()));
        gameObjects.add(ObjectFactory.getNewPaddle(bkgImage, Color.PINK, PADDING, 190));
        gameObjects.add(ObjectFactory.getNewBall(bkgImage, 90,90));
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

    public static int getWidth() {

        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    public static int getHeight() {

        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}