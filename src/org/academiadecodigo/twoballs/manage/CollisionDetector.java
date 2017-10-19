package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.simplegraphics.graphics.Shape;
import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by codecadet on 18/10/17.
 */
public class CollisionDetector {

    private Collider collider = new Collider();

    private List<Shape> shapesOnTop(Set<GameObject> gameObjects, GameObject a) {

        List<Shape> shapes = new ArrayList<>();

        for(GameObject b : gameObjects) {

            if(a.equals(b)) {

                continue;
            }

            int buffer = 2;
            int x = a.getX();
            int rightX = x + a.getWidth();
            int y = a.getY();
            int rightY = y + a.getHeight();

            boolean topLeftSameX = x + buffer >= b.getX() - buffer && x - buffer <= b.getX() + b.getWidth() + buffer;
            boolean topLeftSameY = y + buffer >= b.getY() - buffer && y - buffer <= b.getY() + b.getHeight() + buffer;

            boolean bottomRightSameX = rightX + buffer >= b.getX() - buffer && rightX <= b.getX() + b.getWidth();
            boolean bottomRightSameY = rightY + buffer >= b.getY() - buffer && rightY <= b.getY() + b.getHeight();

            //System.out.println("A: " + (topLeftSameX && topLeftSameY));
            //System.out.println("B: " + (bottomRightSameX && bottomRightSameY));

            if((topLeftSameX && topLeftSameY) || (bottomRightSameX && bottomRightSameY)) {

                shapes.add(b.getShape());
            }
        }

        return shapes;
    }


    public void checkCollision(Set<GameObject> objSet) {

        Iterator<GameObject> iterator = objSet.iterator();
        while(iterator.hasNext()) {

            GameObject gameObject = iterator.next();

            if(!(gameObject instanceof Ball)) {

                continue;
            }

            List<Shape> shapes = shapesOnTop(objSet, gameObject);

            Iterator<GameObject> innerIterator = objSet.iterator();
            while(innerIterator.hasNext()) {

                GameObject objectB = innerIterator.next();

                if(!shapes.contains(objectB.getShape())) {

                    continue;
                }

                if(objectB instanceof Paddle) {

                    collide((Ball) gameObject, (Paddle) objectB);
                    //collider.collide((Ball) gameObject, (Paddle) objectB);
                }
                else if(objectB instanceof Ball) {

                    collider.collide((Ball) gameObject, (Ball) objectB);
                }
            }
        }
    }

    private void collide(Ball ball, Paddle paddle) {

        //System.out.println(ball + "_____" + paddle);

        //if on the left side of the paddle
        int buffer = 2;
        int x = ball.getX();
        int rightX = x + ball.getWidth();
        int y = ball.getY();
        int rightY = y + ball.getHeight();

        boolean left = rightX + buffer <= paddle.getX() - buffer;
        boolean down = x + buffer <= paddle.getY();
        boolean up = y >= paddle.getY() + paddle.getHeight();

        System.out.println(left);
    }
}