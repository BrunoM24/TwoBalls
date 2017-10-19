package org.academiadecodigo.twoballs;

import org.academiadecodigo.simplegraphics.graphics.Text;

/**
 * TwoBalls Created by BrunoM24 on 17/10/2017.
 */
public class ScoreManager {

    int[] score = new int[3];

    Text tP1Score;
    Text tP2Score;

    public void draw() {

        tP1Score = new Text(50, 40, score[1] + "");
        tP2Score = new Text(GameScreen.getWidth() - 100, 40, score[2] + "");
        System.out.println("Width: " + tP1Score.getWidth() + " Height: " + tP1Score.getHeight());
        tP1Score.grow(5, 10);
        tP2Score.grow(5, 10);
        System.out.println("Width: " + tP1Score.getWidth() + " Height: " + tP1Score.getHeight());
        tP1Score.draw();
        tP2Score.draw();
    }


    public void setScore(int player, int points) {

        score[player] += points;
        Text text;

        if (player == 1) {
            text = tP1Score;
        } else {
            text = tP2Score;
        }

        int textSize = text.getText().length();
        text.setText(Integer.toString(score[player]));
        if (textSize != text.getText().length()) {
            System.out.println(text.getText().length());
            text.grow(5 * text.getText().length(), 0);
            System.out.println("Width: " + tP1Score.getWidth() + " Height: " + tP1Score.getHeight());
        }
    }

}