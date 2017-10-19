package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * Created by codecadet on 19/10/17.
 */
public class PauseText {

    Text pauseText;
    String textToDisplay = " -PAUSE- ";

    public PauseText() {
        pauseText = new Text (470,272,textToDisplay);
        pauseText.setColor(Color.DARK_GRAY);
        pauseText.grow(80,25);
        // Checking coordinates and pixels
        // System.out.println("X = " + pauseText.getX() + "\nY = " + pauseText.getY() + ";\nWidth = " + pauseText.getWidth() + "\nHeight = " + pauseText.getHeight());
    }


    public void draw() {
        pauseText.draw();
    }

    public void delete() {
        pauseText.delete();
    }

}
