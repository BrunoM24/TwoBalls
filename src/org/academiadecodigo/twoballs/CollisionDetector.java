package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by codecadet on 18/10/17.
 */
public class CollisionDetector {

    private List<Shape> isTouching(Set<GameObject> gameObjects, GameObject areaObject) {

        List<Shape> list = new ArrayList<>();

        for(GameObject objectB : gameObjects) {

            if(objectB.equals(areaObject)) {

                continue;
            }

            //TODO Increase the area a bit to include FUTURE TOUCH
            // TODO  isTouching conditions still need to be tested
            boolean abTouchX = (areaObject.getX() >= objectB.getX() && areaObject.getX() <= objectB.getX() + objectB.getWidth()) || ((areaObject.getX() + areaObject.getWidth() >= objectB.getX()) && (areaObject.getX() + areaObject.getWidth() <= objectB.getX() + objectB.getWidth()));

            boolean abTouchY = (areaObject.getY() >= objectB.getY() && areaObject.getY() <= objectB.getY() + objectB.getHeight()) || ((areaObject.getY() + areaObject.getHeight() >= objectB.getY()) && (areaObject.getY() + areaObject.getHeight() <= objectB.getY() + objectB.getHeight()));

            if(!(abTouchX && abTouchY)) {

                continue;
            }

            list.add(objectB.getShape());
        }

        return list;
    }


    public void checkCollision(Set<GameObject> gameObjects, GameObject object) {

        List<Shape> shapeList = isTouching(gameObjects, object);

        if(shapeList.size() < 1) {

            return;
        }

        for(GameObject objectB : gameObjects) {

            if(objectB.equals(object)) {
                continue;
            }

            if(!shapeList.contains(objectB.getShape())) {
                continue;
            }
            //a e b colidem

            collide(object, objectB);
        }
    }

    private void collide(GameObject object, GameObject objectB) {

        if(object instanceof Ball) {

            ((Ball) object).changeX();//TODO Add timer to prevent multiple changes in 1 frame
            //if touched on top || if touched on bottom
            //flip y
            //if touched on left || if touched on right
            //flip x
        }
    }
}