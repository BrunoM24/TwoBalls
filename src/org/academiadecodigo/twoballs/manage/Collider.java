package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;
import org.academiadecodigo.twoballs.sound.GameSound;
import org.academiadecodigo.twoballs.sound.SoundManager;

import java.awt.geom.Rectangle2D;

/**
 * Created by miro on 20/10/2017.
 */
public class Collider {

    void updateBall(Ball ball, GameObject object) {

        if(checkCenterBallPosition(ball, object)) {

            return;
        }

        if(isSideCollision(ball, object.getBounds())) {

            ball.flipX();
        }
        else {

            ball.flipY();
        }
    }

    private float getIntersectAmount(double obj1Start, double obj1End, double obj2Start, double obj2End) {

        Rectangle2D obj1Rect = new Rectangle2D.Double(obj1Start, 0, obj1End, 1);
        Rectangle2D obj2Rect = new Rectangle2D.Double(obj2Start, 0, obj2End, 1);

        if(!obj1Rect.intersects(obj2Rect)) {

            return 0;
        }

        //create a new rectangle with the new area and assign it to obj2Rect
        Rectangle2D.intersect(obj1Rect, obj2Rect, obj2Rect);
        return (float) (obj2Rect.getWidth() / obj1Rect.getWidth());
    }

    private double horContactAmount(Rectangle2D ballRect, Rectangle2D gameObjectRect) {

        return getIntersectAmount(ballRect.getX(), ballRect.getWidth(), gameObjectRect.getX(), gameObjectRect.getWidth());
    }

    private double verContactAmount(Rectangle2D ballRect, Rectangle2D gameObjectRect) {

        return getIntersectAmount(ballRect.getY(), ballRect.getHeight(), gameObjectRect.getY(), gameObjectRect.getHeight());
    }

    private boolean isSideCollision(Ball ball, Rectangle2D gameObjectRect) {

        Rectangle2D ballRect = ball.getBounds();
        return horContactAmount(ballRect, gameObjectRect) < verContactAmount(ballRect, gameObjectRect);
    }

    @Deprecated
    void oldUpdateBall(int buffer, Ball ball, GameObject object) {

        //CENTER CHECK
        if(checkCenterBallPosition(ball, object)) {

            return;
        }

        boolean ballIsRight = ball.getX() + buffer >= object.getX() + object.getWidth() - buffer;
        boolean ballIsLeft = ball.getX() + ball.getWidth() - buffer <= object.getX() + buffer;

        if(ballIsRight || ballIsLeft) {

            ball.flipX();
            return;
        }

        boolean ballIsUp = ball.getY() + ball.getHeight() - buffer <= object.getY() + buffer;
        boolean ballIsDown = ball.getY() + buffer >= object.getY() + object.getHeight() - buffer;

        if(ballIsDown || ballIsUp) {

            ball.flipY();
            return;
        }

        System.out.println("Last resort");
        //DIAGONAL CHECK
        ballIsUp = ball.getY() <= object.getY();
        ballIsDown = ball.getY() >= object.getY();
        ballIsLeft = ball.getX() < object.getX();
        ballIsRight = ball.getX() > object.getX();

        if(ballIsDown || ballIsUp) {

            flipBall(ball, ballIsLeft, ballIsRight);
        }
    }

    @Deprecated
    void olderUpdateBall(Ball ball, GameObject object) {

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

    @Deprecated
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

        SoundManager.playSound(GameSound.drsh());
    }

    private float paddleHeightDivision(int divisionNumber, GameObject object) {

        return (object.getY() + (divisionNumber / 3f) * object.getHeight());
    }

    void ballOnBall(Ball ballA, Ball ballB) {

        ballB.setLastObjectTouched(ballA);
        if(isSideCollision(ballA, ballB.getBounds())) {

            ballB.getDirection().x = ballA.getDirection().x;
            ballA.flipX();
        }
        else {

            ballB.getDirection().y = ballA.getDirection().y;
            ballA.flipY();
        }

    }

    @Deprecated
    void oldBallOnBall(Ball ballA, Ball ballB) {        //SEEMS TO BE WORKING FINE?

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