package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.gameobjects.Ball;
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


        boolean touchCenter = (ballCenterLine >= firstDivision && ballCenterLine <= secondDivision);

        boolean touchUp = (ball.getY() + ball.getHeight() >= paddle.getY()) && (ballCenterLine < (paddle.getY() + (1f / 3f) * paddle.getHeight()));
        boolean touchDown = (ball.getY() <= paddle.getY() + paddle.getHeight()) && (ballCenterLine > (paddle.getY() + (2f / 3f) * paddle.getHeight()));

        ball.flipX(true);

        //TODO: > if touchup && getDirY > 0
        if(touchUp) {

            if(ball.getDirectionY() > 0) {

                ball.flipY(true);
            }

            //countUp++;
            //System.out.println("touching up " + countUp);
        }

        if(touchDown) {

            if(ball.getDirectionY() < 0) {

                ball.flipY(true);
            }

            //countDown++;
            //System.out.println("touching down baby..hmmm " + countDown);
        }

        if(touchCenter) {

            //countCenter++;
            //System.out.println("na muche CENTER " + countCenter);
        }
    }

    private float paddleHeightDivision(int divisionNumber, GameObject object) {

        return (object.getY() + (divisionNumber / 3f) * object.getHeight());
    }
}