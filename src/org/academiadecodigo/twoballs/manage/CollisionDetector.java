package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.Stage;
import org.academiadecodigo.twoballs.gameobjects.*;
import org.academiadecodigo.twoballs.sound.GameSound;
import org.academiadecodigo.twoballs.sound.SoundManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by miro on 20/10/2017.
 */
public class CollisionDetector {

    int buffer = 2;

    private Collider collider = new Collider();

    private Stage stage;

    public CollisionDetector(Stage stage) {

        this.stage = stage;
    }

    public void checkCollisions(GameObject ball, Set<GameObject> gameObjects) {

        ArrayList<GameObject> objsOnTop = objectsOnTop(ball, gameObjects);

        if(objsOnTop.isEmpty()) {

            return;
        }

        GameObject object = objsOnTop.get(0);

        if(ball instanceof Ball) {

            checkBallCollision((Ball) ball, object);
        }
        else if(ball instanceof PowerUp && object instanceof Paddle) {

            checkPowerUpCollision((PowerUp) ball, (Paddle) object);
        }
    }

    private void checkPowerUpCollision(PowerUp pUp, Paddle paddle) {

        pUp.getPowerUp().collided(paddle.getPaddleId());

        stage.pUpCollideWith(pUp, paddle);

        if(pUp.getPowerUp().isGood()) {

            SoundManager.playSound(GameSound.POINTS);
        }
        else {

            SoundManager.playSound(GameSound.laugh());
        }

        Spawn.removeObject(pUp);
    }

    private void checkBallCollision(Ball ball, GameObject object) {

        if(!ball.canCollideWith(object)) {

            return;
        }

        if(object instanceof PowerUp) {

            return;
        }

        ball.setLastObjectTouched(object);

        if(object instanceof Ball) {

            collider.ballOnBall(ball, (Ball) object);
        }
        else if(object instanceof Paddle) {

            collider.updateBall(ball, object);
            collider.ballOnPaddle(ball, (Paddle) object);
        }
        else if(object instanceof Brick) {

            //((Brick) object).damageBrick(0);
            collider.updateBall(ball, object);
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

            if(!ball.getBounds().intersects(objB.getX(), objB.getY(), objB.getWidth(), objB.getHeight())) {

                continue;
            }

            objects.add(objB);
            break;
        }

        return objects;
    }
}