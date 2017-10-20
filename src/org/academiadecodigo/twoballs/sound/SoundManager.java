package org.academiadecodigo.twoballs.sound;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by miro on 20/10/2017.
 */
public class SoundManager {

    private static SoundManager instance;

    private HashMap<GameSound, Sound> sounds = new HashMap<>();

    public static SoundManager getInstance() {

        return instance;
    }

    public SoundManager init() {

        instance = this;
        for (GameSound sound : GameSound.values()) {

            try {

                sounds.put(sound, new Sound(sound.getPath()));
            } catch (IOException e) {

                sounds.put(sound, null);
                System.out.println("Sound not found " + sound.getPath());
            }
        }

        return this;
    }

    public void dispose() {

        for (GameSound sound : sounds.keySet()) {

            if (sounds.get(sound) == null) {

                continue;
            }

            sounds.get(sound).close();
        }
    }

    public void playSound(GameSound gameSound) {

        Sound s = sounds.get(gameSound);

        if (s == null) {

            System.out.println("Sound not found " + gameSound);
            return;
        }


        if (s.isPlaying()) {

            return;
        }

        s.play(true);
    }

    public GameSound gargalhada() {

        int x = (int) (Math.random() * 3);

        switch (x) {
            case 0:
                return GameSound.GARGALHADA1;
            default:
                return GameSound.GARGALHADA2;

        }

    }

    public GameSound drsh() {
        int x = (int) (Math.random() * 5);

        switch (x) {
            case 0:
                return GameSound.DRSH6;
            case 1:
                return GameSound.DRSH5;
            case 2:
                return GameSound.DRSH4;
            case 3:
                return GameSound.DRSH3;
            case 4:
                return GameSound.DRSH2;
            default:
                return GameSound.DRSH1;
        }

    }
}