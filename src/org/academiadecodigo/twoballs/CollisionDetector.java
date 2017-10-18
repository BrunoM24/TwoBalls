package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.GameObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by codecadet on 18/10/17.
 */
public class CollisionDetector {

    private ArrayList<Shape> shapesOnTop(GameObject object) {

        return Canvas.getInstance().getShapesInArea(object.getX(), object.getY(), object.getX() + object.getWidth(), object.getY() + object.getHeight());
    }


    private ArrayList<Shape> isTouching(Set<GameObject> gameObjects, GameObject object){

        List<Shape> list = new ArrayList<>();

        for (GameObject obj : gameObjects){

            if(obj.equals(object) ){
                continue;
            }

            boolean abTouchX = object.getX()>= obj.getX() && object.getX() <= obj.getX()+obj.getWidth();
            //boolean baTouchX


            boolean abTouchY = object.getY()<=obj.getY() && object.getY() >= obj.getY()+obj.getHeight();





        }


        return  new ArrayList<Shape>();
    }



    public void checkCollision(Set<GameObject> gameObjects, GameObject object) {

        List<Shape> shapeList = shapesOnTop(object);

        for (GameObject objectB : gameObjects) {

            if (objectB.equals(object)) {
                continue;
            }

            if (!shapeList.contains(objectB.getShape())) {
                continue;
            }
            //a e b colidem

            collide(object, objectB);
        }
    }

    private void collide(GameObject object, GameObject objectB) {

        System.out.println(object + " e " + objectB);
        /* precisamos de saber se algum dos objectos é bola.
        se for bola, vamos ter que inverter o x e y.
         */

        if (object instanceof Ball) {
            ((Ball) object).changeX();
        }

    }
}