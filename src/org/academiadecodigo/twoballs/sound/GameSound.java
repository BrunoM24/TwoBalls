package org.academiadecodigo.twoballs.sound;

/**
 * Created by miro on 20/10/2017.
 */
public enum GameSound {

    BOUNCE("bounce"),
    POINTS("points"),
    BKG_LOOP("bkg_loop"),
    PUF("puf");

    private String path;

    GameSound(String sound) {

        path = "/assets/sound/" + sound + ".wav";
    }

    public String getPath() {

        return path;
    }
}