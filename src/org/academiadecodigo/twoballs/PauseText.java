package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * Created by codecadet on 19/10/17
 */
public class PauseText {

    static final String paused = " -PAUSED- ";

    static final String pressPToStart = " -Press P To Start- ";

    //Text pauseText2;

    static final String PLAYER_WIN = " -Press P To Start- ";

    static Text fpsText;

    Text pauseText;

    private boolean alreadyPausedOnce = false;

    private Color defaultColor = Color.DARK_GRAY;

    PauseText() {

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

    void updateText(String text) {

        if(pauseText.getText().equals(text)) {

            return;
        }

        pauseText.setText(text);
        pauseText.draw();
    }

    void draw() {

        if(alreadyPausedOnce) {

            if(!pauseText.getText().equals(paused)) {

                pauseText.grow(-80, 0);
            }

            pauseText.setText(paused);
            // pauseText2.draw();
        }

        pauseText.draw();
    }

    void delete() {

        pauseText.delete();
        setAlreadyPausedOnce();
        //pauseText2.delete();
    }

    private void setAlreadyPausedOnce() {

        alreadyPausedOnce = true;
    }
}