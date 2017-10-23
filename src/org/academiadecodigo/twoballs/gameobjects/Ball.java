package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.GameScreen;
import org.academiadecodigo.twoballs.gameobjects.move.Direction;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;
import org.academiadecodigo.twoballs.gameobjects.move.Speed;
import org.academiadecodigo.twoballs.manage.ScoreManager;
import org.academiadecodigo.twoballs.sound.GameSound;
import org.academiadecodigo.twoballs.sound.SoundManager;

import java.awt.*;

/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */
public class Ball extends GameObject implements Movable {

    private static final int leftSideStartX = GameScreen.getWidth() / 2 - 200;

    private static final int rightSideStartX = GameScreen.getWidth() / 2 + 200;

    private static final int startHeight = GameScreen.getHeight() / 2;

    private Speed speed = new Speed();

    private Direction direction = new Direction();

    private float diffX, diffY;

    private GameObject lastGameObjectTouched;

    private Paddle lastPaddleTouched;

    public Ball(int x, int y, int dirX, int dirY) {

        shape = new Picture(x, y, "ball.png");
        shape.draw();

        bounds = new Rectangle(x, y, shape.getWidth(), shape.getHeight());

        speed.x = calcSpeed(speed.x);
        speed.y = calcSpeed(speed.y);

        direction.x = dirX;
        direction.y = dirY;
    }

    @Override
    public void move(float delta) {

        diffX = direction.x * speed.x;
        diffY = direction.y * speed.y;

        checkBoundaries(delta);

        translate(diffX * delta, diffY * delta);

        keepInScreen();
    }

    private void keepInScreen() {

        if(getX() <= 0) {

            translate(getX() * -1, 0);
            if(direction.x < 0) {

                flipX();
            }
        }

        if(getY() <= 0) {

            translate(0, getY() * -1);
            if(direction.y < 0) {

                flipY();
            }
        }

        if(getX() + getWidth() > GameScreen.getWidth()) {

            translate(-1, 0);
            if(direction.x > 0) {

                flipX();
            }
        }

        if(getY() + getHeight() > GameScreen.getHeight()) {

            translate(0, -1);
            if(direction.y > 0) {

                flipY();
            }
        }
    }

    private void checkBoundaries(float delta) {

        if(getX() + diffX * delta < GameScreen.getX()) {

            resetBall(true, 2);
        }


        if(getX() + getWidth() + diffX * delta > GameScreen.getWidth()) {

            resetBall(false, 1);
        }

        if(getY() + diffY * delta < GameScreen.getY() || getY() + getHeight() + diffY * delta > GameScreen.getHeight()) {

            diffY *= -1;
            flipY();
            SoundManager.playSound(GameSound.BOUNCE);
            setLastObjectTouched(null);
        }
    }

    private void resetBall(boolean left, int playerId) {

        diffX *= -1;

        flipX();

        ScoreManager.addPoints(playerId, ScoreManager.OUT_OF_BOUNDS_POINTS);

        SoundManager.playSound(GameSound.laugh());

        speed.x = calcSpeed(0);
        speed.y = calcSpeed(0);

        direction.y = 0;

        direction.x = left ? -1 : 1;

        setLastObjectTouched(null);

        translate((left ? leftSideStartX : rightSideStartX) - getX(), startHeight - getY());
    }

    public void flipX() {

        direction.x *= -1;
        speed.x = calcSpeed(speed.x);
    }

    public void flipY() {

        direction.y *= -1;
        speed.y = calcSpeed(speed.y);
    }

    private float calcSpeed(float speed) {

        if(speed == 0) {

            int MAX_SPEED = 3;
            int MIN_SPEED = 3;
            return (float) ((Math.random() * MAX_SPEED) + MIN_SPEED);
        }

        return speed + 0.1f;
    }

    public Direction getDirection() {

        return direction;
    }

    public void setLastObjectTouched(GameObject gameObject) {

        lastGameObjectTouched = gameObject;

        if(gameObject instanceof Paddle) {
            lastPaddleTouched = (Paddle) gameObject;
        }
    }

    public GameObject getLastGameObjectTouched() {

        return lastGameObjectTouched;
    }

    public boolean canCollideWith(GameObject object) {

        return lastGameObjectTouched != object;
    }

    public Paddle getLastPaddleTouched() {

        return lastPaddleTouched;
    }
}