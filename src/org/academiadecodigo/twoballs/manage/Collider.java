package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.gameobjects.Ball;

/**
 * Created by miro on 20/10/2017.
 */
public class Collider {

    public void ballOnBall(Ball ballA, Ball ballB) {

        boolean ballA_TouchingFromTop = (ballA.getY() + ballA.getHeight() >= ballB.getY());// || (ballA.getY() <= ballB.getY() + ballB.getHeight());
        boolean ballA_TouchingFromLeft = (ballA.getX() + ballA.getWidth() >= ballB.getX());// || (ballA.getX() <= ballB.getX() + ballB.getWidth());
    }
}