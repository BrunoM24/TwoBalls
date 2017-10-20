package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;

/**
 * Created by miro on 20/10/2017.
 */
public class Collider {

    void updateBall(int buffer, Ball ball, GameObject object) {

        boolean touchedLeft = ball.getX() + ball.getWidth() < object.getX();
        boolean touchedRight = ball.getX() < object.getX() + object.getWidth();

        boolean touchedUp = ball.getY() + ball.getHeight() < object.getY();
        boolean touchedDown = ball.getY() + ball.getHeight() < object.getY();

        if (touchedLeft || touchedRight) {

            ball.flipX();
            ball.translate(ball.getDirectionX() * buffer, 0);
            return;
        }

        if (touchedDown || touchedUp) {

            ball.flipY();
            ball.translate(0, ball.getDirectionY() * buffer);
        }
    }

    void ballOnPaddle(Ball ball, Paddle paddle) {

        float ballCenterLine = ball.getY() + (1f / 2f) * ball.getHeight();
        float firstDivision = paddleHeightDivision(1, paddle);
        float secondDivision = paddleHeightDivision(2, paddle);

        //boolean touchCenter = (ballCenterLine >= firstDivision && ballCenterLine <= secondDivision);
        boolean touchUp = (ball.getY() + ball.getHeight() >= paddle.getY()) && (ballCenterLine < firstDivision);
        boolean touchDown = (ball.getY() <= paddle.getY() + paddle.getHeight()) && (ballCenterLine > secondDivision);



        if (touchUp && ball.getDirectionY() >= 0) {

            ball.getDirection().y = -1;
        }

        if (touchDown && ball.getDirectionY() <= 0) {

            ball.getDirection().y = 1;
        }
    }

    private float paddleHeightDivision(int divisionNumber, GameObject object) {

        return (object.getY() + (divisionNumber / 3f) * object.getHeight());
    }
}