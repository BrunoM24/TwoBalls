package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * Created by codecadet on 19/10/17.
 */
public class PauseText {

    static Text fpsText;

    Text pauseText;

    Text pauseText2;

    boolean alreadyPausedOnce = false;

    String paused = " -PAUSED- ";

    String pressPToStart = " -Press P To Start- ";

    public PauseText() {

        pauseText = new Text(470, 272, pressPToStart);
        pauseText.setColor(Color.YELLOW);
        pauseText.grow(80, 25);

        pauseText2 = new Text(470, 272, paused);
        pauseText2.setColor(Color.YELLOW);
        pauseText2.grow(80, 25);

        fpsText = new Text(GameScreen.getX(), GameScreen.getHeight() - 10, "?");
        fpsText.setColor(Color.WHITE);
        fpsText.draw();
    }


    public void draw() {

        if(alreadyPausedOnce) {

            pauseText2.draw();
            return;
        }

        pauseText.draw();
    }

    public void delete() {

        pauseText.delete();
        setAlreadyPausedOnce();
        pauseText2.delete();
    }

    public void setAlreadyPausedOnce() {

        alreadyPausedOnce = true;
    }
}