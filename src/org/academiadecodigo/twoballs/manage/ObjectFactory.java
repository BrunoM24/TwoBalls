package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.GameScreen;
import org.academiadecodigo.twoballs.Stage;
import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.Brick;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class ObjectFactory {

    private static ObjectFactory instance;

    private Stage stage;

    public ObjectFactory(Stage stage) {

        instance = this;
        this.stage = stage;
    }

    public static Ball getNewBall(int x, int y, int dx, int dy) {

        return new Ball(x, y, dx, dy);
    }

    public static Ball getNewBall(int x, int y) {

        return new Ball(x, y);
    }

    public static Paddle getLeftPaddle(String color) {

        return getNewPaddle(color, Stage.PADDING, GameScreen.getHeight() / 2);
    }

    public static Paddle getRightPaddle(String color) {

        return getNewPaddle(color, GameScreen.getWidth() - Stage.PADDING, GameScreen.getHeight() / 2);
    }

    private static Paddle getNewPaddle(String color, int x, int y) {

        return new Paddle(color, x, y);
    }

    public static Brick getNewBrick(int x, int y, int dur) {

        return new Brick(x, y, dur);
    }

    public static void removeObject(GameObject object) {

        instance.stage.removeObject(object);
    }
}