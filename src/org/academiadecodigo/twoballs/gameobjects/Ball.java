package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.Stage;
import org.academiadecodigo.twoballs.gameobjects.move.Direction;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;
import org.academiadecodigo.twoballs.gameobjects.move.Speed;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Ball extends GameObject implements Movable {

    private final int BUFFER = Stage.PADDING;

    private Picture ball;

    private Rectangle stageBoundaries;

    private Speed speed = new Speed();

    private Direction direction = new Direction();

    private int dx, dy;

    public Ball(Rectangle bounds) {

        this(bounds, (int) (Math.random() * (bounds.getWidth() / 2) + Stage.PADDING * 2), (int) (Math.random() * (bounds.getHeight() / 2) + Stage.PADDING * 2));
    }

    public Ball(Rectangle backgroundImage, int x, int y) {

        ball = new Picture(x, y, "assets/ball.png");
        ball.draw();

        stageBoundaries = backgroundImage;

        speed.x = calcSpeed();
        speed.y = calcSpeed();

        direction.x = Math.random() > 0.5f ? 1 : -1;
        direction.y = Math.random() > 0.5f ? 1 : -1;
    }

    @Override
    public void move() {

    }

    @Override
    public void move(float delta) {

        if(delta > 10) {

            delta = 10;
        }

        dx = direction.x * speed.x;
        dy = direction.y * speed.y;

        checkBoundaries();

        ball.translate(dx * delta, dy * delta);
    }

    @Override
    public void checkCollision() {

    }

    private void checkBoundaries() {

        if(getX() + dx < stageBoundaries.getX() || getX() + getWidth() + dx > stageBoundaries.getWidth() + stageBoundaries.getX()) {

            dx *= -1;
            changeX();
        }

        if(getY() + dy < stageBoundaries.getY() || getY() + getHeight() + dy > stageBoundaries.getHeight() + stageBoundaries.getY()) {

            dy *= -1;
            changeY();
        }
    }

    @Deprecated
    public void checkDirection() {

        //bottom
        if(ball.getY() + ball.getHeight() > stageBoundaries.getHeight() + stageBoundaries.getY()) {

            changeY();
            return;
        }

        // top
        if(ball.getY() < stageBoundaries.getY()) {

            changeY();
            return;
        }

        //right
        if(ball.getX() + ball.getWidth() > stageBoundaries.getWidth() + stageBoundaries.getX()) {

            changeX();
            return;
        }

        //left? for now I'll set the four sides
        if(ball.getX() < stageBoundaries.getX()) {

            changeX();
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

    @Override
    public int getX() {

        return ball.getX();
    }

    @Override
    public int getY() {

        return ball.getY();
    }

    @Override
    public int getWidth() {

        return ball.getWidth();
    }

    @Override
    public int getHeight() {

        return ball.getHeight();
    }
}