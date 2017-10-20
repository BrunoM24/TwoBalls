package org.academiadecodigo.twoballs.input;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.keyboard.VKeyboard;
import org.academiadecodigo.twoballs.Game;
import org.academiadecodigo.twoballs.sound.GameSound;
import org.academiadecodigo.twoballs.sound.SoundManager;

import java.awt.event.KeyEvent;

/**
 * Created by miro on 17/10/2017.
 */
public class KeyboardManager implements KeyboardHandler {

    public static final int P1_UP = KeyEvent.VK_W;
    public static final int P2_UP = KeyEvent.VK_UP;
    public static final int P1_DOWN = KeyEvent.VK_S;
    public static final int P2_DOWN = KeyEvent.VK_DOWN;

    public static final int P3_UP = KeyEvent.VK_I;
    public static final int P3_DOWN = KeyEvent.VK_K;
    public static final int P3_LEFT = KeyEvent.VK_J;
    public static final int P3_RIGHT = KeyEvent.VK_L;

    public static final int GAME_PAUSE = KeyEvent.VK_P;
    public static final int GAME_END = KeyEvent.VK_ESCAPE;

    VKeyboard keyboard;

    Game game;

    public KeyboardManager(Game game) {

        this.game = game;

        keyboard = new VKeyboard(this);

        keyboard.listenToKey(P1_DOWN);
        keyboard.listenToKey(P1_UP);

        keyboard.listenToKey(P2_DOWN);
        keyboard.listenToKey(P2_UP);

        keyboard.listenToKey(GAME_PAUSE);
        keyboard.listenToKey(GAME_END);

        keyboard.listenToKey(P3_DOWN);
        keyboard.listenToKey(P3_UP);
        keyboard.listenToKey(P3_RIGHT);
        keyboard.listenToKey(P3_LEFT);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        game.keyPressed(keyboardEvent.getKey());
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        game.keyReleased(keyboardEvent.getKey());
    }

    @Override
    public void keyTyped(KeyboardEvent keyboardEvent) {

    }
}