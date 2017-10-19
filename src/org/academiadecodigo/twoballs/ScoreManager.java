package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * TwoBalls Created by BrunoM24 on 17/10/2017.
 */
public class ScoreManager {

    private int p1Score = 0;
    private int p2Score = 50;
    Text tP1Score;
    Text tP2Score;

    public void draw() {

        String s1 = Integer.toString(p1Score);
        String s2 = Integer.toString(p2Score);


        tP1Score = new Text(50, 40, s1);
        tP2Score = new Text(GameScreen.getWidth() - 100, 40, s2);
        tP1Score.grow(5 * s1.length(), 10);
        tP1Score.grow(5 * s1.length(), 10);
        tP1Score.draw();
        tP2Score.draw();

        setScore(10000000);
    }


    public void setScore(int score) {

        p1Score = score;
        tP1Score.setText(Integer.toString(score));
        tP1Score.grow(5 * tP1Score.getText().length(), 10);
    }

    /*
    Text t2 = new Text(115, 70, "Points " + score);
        t2.setColor(Color.RED);
        t2.grow(50, 10);
        t2.draw();
     */
}