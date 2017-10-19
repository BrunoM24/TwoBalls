package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.twoballs.gameobjects.Brick;
import org.academiadecodigo.twoballs.gameobjects.GameObject;
import org.academiadecodigo.twoballs.gameobjects.Paddle;
import org.academiadecodigo.twoballs.gameobjects.move.Movable;
import org.academiadecodigo.twoballs.manage.CollisionDetector;
import org.academiadecodigo.twoballs.manage.ObjectFactory;
import org.academiadecodigo.twoballs.manage.ScoreManager;

import java.util.HashSet;
import java.util.Set;

import static org.academiadecodigo.twoballs.input.KeyboardManager.*;

/**
 * Created by codecadet on 16/10/17.
 */
public class Stage {

    public static final int PADDING = 10;

    private Rectangle bkgRectangle;

    private Set<GameObject> gameObjects = new HashSet<>();  //elaborate a functional SET list

    private Paddle player1;

    private Paddle player2;

    private CollisionDetector collisionDetector = new CollisionDetector();

    ScoreManager scoreManager;

    public Stage(int width, int height) {

        this.bkgRectangle = new Rectangle(PADDING, PADDING, width, height);

        bkgRectangle.draw();

        scoreManager = new ScoreManager();
        scoreManager.draw();
    }

    public void initializeObjects() {

        gameObjects.add(player1 = ObjectFactory.getLeftPaddle("blue"));
        gameObjects.add(player2 = ObjectFactory.getRightPaddle("red"));

        int numberOfBalls = 3;

        for(int i = 1; i <= numberOfBalls; i++) {

            gameObjects.add(ObjectFactory.getNewBall(150, 150));
        }

        //TODO SPAWN BRICKS
        int xRange = 7;
        int yRange = 5;
        int brickWidth = 32;
        int brickHeight = 64;
        int brickSpacing = 2;


        System.out.println("Hright : " + GameScreen.getHeight() + " Width : " + GameScreen.getWidth());
        System.out.println();

        for(int y = 0; y < yRange; y++) {

            new Brick (400+brickWidth*y+brickSpacing*y, 58);



            for(int x = 0; x < xRange; x++) {

               new Brick (400+brickWidth*y+brickSpacing*y, 58+brickHeight*x+brickSpacing*x );
            }
        }

        //gameObjects.add(ObjectFactory.getNewBrick(200, 200));
    }

    public void run(float delta) {

        for(GameObject object : gameObjects) {

            if(object instanceof Movable) {

                ((Movable) object).move(delta);
            }
        }

        collisionDetector.checkCollision(gameObjects);
    }


    void keyPressed(int key) {

        if(handleKey(player1, key, P1_UP, P1_DOWN)) {

            return;
        }

        if(handleKey(player2, key, P2_UP, P2_DOWN)) {

            return;
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
}