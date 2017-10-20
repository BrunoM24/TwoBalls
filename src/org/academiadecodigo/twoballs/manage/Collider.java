package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;
import org.academiadecodigo.twoballs.sound.GameSound;
import org.academiadecodigo.twoballs.sound.SoundManager;

/**
 * Created by miro on 20/10/2017.
 */
public class Collider {

    void updateBall(Ball ball, GameObject object) {

        //CENTER CHECK
        if(checkCenterBallPosition(ball, object)) {

            return;
        }

        //TODO I Think I saw a ball enter a brick from downLeft side
        //TODO I SAW a ball enter in a U shape and then ghost through it

        //DIAGONAL CHECK
        boolean ballIsUp = ball.getY() <= object.getY();
        boolean ballIsDown = ball.getY() >= object.getY();
        boolean ballIsLeft = ball.getX() < object.getX();
        boolean ballIsRight = ball.getX() > object.getX();

        if(ballIsDown || ballIsUp) {

            flipBall(ball, ballIsLeft, ballIsRight);
        }
    }

    private void flipBall(Ball ball, boolean ballIsLeft, boolean ballIsRight) {

        ball.getDirection().y = -1;
        if(ballIsLeft) {

            ball.getDirection().x = -1;
        }
        else if(ballIsRight) {

            ball.getDirection().x = 1;
        }
    }

    private boolean checkCenterBallPosition(Ball ball, GameObject object) {    //SEEMS FINE??

        int ballCenterX = ball.getX() + ball.getWidth() / 2;
        int ballCenterY = ball.getY() + ball.getHeight() / 2;

        boolean sameYAxis = ballCenterX > object.getX() && ballCenterX < object.getX() + object.getWidth();
        boolean sameXAxis = ballCenterY > object.getY() && ballCenterY < object.getY() + object.getHeight();

        if(sameYAxis) {

            ball.flipY();
            return true;
        }
        else if(sameXAxis) {

            ball.flipX();
            return true;
        }
        return false;
    }

    void ballOnPaddle(Ball ball, Paddle paddle) {   //WORKS FINE

        float ballCenterLine = ball.getY() + (1f / 2f) * ball.getHeight();
        float firstDivision = paddleHeightDivision(1, paddle);
        float secondDivision = paddleHeightDivision(2, paddle);

        //boolean touchCenter = (ballCenterLine >= firstDivision && ballCenterLine <= secondDivision);
        boolean touchUp = (ball.getY() + ball.getHeight() >= paddle.getY()) && (ballCenterLine < firstDivision);
        boolean touchDown = (ball.getY() <= paddle.getY() + paddle.getHeight()) && (ballCenterLine > secondDivision);


        if(touchUp && ball.getDirection().y >= 0) {

            ball.getDirection().y = -1;
        }

        if(touchDown && ball.getDirection().y <= 0) {

            ball.getDirection().y = 1;
        }

        SoundManager.getInstance().playSound(GameSound.drsh());
    }

    private float paddleHeightDivision(int divisionNumber, GameObject object) {

        return (object.getY() + (divisionNumber / 3f) * object.getHeight());
    }

    public void ballOnBall(Ball ballA, Ball ballB) {        //SEEMS TO BE WORKING FINE?

        boolean ballA_TouchingFromTop = (ballA.getY() + ballA.getHeight() >= ballB.getY());// || (ballA.getY() <= ballB.getY() + ballB.getHeight());
        boolean ballA_TouchingFromLeft = (ballA.getX() + ballA.getWidth() >= ballB.getX());// || (ballA.getX() <= ballB.getX() + ballB.getWidth());


        if(ballA.getDirection().x > 0 && ballB.getDirection().x < 0 && ballA_TouchingFromLeft) {
            ballA.flipX();
            ballB.flipX();
        }

        if(ballA.getDirection().x < 0 && ballB.getDirection().x < 0 && ballA_TouchingFromLeft) {
            ballB.flipX();
        }

        if(ballA.getDirection().x > 0 && ballB.getDirection().x > 0 && ballA_TouchingFromLeft) {
            ballA.flipX();
        }

        if(ballA.getDirection().y > 0 && ballB.getDirection().y < 0 && ballA_TouchingFromTop) {
            ballA.flipY();
            ballB.flipY();
        }

        if(ballA.getDirection().y < 0 && ballB.getDirection().y < 0 && ballA_TouchingFromTop) {
            ballB.flipY();
        }

        if(ballA.getDirection().y > 0 && ballB.getDirection().y > 0 && ballA_TouchingFromTop) {
            ballA.flipY();
        }
    }
}