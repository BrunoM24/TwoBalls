package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.Brick;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;

/**
 * Created by miro on 20/10/2017.
 */
public class Collider {

    public void updateBall(int buffer, Ball ball, GameObject object) {

        ball.flipX();
    }

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


        ball.flipX();

        if(touchUp && ball.getDirectionY() >= 0) {
            ball.getDirection().y = -1;
        }

        if(touchDown && ball.getDirectionY() <= 0) {
            ball.getDirection().y = 1;
        }

    }

    public void ballOnBall(Ball ballA, Ball ballB) {

        boolean ballA_TouchingFromTop = (ballA.getY() + ballA.getHeight() >= ballB.getY());// || (ballA.getY() <= ballB.getY() + ballB.getHeight());
        boolean ballA_TouchingFromLeft = (ballA.getX() + ballA.getWidth() >= ballB.getX());// || (ballA.getX() <= ballB.getX() + ballB.getWidth());

        System.out.println("Touched");
    }

    public void ballOnBrick(Ball ball, Brick brick) {

        boolean touchBottomOfBrick = (ball.getY() <= brick.getY() + brick.getHeight());
        boolean touchTopOfBrick = (ball.getY() + ball.getHeight() >= brick.getY());
        boolean touchRightSideOfBrick = (ball.getX() <= brick.getX() + brick.getWidth());
        boolean touchLeftSideOfBrick = (ball.getX() + ball.getWidth() >= brick.getX());

        if(touchBottomOfBrick || touchTopOfBrick) {
            ball.flipY();
            brick.deleteBrick();
        }

        if(touchLeftSideOfBrick || touchRightSideOfBrick) {
            ball.flipX();
            brick.deleteBrick();
        }
    }

    public void ballOnPaddle(Ball ball, Paddle paddle) {

        System.out.println("Paddle");
    }

    private float paddleHeightDivision(int divisionNumber, GameObject object) {

        return (object.getY() + (divisionNumber / 3f) * object.getHeight());
    }
}