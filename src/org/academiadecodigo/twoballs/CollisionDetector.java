package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by codecadet on 18/10/17.
 */
public class CollisionDetector {

    private List<Shape> isTouching(Set<GameObject> gameObjects, GameObject areaObject) {

        List<Shape> list = new ArrayList<>();

        int buffer = 2;
        for (GameObject objectB : gameObjects) {

            if (objectB.equals(areaObject)) {

                continue;
            }


            int x = areaObject.getX();
            int rightX = x + areaObject.getWidth();
            int y = areaObject.getY();
            int rightY = y + areaObject.getHeight();

            // TODO  isTouching conditions still need to be tested
            boolean leftXTouching = x + buffer >= objectB.getX() - buffer && x - buffer <= objectB.getX() + objectB.getWidth() + buffer;
            boolean leftYTouching = y + buffer >= objectB.getY() - buffer && y - buffer <= objectB.getY() + objectB.getHeight() + buffer;
            boolean rightXTouching = rightX + buffer >= objectB.getX() - buffer && rightX <= objectB.getX() + objectB.getWidth();
            boolean rightYTouching = rightY + buffer >= objectB.getY() - buffer && rightY <= objectB.getY() + objectB.getHeight();

            boolean abTouchX = leftXTouching || rightXTouching;

            boolean abTouchY = leftYTouching || rightYTouching;

            if (!(abTouchX && abTouchY)) {

                continue;
            }

            list.add(objectB.getShape());
        }

        return list;
    }


    public void checkCollision(Set<GameObject> gameObjects, GameObject object) {

        List<Shape> shapeList = isTouching(gameObjects, object);

        if (shapeList.size() < 1) {

            return;
        }

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

        if (object instanceof Ball) {

            //((Ball) object).flipX();//TODO Add timer to prevent multiple changes in 1 frame
            //if touched on top || if touched on bottom
            //flip y
            //if touched on left || if touched on right
            //flip x
        }

        //TODO Eduardo working on paddle ball interaction


        if (object instanceof Ball && objectB instanceof Paddle) {

            float ballCenterLine = object.getY() + (1f / 2f) * object.getHeight();
            float firstDivision = paddleHeightDivision(1, objectB);
            float secondDivision = paddleHeightDivision(2, objectB);


            boolean touchCenter = (ballCenterLine >= firstDivision && ballCenterLine <= secondDivision);

            boolean touchUp = (object.getY() + object.getHeight() >= objectB.getY()) && (ballCenterLine < (objectB.getY() + (1f / 3f) * objectB.getHeight()));
            boolean touchDown = (object.getY() <= objectB.getY() + objectB.getHeight()) && (ballCenterLine > (objectB.getY() + (2f / 3f) * objectB.getHeight()));
            ;
            ((Ball) object).flipX(false);

            if (touchUp) {
                if (((Ball) object).getDirectionY() > 0) {
                    ((Ball) object).flipY(true);
                }
                countUp++;
                System.out.println("touching up " + countUp);
            }

            if (touchDown) {
                if (((Ball) object).getDirectionY() < 0) {
                    ((Ball) object).flipY(true);
                }

                countDown++;
                System.out.println("touching down baby..hmmm " + countDown);
            }

            if (touchCenter) {
                countCenter++;
                System.out.println("na muche CENTER " + countCenter);
            }

        }
    }

    private float paddleHeightDivision(int divisionNumber, GameObject object) {
        return (object.getY() + (divisionNumber / 3f) * object.getHeight());
    }

    private int countCenter = 0;
    private int countDown = 0;
    private int countUp = 0;
}