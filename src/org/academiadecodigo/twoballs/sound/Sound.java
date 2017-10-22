package org.academiadecodigo.twoballs.sound;

/**
 * Created by miro on 20/10/2017.
 */

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sound {

    private Clip clip;

    private URL soundURL;

    public Sound(String path) throws IOException {

        initClip(path);
    }

    /**
     * Plays the clip from the point it was stopped or from start if passed with the fromStart argument false or true
     *
     * @param fromStart should be true if want to replay the sound from the start or false otherwise
     */
    public void play(boolean fromStart) {

        /*
        if(fromStart) {

            clip.setFramePosition(0);
        }

        clip.start();*/
    }

    public void stop() {

        clip.stop();
    }

    public void close() {

        clip.close();
    }

    public boolean isPlaying() {

        return clip.isActive();
    }

    public int getLength() {

        return clip.getFrameLength();
    }

    public void loopIndef() {

        //sets loop points at start and end of track
        clip.setLoopPoints(0, (int) (getLength() * 0.94));

        //activates loop
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void reOpen() throws IOException {

        AudioInputStream inputStream = null;

        try {

            inputStream = AudioSystem.getAudioInputStream(soundURL);
            clip.open(inputStream);
        }
        catch(LineUnavailableException | IOException | UnsupportedAudioFileException ex) {

            throw new IOException(ex);
        }
    }

    private void initClip(String path) throws IOException {

        soundURL = Sound.class.getResource(path); //if loading from jar
        AudioInputStream inputStream = null;

        try {

            if(soundURL == null) {

                path = path.substring(1);
                File file = new File(path);
                soundURL = file.toURI().toURL(); //if executing on intellij
            }

            inputStream = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        }
        catch(UnsupportedAudioFileException | LineUnavailableException | IOException ex) {

            throw new IOException(ex);
        }
        catch(IllegalArgumentException e) { //Pulse audio em linux

            try {

                inputStream = AudioSystem.getAudioInputStream(new File(path));
                AudioFormat format = inputStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(inputStream);
                //clip.start();
            }
            catch(LineUnavailableException | UnsupportedAudioFileException | IOException e1) {

                e1.printStackTrace();
            }
        }
    }
}
