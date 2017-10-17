package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by codecadet on 16/10/17.
 */
public class Stage {

    public static final int PADDING = 10;

    private Rectangle bkgRectangle;

    private Set<GameObject> gameObjects = new HashSet<>();  //elaborate a functional SET list

    public Stage(int width, int height) {

        this.bkgRectangle = new Rectangle(PADDING, PADDING, width, height);

        bkgRectangle.draw();
    }

    public void initializeObjects() {

        gameObjects.add(ObjectFactory.getLeftPaddle(bkgRectangle, "blue"));
        gameObjects.add(ObjectFactory.getRightPaddle(bkgRectangle, "red"));
        gameObjects.add(ObjectFactory.getNewBall(bkgRectangle, 90, 90));
        gameObjects.add(ObjectFactory.getNewBall(bkgRectangle, 190, 190));
        gameObjects.add(ObjectFactory.getNewBrick(200, 200));
    }

    public void run(float delta) {

        //TODO: kill bricks
        //kill bricks
        //spawn powers if any
        //update objects

        for(GameObject object : gameObjects) {

            if(object instanceof Movable) {

                ((Movable) object).move();
            }

            object.checkCollision();
        }
    }
}