package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.GameScreen;
import org.academiadecodigo.twoballs.Stage;
import org.academiadecodigo.twoballs.gameobjects.*;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Spawn {

    private static Spawn instance;

    private Stage stage;

    public Spawn(Stage stage) {

        instance = this;
        this.stage = stage;
    }

    public static void newPowerUp(int x, int y, int dirX) {

        spawn(new PowerUp(x, y, dirX));
    }

    public static void newBall(int x, int y, int dx, int dy) {

        spawn(new Ball(x, y, dx, dy));
    }

    public static Paddle newLeftPaddle(String color) {

        return newPaddle(color, Stage.PADDING, (GameScreen.getHeight() - 52 - Stage.PADDING) / 2, 1);
    }

    public static Paddle newRightPaddle(String color) {

        return newPaddle(color, GameScreen.getWidth() - Stage.PADDING, (GameScreen.getHeight() - 52 - Stage.PADDING) / 2, 2);
    }

    private static Paddle newPaddle(String color, int x, int y, int scoreId) {

        Paddle paddle = new Paddle(color, x, y, scoreId);
        spawn(paddle);
        return paddle;
    }

    public static void newBrick(int x, int y, int dur) {

        spawn(new Brick(x, y, dur));
    }

    public static void spawn(GameObject object) {

        instance.stage.spawnObject(object);
    }

    public static void spawnParticle(int x, int y) {

        int dx = (int) (Math.random() * 3);
        int dy = (int) (Math.random() * 3);
        spawn(new Particle(x, y, dx - 1, dy - 1));
    }

    public static void removeObject(GameObject object) {

        instance.stage.removeObject(object);
    }
}