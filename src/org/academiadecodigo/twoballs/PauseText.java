package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * Created by codecadet on 19/10/17.p
 */
public class PauseText {

    static Text fpsText;

    Text pauseText;

    //Text pauseText2;

    boolean alreadyPausedOnce = false;

    String paused = " -PAUSED- ";

    String pressPToStart = " -Press P To Start- ";

    Color defaultColor = Color.DARK_GRAY;

    public PauseText() {

        pauseText = new Text(470, 272, pressPToStart);
        pauseText.setColor(defaultColor);
        pauseText.grow(160, 25);

        /*
        pauseText2 = new Text(470, 272, paused);
        pauseText2.setColor(defaultColor);
        pauseText2.grow(80, 25);
*/
        fpsText = new Text(GameScreen.getX(), GameScreen.getHeight() - 10, "?");
        fpsText.setColor(defaultColor);
        fpsText.draw();
    }


    public void draw() {

        if(alreadyPausedOnce) {

            if(!pauseText.getText().equals(paused)) {

                pauseText.grow(-80, 0);
            }

            pauseText.setText(paused);
            // pauseText2.draw();
        }

        pauseText.draw();
    }

    public void delete() {

        pauseText.delete();
        setAlreadyPausedOnce();
        //pauseText2.delete();
    }

    public void setAlreadyPausedOnce() {

        alreadyPausedOnce = true;
    }
}