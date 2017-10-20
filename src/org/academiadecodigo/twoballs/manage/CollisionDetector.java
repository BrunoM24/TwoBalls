package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by miro on 20/10/2017.
 */
public class CollisionDetector {

    public void checkCollisions(GameObject objA, Set<GameObject> gameObjects) {

        ArrayList<Rectangle> objsOnTop = objectsOnTop(objA, gameObjects);

        if(objsOnTop.isEmpty()) {

            return;
        }

    }

    private ArrayList<Rectangle> objectsOnTop(GameObject object, Set<GameObject> gameObjects) {

        ArrayList<Rectangle> rects = new ArrayList<>();

        Iterator<GameObject> iterator = gameObjects.iterator();
        while(iterator.hasNext()) {

            GameObject objB = iterator.next();

            if(!(object instanceof Ball)) {

                continue;
            }

            if(objB.equals(object)) {

                continue;
            }


            int buffer = 2;
            if(!object.getBounds().intersects(objB.getX() - buffer, objB.getY() - buffer, objB.getWidth() + buffer, objB.getHeight() + buffer)) {

                continue;
            }

            rects.add(objB.getBounds());
        }

        return rects;
    }
}