package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.Brick;
import org.academiadecodigo.twoballs.gameobjects.Paddle;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class ObjectFactory {

    public static Ball getNewBall(Rectangle bounds, int x, int y) {

        return new Ball(bounds, x, y);
    }

    public static Paddle getLeftPaddle(Rectangle bounds, String color) {

        return getNewPaddle(bounds, color, Stage.PADDING, bounds.getHeight() / 2);
    }

    public static Paddle getRightPaddle(Rectangle bounds, String color) {

        return getNewPaddle(bounds, color, bounds.getWidth() - Stage.PADDING, bounds.getHeight() / 2);
    }

    private static Paddle getNewPaddle(Rectangle bounds, String color, int x, int y) {

        return new Paddle(bounds, color, x, y);
    }

    public static Brick getNewBrick(int x, int y) {

        return new Brick(x, y);
    }
}