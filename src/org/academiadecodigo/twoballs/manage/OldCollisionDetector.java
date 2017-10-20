package org.academiadecodigo.twoballs.manage;

import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.Brick;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by codecadet on 18/10/17.
 */
public class OldCollisionDetector {

    private OldCollider oldCollider = new OldCollider();

    private List<Rectangle> shapesOnTop(Set<GameObject> gameObjects, GameObject a) {

        List<Rectangle> shapes = new ArrayList<>();

        for(GameObject b : gameObjects) {

            if(a.equals(b)) {

                continue;
            }

            if(!a.getBounds().intersects(b.getBounds())) {

                continue;
            }

            shapes.add(b.getBounds());
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

            List<Rectangle> shapes = shapesOnTop(objSet, gameObject);

            Iterator<GameObject> innerIterator = objSet.iterator();
            while(innerIterator.hasNext()) {

                GameObject objectB = innerIterator.next();

                if(objectB.equals(gameObject)) {

                    continue;
                }

                if(!shapes.contains(objectB.getBounds())) {

                    continue;
                }

                if(objectB instanceof Paddle) {

                    //collide((Ball) gameObject, (Paddle) objectB);
                    oldCollider.collide((Ball) gameObject, (Paddle) objectB);
                }
                else if(objectB instanceof Ball) {

                    oldCollider.collide((Ball) gameObject, (Ball) objectB);
                }
                else if(objectB instanceof Brick) {

                    oldCollider.collide((Ball) gameObject, (Brick) objectB );
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

        ball.flipX(false);
        //ball move a little to the left
        ball.move(1.0f);
        //then update its position
    }
}