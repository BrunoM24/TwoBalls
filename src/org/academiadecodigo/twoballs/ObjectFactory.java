package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.Brick;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class ObjectFactory {

    public static Ball getNewBall(Rectangle bounds, int x, int y) {

        return new Ball(bounds, x, y);
    }

    public static Paddle getNewPaddle(Rectangle bounds, Color color ,int x, int y) {

        return new Paddle(bounds, color, x, y);
    }

    //TODO REPLACE WITH BRICK
    public static Brick getNewBrick(int x, int y) {
        
        return new Brick();
    }
}