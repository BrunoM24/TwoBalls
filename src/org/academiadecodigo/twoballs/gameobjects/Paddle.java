package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;

import java.util.ArrayList;


/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Paddle extends GameObject implements Movable {

    private int direction = 0;

    private int speed = 4;

    private Rectangle stageBoundaries;

    private int deltaY;

    public Paddle(Rectangle stageBoundaries, String paddleColor, int posX, int posY) {

        if(posX - 14 > stageBoundaries.getWidth() / 2) {

            posX -= 4;
        }

        shape = new Picture(posX, posY, "assets/" + paddleColor + "Paddle.png");
        shape.draw();

        this.stageBoundaries = stageBoundaries;
    }

    public void updateDirection(int newValue) {

        direction = newValue;
    }

    @Override
    public void move(float delta) {

        deltaY = direction * speed;

        checkBoundaries();

        ((Picture) shape).translate(0, deltaY * delta);
    }

    private void checkBoundaries() {

        if(getY() + deltaY < stageBoundaries.getY() || getY() + getHeight() + deltaY > stageBoundaries.getHeight() + stageBoundaries.getY()) {

            deltaY *= -1;
        }
    }
}