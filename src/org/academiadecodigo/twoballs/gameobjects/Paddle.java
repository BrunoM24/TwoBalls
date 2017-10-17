package org.academiadecodigo.twoballs.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


/**
 * TwoBalls Created by BrunoM24 on 16/10/2017.
 */


public class Paddle extends GameObject implements Movable {

    private Rectangle paddle;
    private int direction = -1;
    private int speed = 4;
    private int paddleWidth = 20;
    private int paddleHeight = 80;

    private Rectangle backgroundImage;

    public Paddle(Rectangle backgroundImage, Color paddleColor) {

        this(backgroundImage, paddleColor, backgroundImage.getX(), backgroundImage.getHeight() / 2);
    }

    public Paddle(Rectangle backgroundImage, Color paddleColor, int posX, int posY) {

        paddle = new Rectangle(posX, posY, paddleWidth, paddleHeight);
        paddle.setColor(paddleColor);
        paddle.fill();

        this.backgroundImage = backgroundImage;
    }

    @Override
    public void move() {

        checkDirection();

        paddle.translate(0, direction * speed);
    }

    public void checkDirection() {

        if (paddle.getY() <= 10 ||      //TODO 10 of PADDING....improve this ..seems ugly
                paddle.getY() + paddle.getHeight() >= backgroundImage.getHeight() + 10) {

            changeDirection();
        }
    }

    public void changeDirection() {

        direction = direction * (-1);
    }

    @Override
    public void checkCollision() {

    }

    public void setPaddleHeight(int newPaddleHeight) {  //for further 'boosts'

        paddleHeight = newPaddleHeight;
    }

    public void setSpeed(int newSpeed) {

        speed = newSpeed;
    }
}