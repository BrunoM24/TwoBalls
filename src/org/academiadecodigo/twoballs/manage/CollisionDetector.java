package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.GameObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by miro on 20/10/2017.
 */
public class CollisionDetector {

    int buffer = 2;
    private Collider collider = new Collider();

    public void checkCollisions(Ball ball, Set<GameObject> gameObjects) {

        ArrayList<GameObject> objsOnTop = objectsOnTop(ball, gameObjects);

        if(objsOnTop.isEmpty()) {

            return;
        }

        for(GameObject object : objsOnTop) {

            //Bola vira pra tras
            if(object instanceof Ball) {

                collider.ballOnBall(ball, (Ball) object);
            }
        }
    }

    private ArrayList<GameObject> objectsOnTop(Ball ball, Set<GameObject> gameObjects) {

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