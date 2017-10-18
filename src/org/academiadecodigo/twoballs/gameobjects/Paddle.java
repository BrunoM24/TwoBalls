package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.Stage;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;


/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Paddle extends GameObject implements Movable {

    private Picture paddle;

    private int direction = 0;

    private int speed = 4;

    private Rectangle stageBoundaries;

    private int dy;

    public Paddle(Rectangle stageBoundaries, String paddleColor, int posX, int posY) {

        if(posX - 14 > stageBoundaries.getWidth() / 2) {

            posX -= 4;
        }

        paddle = new Picture(posX, posY, "assets/" + paddleColor + "Paddle.png");
        paddle.draw();

        this.stageBoundaries = stageBoundaries;
    }

    public void updateDirection(int newValue) {

        direction = newValue;
    }

    @Override
    public void move() {

    }

    @Override
    public void move(float delta) {

        dy = direction * speed;

        checkBoundaries();

        paddle.translate(0, dy * delta);
    }

    private void checkBoundaries() {

        if(getY() + dy < stageBoundaries.getY() || getY() + getHeight() + dy > stageBoundaries.getHeight() + stageBoundaries.getY()) {

            dy *= -1;
        }
    }

    @Deprecated
    public void checkDirection() {

    }

    @Override
    public void checkCollision() {

        //get all the shapes in area
    }

    @Override
    public int getX() {

        return paddle.getX();
    }

    @Override
    public int getY() {

        return paddle.getY();
    }

    @Override
    public int getWidth() {

        return paddle.getWidth();
    }

    @Override
    public int getHeight() {

        return paddle.getHeight();
    }
}