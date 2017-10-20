package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.gameobjects.Ball;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;
import org.academiadecodigo.twoballs.manage.CollisionDetector;
import org.academiadecodigo.twoballs.manage.ObjectFactory;
import org.academiadecodigo.twoballs.manage.ScoreManager;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.academiadecodigo.twoballs.input.KeyboardManager.*;

/**
 * Created by codecadet on 16/10/17.
 */
public class Stage {

    public static final int PADDING = 10;

    ScoreManager scoreManager;

    private Picture backGround;

    private Set<GameObject> gameObjectsToAdd = new HashSet<>();

    private Set<GameObject> gameObjectsToRemove = new HashSet<>();

    private Set<GameObject> gameObjects = new HashSet<>();

    private Paddle player1;

    private Paddle player2;

    private Ball controlledBall;

    private CollisionDetector collisionDetector = new CollisionDetector();

    Stage() {

        this.backGround = new Picture(PADDING, PADDING, "assets/background.jpg");
        this.backGround.draw();

        scoreManager = new ScoreManager();
        scoreManager.draw();

        new ObjectFactory(this);
    }

    void initializeObjects() {

        gameObjects.add(player1 = ObjectFactory.getLeftPaddle("blue"));
        gameObjects.add(player2 = ObjectFactory.getRightPaddle("red"));
        for(int i = 0; i < 10; i++) {

            //gameObjects.add(ObjectFactory.getNewBall(GameScreen.getWidth() / 2 + 200, GameScreen.getHeight() / 2, 1, 1));
        }
        gameObjects.add(controlledBall = ObjectFactory.getNewBall(GameScreen.getWidth() / 2 - 200, GameScreen.getHeight() / 2, -1, 0));

        int xRange = 5;
        int yRange = 8;
        int brickWidth = 32;
        int brickHeight = 64;
        int brickSpacing = 0;

        int initialX = (GameScreen.getWidth() - (xRange * (brickWidth + brickSpacing))) / 2;
        for(int y = 0; y < yRange; y++) {

            for(int x = 0; x < xRange; x++) {

                int dur = 0;
                if(x == 1 || x == 3) {

                    dur = 1;
                }
                else if(x == 2) {

                    dur = 2;
                }

                gameObjects.add(ObjectFactory.getNewBrick(initialX + x * (brickWidth + brickSpacing), 26 + y * (brickHeight + brickSpacing), dur));
            }
        }
    }

    public void run(float delta) {

        //Add objects here
        if(!gameObjectsToAdd.isEmpty()) {

            gameObjects.addAll(gameObjectsToAdd);
            gameObjectsToAdd.clear();
        }

        //Remove objects here
        if(!gameObjectsToRemove.isEmpty()) {

            for(GameObject goB : gameObjectsToRemove) {

                Iterator<GameObject> copy = gameObjects.iterator();
                while(copy.hasNext()) {

                    GameObject go = copy.next();
                    if(go.equals(goB)) {

                        Canvas.getInstance().hide(go.getShape());
                        copy.remove();
                    }
                }
            }

            gameObjectsToRemove.clear();
        }

        Iterator<GameObject> copy = gameObjects.iterator();
        while(copy.hasNext()) {

            GameObject object = copy.next();

            if(!object.isDead()) {

                if(object instanceof Movable) {

                    if(object.equals(controlledBall)) {

                        collisionDetector.checkCollisions((Ball) object, gameObjects);
                        continue;
                    }
                    ((Movable) object).move(delta);
                }

                if(!(object instanceof Ball)) {

                    continue;
                }

                collisionDetector.checkCollisions((Ball) object, gameObjects);
                continue;
            }

            copy.remove();
            removeObject(object);
        }

        //oldCollisionDetector.checkCollision(gameObjects);
    }


    void keyPressed(int key) {

        if(handleKey(player1, key, P1_UP, P1_DOWN)) {

            return;
        }

        if(handleKey(player2, key, P2_UP, P2_DOWN)) {

            //TODO Return if more keys
            return;
        }

        if(key == P3_DOWN || key == P3_UP || key == P3_LEFT || key == P3_RIGHT) {

            int dx = key == P3_RIGHT ? 1 : (key == P3_LEFT ? -1 : 0);
            int dy = key == P3_UP ? -1 : (key == P3_DOWN ? 1 : 0);
            int speed = 5;
            controlledBall.translate(dx * speed, dy * speed);
        }
    }

    private boolean handleKey(Paddle player, int key, int up, int down) {

        if(key == down || key == up) {

            player.updateDirection(key == up ? -1 : 1);
            return true;
        }

        return false;
    }

    void keyReleased(int key) {

        if(key == P1_DOWN || key == P1_UP) {

            player1.updateDirection(0);
        }

        if(key == P2_DOWN || key == P2_UP) {

            player2.updateDirection(0);
        }
    }

    public void removeObject(GameObject object) {

        gameObjectsToRemove.add(object);
    }

    public void spawnObject(GameObject object) {

        gameObjectsToAdd.add(object);
    }
}