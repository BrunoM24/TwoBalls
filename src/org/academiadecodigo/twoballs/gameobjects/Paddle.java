package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.GameScreen;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;

import java.awt.*;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Paddle extends GameObject implements Movable {

    private int direction = 0;

    private int speed = 7;

    private int deltaY;

    public Paddle(String paddleColor, int x, int y) {

        if(x - 14 > GameScreen.getWidth() / 2) {

            x -= 4;
        }

        shape = new Picture(x, y, "assets/" + paddleColor + "Paddle.png");
        shape.draw();
        bounds = new Rectangle(x, y, shape.getWidth(), shape.getHeight());
    }

    public void updateDirection(int newValue) {

        direction = newValue;
    }

    @Override
    public void move(float delta) {

        deltaY = direction * speed;

        checkBoundaries();

        ((Picture) shape).translate(0, deltaY * delta);
        bounds.setLocation(shape.getX(), shape.getY());
    }

    private void checkBoundaries() {

        if(getY() + deltaY < GameScreen.getY() || getY() + getHeight() + deltaY > GameScreen.getHeight() + GameScreen.getY()) {

            deltaY *= 0;
        }
    }
}