package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.twoballs.gameobjects.*;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;
import org.academiadecodigo.twoballs.manage.CollisionDetector;
import org.academiadecodigo.twoballs.manage.ScoreManager;
import org.academiadecodigo.twoballs.manage.Spawn;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.academiadecodigo.twoballs.input.KeyboardManager.*;

/**
 * Created by codecadet on 16/10/17.
 */
public class Stage {

    public static final int PADDING = 10;

    private ScoreManager scoreManager;

    private Picture backGround;

    private Set<GameObject> gameObjectsToAdd = new HashSet<>();

    private Set<GameObject> gameObjectsToRemove = new HashSet<>();

    private Set<GameObject> gameObjects = new HashSet<>();

    private PauseText titleText;

    private Paddle player1;

    private Paddle player2;

    private boolean running = true;

    //private Ball controlledBall;

    private CollisionDetector collisionDetector;

    private int brickCounter;

    Stage(PauseText pauseText) {

        collisionDetector = new CollisionDetector(this);

        this.backGround = new Picture(PADDING, PADDING, "background.jpg");
        this.backGround.draw();

        scoreManager = new ScoreManager();
        scoreManager.draw();

        new Spawn(this);

        this.titleText = pauseText;
    }

    void initializeObjects() {

        player1 = Spawn.newLeftPaddle("blue");
        player2 = Spawn.newRightPaddle("red");

        /*for(int i = 0; i < 10; i++) {

            Spawn.newBall(GameScreen.getWidth() / 2 + 200, GameScreen.getHeight() / 2, 1, 1);
        }*/
        //Spawn.newPowerUp(100, 100, 1);
        Spawn.newBall(GameScreen.getWidth() / 2 - 200, GameScreen.getHeight() / 2, -1, 0);
        Spawn.newBall(GameScreen.getWidth() / 2 + 200, GameScreen.getHeight() / 2, 1, 0);

        spawnBricks(5, 8, 32, 64, 0);
    }

    private void spawnBricks(int xRange, int yRange, int brickWidth, int brickHeight, int brickSpacing) {
        //TODO Read from file by colors (RGB)

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

                brickCounter++;
                Spawn.newBrick(initialX + x * (brickWidth + brickSpacing), 26 + y * (brickHeight + brickSpacing), dur);
            }
        }
    }

    void run(float delta) {

        if(titleText == null) {

            return;
        }

        if(brickCounter == 0) {

            titleText.updateText(PauseText.PLAYER_WIN.replace("X" , scoreManager.get));
            return;
            //spawnBricks(5, 8, 32, 64, 0);
        }

        //Add objects here
        if(!gameObjectsToAdd.isEmpty()) {

            gameObjects.addAll(gameObjectsToAdd);
            gameObjectsToAdd.clear();
        }

        cleanDeadObjects();

        Iterator<GameObject> copy = gameObjects.iterator();
        while(copy.hasNext()) {

            GameObject object = copy.next();

            if(!object.isDead()) {

                if(object instanceof Movable) {

                    /*if(object.equals(controlledBall)) {

                        collisionDetector.checkCollisions((Ball) object, gameObjects);
                        continue;
                    }*/
                    ((Movable) object).move(delta);
                }

                if(!(object instanceof Ball) && !(object instanceof PowerUp)) {

                    continue;
                }

                collisionDetector.checkCollisions(object, gameObjects);
                continue;
            }

            copy.remove();
            removeObject(object);
        }
    }

    private void cleanDeadObjects() {

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
    }

    void keyPressed(int key) {

        if(handleKey(player1, key, P1_UP, P1_DOWN)) {

            return;
        }

        if(handleKey(player2, key, P2_UP, P2_DOWN)) {

            return;
        }

        if(key == P3_DOWN || key == P3_UP || key == P3_LEFT || key == P3_RIGHT) {

            int dx = key == P3_RIGHT ? 1 : (key == P3_LEFT ? -1 : 0);
            int dy = key == P3_UP ? -1 : (key == P3_DOWN ? 1 : 0);
            int speed = 5;
            //controlledBall.translate(dx * speed, dy * speed);
            //TODO Return if more keys
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

        //brickCounter of bricks
        if(object instanceof Brick) {

            brickCounter--;
        }

        gameObjectsToRemove.add(object);
    }

    public void spawnObject(GameObject object) {

        gameObjectsToAdd.add(object);
    }

    boolean isRunning() {

        return running;
    }

    public void pUpCollideWith(PowerUp pUp, Paddle paddle) {

        switch(pUp.getPowerUp()) {

            case FREEZE:
                paddle.freeze(PowerUp.FREEZE_TIME);
                break;
            case FREEZE_OTHER: {

                Paddle playerToFreeze = player1;
                if(paddle.equals(player1)) {

                    playerToFreeze = player2;
                }

                playerToFreeze.freeze(PowerUp.FREEZE_TIME);
            }
            break;
        }
    }
}