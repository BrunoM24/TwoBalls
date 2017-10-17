package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Ball extends GameObject implements Movable {

    private Ellipse ball;
    private Rectangle background;
    private int radius = 15;

    public Ball(Rectangle backgroundImage, int x, int y) {

        ball = new Ellipse(x, y, radius, radius);
        ball.setColor(Color.BLUE);
        ball.fill();
        background = backgroundImage;

        speed.x = 2;
        speed.y = 1;

        direction.x = 1;
        direction.y = 1;
    }

    @Override
    public void move() {

        checkDirection();

        ball.translate(direction.x * speed.x, direction.y * speed.y);
    }

    @Override
    public void checkCollision() {

    }

    public void checkDirection() {

        //bottom
        if (ball.getY() + ball.getHeight() > background.getHeight() + background.getY()) {
            changeY();
            return;
        }

        // top
        if (ball.getY() < background.getY()) {
            changeY();
            return;
        }

        //right
        if (ball.getX() + ball.getWidth() > background.getWidth() + background.getX()) {
            changeX();
            return;
        }

        //left? for now I'll set the four sides
        if (ball.getX() < background.getX()) {
            changeX();
            return;
        }
    }

    public void changeX() {

        direction.x *= -1;
        speed.x = calcSpeed();
    }

    public void changeY() {

        direction.y *= -1;
        speed.y = calcSpeed();
    }

    public int calcSpeed() {

        return (int) ((Math.random() * 3) + 1);
    }
}