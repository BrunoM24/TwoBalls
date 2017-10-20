package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.gameobjects.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by miro on 20/10/2017.
 */
public class CollisionDetector {

    int buffer = 2;

    private Collider collider = new Collider();

    public void checkCollisions(GameObject ball, Set<GameObject> gameObjects) {

        ArrayList<GameObject> objsOnTop = objectsOnTop(ball, gameObjects);

        if(objsOnTop.isEmpty()) {

            return;
        }

        GameObject object = objsOnTop.get(0);

        if(ball instanceof Ball) {

            checkBallCollision((Ball) ball, object);
        }
        else if(ball instanceof PowerUp) {

            checkPowerUpCollision((PowerUp) ball, (Paddle) object);
        }
    }

    private void checkPowerUpCollision(PowerUp pUp, Paddle paddle) {

        System.out.println(pUp + " is touching " + paddle);
    }

    private void checkBallCollision(Ball ball, GameObject object) {

        if(!ball.canCollideWith(object)) {

            return;
        }

        ball.setLastObjectTouched(object);
        if(object instanceof Ball) {

            collider.ballOnBall(ball, (Ball) object);
            ((Ball) object).setLastObjectTouched(ball);
        }
        else if(object instanceof Paddle) {

            collider.updateBall(buffer, ball, object);
            collider.ballOnPaddle(ball, (Paddle) object);
        }
        else if(object instanceof Brick) {

            collider.updateBall(buffer, ball, object);
            ((Brick) object).damageBrick(ball.getLastPaddleTouched() == null ? 0 : ball.getLastPaddleTouched().getPaddleId());
        }
    }

    private ArrayList<GameObject> objectsOnTop(GameObject ball, Set<GameObject> gameObjects) {

        ArrayList<GameObject> objects = new ArrayList<>();

        Iterator<GameObject> iterator = gameObjects.iterator();
        while(iterator.hasNext()) {

            GameObject objB = iterator.next();

            if(objB.equals(ball)) {

                continue;
            }


            if(!ball.getBounds().intersects(objB.getX() - buffer, objB.getY() - buffer, objB.getWidth() + buffer, objB.getHeight() + buffer)) {

                continue;
            }

            objects.add(objB);
        }

        return objects;
    }
}