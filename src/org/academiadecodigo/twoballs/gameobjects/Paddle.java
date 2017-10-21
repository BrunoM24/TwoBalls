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

    private int paddleId;

    private float freezeTimer;

    public Paddle(String paddleColor, int x, int y, int paddleId) {

        if(x - 14 > GameScreen.getWidth() / 2) {

            x -= 4;
        }

        shape = new Picture(x, y, paddleColor + "Paddle.png");
        shape.draw();
        bounds = new Rectangle(x, y, shape.getWidth(), shape.getHeight());

        this.paddleId = paddleId;
    }

    public void updateDirection(int newValue) {

        direction = newValue;
    }

    @Override
    public void move(float delta) {

        if(freezeTimer > 0) {

            freezeTimer -= delta;
            delta = 0;
        }

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

    public int getPaddleId() {

        return paddleId;
    }

    /**
     * Freeze this paddle for X seconds
     *
     * @param time in seconds
     */
    public void freeze(int time) {

        freezeTimer = time * 60;
    }
}