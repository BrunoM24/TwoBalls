package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.Paddle;

/**
 * Created by miro on 20/10/2017.
 */
public class Collider {

    public void collide(Ball ball, Paddle paddle) {

        float ballCenterLine = ball.getY() + (1f / 2f) * ball.getHeight();

        float firstDivision = paddleHeightDivision(1, paddle);
        float secondDivision = paddleHeightDivision(2, paddle);

        //boolean touchCenter = (ballCenterLine >= firstDivision && ballCenterLine <= secondDivision);
        boolean touchUp = (ball.getY() + ball.getHeight() >= paddle.getY()) && (ballCenterLine < firstDivision);
        boolean touchDown = (ball.getY() <= paddle.getY() + paddle.getHeight()) && (ballCenterLine > secondDivision);


        ball.setLastObjectTouched(paddle);

        //System.out.println(ball.getLastGameObjectTouched());
        //TODO ball color (image) according to last paddle touched. Maybe better


        ball.flipX(false);

        if (touchUp && ball.getDirectionY() >= 0) {
            ball.getDirection().y = -1;
        }

        if (touchDown && ball.getDirectionY() <= 0) {
            ball.getDirection().y = 1;
        }

    }

    private float paddleHeightDivision(int i, Paddle paddle) {
        //todo
        return 0;
    }


    public void collide(Ball ballA, Ball ballB) {

        boolean ballA_TouchingFromTop = (ballA.getY() + ballA.getHeight() >= ballB.getY());// || (ballA.getY() <= ballB.getY() + ballB.getHeight());
        boolean ballA_TouchingFromLeft = (ballA.getX() + ballA.getWidth() >= ballB.getX());// || (ballA.getX() <= ballB.getX() + ballB.getWidth());
    }
}