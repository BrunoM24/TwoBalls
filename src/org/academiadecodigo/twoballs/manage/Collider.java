package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.Brick;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;

/**
 * Created by miro on 19/10/2017.
 */
public class Collider {

    /*
    private int countCenter = 0;

    private int countDown = 0;

    private int countUp = 0;
    */

    public void collide(Ball ball, Paddle paddle) {

        float ballCenterLine = ball.getY() + (1f / 2f) * ball.getHeight();
        float firstDivision = paddleHeightDivision(1, paddle);
        float secondDivision = paddleHeightDivision(2, paddle);

        //boolean touchCenter = (ballCenterLine >= firstDivision && ballCenterLine <= secondDivision);
        boolean touchUp = (ball.getY() + ball.getHeight() >= paddle.getY()) && (ballCenterLine < firstDivision);
        boolean touchDown = (ball.getY() <= paddle.getY() + paddle.getHeight()) && (ballCenterLine > secondDivision);


        ball.flipX(false);

        if (touchUp && ball.getDirectionY() >= 0) {
            ball.getDirection().y = -1;
        }

        if (touchDown && ball.getDirectionY() <= 0) {
            ball.getDirection().y = 1;
        }

        if (touchDown) {
            System.out.println("touchdown");
        }
    }


    public void collide(Ball ballA, Ball ballB) {

        //TODO GOOD WORK EDU

        boolean ballA_TouchingFromTop = (ballA.getY() + ballA.getHeight() >= ballB.getY());// || (ballA.getY() <= ballB.getY() + ballB.getHeight());
        boolean ballA_TouchingFromLeft = (ballA.getX() + ballA.getWidth() >= ballB.getX());// || (ballA.getX() <= ballB.getX() + ballB.getWidth());


        if (ballA.getDirectionX() > 0 && ballB.getDirectionX() < 0 && ballA_TouchingFromLeft) {
            ballA.flipX(true);
            ballB.flipX(true);
        }

        if (ballA.getDirectionX() < 0 && ballB.getDirectionX() < 0 && ballA_TouchingFromLeft) {
            ballB.flipX(true);
        }

        if (ballA.getDirectionX() > 0 && ballB.getDirectionX() > 0 && ballA_TouchingFromLeft) {
            ballA.flipX(true);
        }

        if (ballA.getDirectionY() > 0 && ballB.getDirectionY() < 0 && ballA_TouchingFromTop) {
            ballA.flipY(true);
            ballB.flipY(true);
        }

        if (ballA.getDirectionY() < 0 && ballB.getDirectionY() < 0 && ballA_TouchingFromTop) {
            ballB.flipY(true);
        }

        if (ballA.getDirectionY() > 0 && ballB.getDirectionY() > 0 && ballA_TouchingFromTop) {
            ballA.flipY(true);
        }

    }


    public void collide(Ball ball, Brick brick) {

        boolean touchBottomOfBrick = (ball.getY() <= brick.getY() + brick.getHeight());
        boolean touchTopOfBrick = (ball.getY() + ball.getHeight() >= brick.getY());
        boolean touchRightSideOfBrick = (ball.getX() <= brick.getX() + brick.getWidth());
        boolean touchLeftSideOfBrick = (ball.getX() + ball.getWidth() >= brick.getX());

        if (touchBottomOfBrick || touchTopOfBrick) {
            ball.flipY(true);
            brick.deleteBrick();
        }

        if (touchLeftSideOfBrick || touchRightSideOfBrick) {
            ball.flipX(true);
            brick.deleteBrick();
        }

    }

    private float paddleHeightDivision(int divisionNumber, GameObject object) {

        return (object.getY() + (divisionNumber / 3f) * object.getHeight());
    }
}