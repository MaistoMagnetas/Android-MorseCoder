package lovedice.cactustree.pc.morsecode;

import android.media.AudioManager;
import android.media.ToneGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for playing dot and dash sounds according to given morse code conversion.
 */
class SoundMorse {

    SoundMorse(String convertedMorseCode) {
        MainMorseConverter morseConverter = new MainMorseConverter(convertedMorseCode);
        List<Integer> integers = generatedSoundPattern(morseConverter.getConvertedMorseText());
        try {
            makeSounds(integers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Makes sounds by using tone generator.
     *
     * @param soundsList sound list pattern.
     * @throws Exception if something fails. (catch above).
     */
    private void makeSounds(List<Integer> soundsList) throws Exception {
        int STREAM_TYPE = AudioManager.STREAM_MUSIC;
        int VOLUME = 80;
        ToneGenerator toneGeneratorDot = new ToneGenerator(STREAM_TYPE, VOLUME);
        ToneGenerator toneGeneratorDash = new ToneGenerator(STREAM_TYPE, VOLUME);

        Thread.sleep(300);
        for (Integer sound : soundsList) {
            int PAUSE_TIME_DELAY = 300;
            if (sound == 0) //dot
            {
                int DOT_DURATION = 100;
                int TONE_TYPE_DOT = ToneGenerator.TONE_DTMF_1;
                toneGeneratorDot.startTone(TONE_TYPE_DOT, DOT_DURATION);
                Thread.sleep(DOT_DURATION + PAUSE_TIME_DELAY);
            } else if (sound == 1) //dash
            {
                int DASH_DURATION = 200;
                int TONE_TYPE_DASH = ToneGenerator.TONE_DTMF_0;
                toneGeneratorDash.startTone(TONE_TYPE_DASH, DASH_DURATION);
                Thread.sleep(DASH_DURATION + PAUSE_TIME_DELAY);
            } else if (sound == 2) //space
            {
                Thread.sleep(PAUSE_TIME_DELAY/2);
            } else if (sound == 3) //word end
            {
                Thread.sleep(PAUSE_TIME_DELAY);
            }
        }
        toneGeneratorDash.stopTone();
        toneGeneratorDot.stopTone();

        toneGeneratorDash.release();
        toneGeneratorDot.release();
    }

    private List<Integer> generatedSoundPattern(String givenMorseCode) {
        List<Integer> sounds = new ArrayList<>();
        List<Character> charArray = turnStringToCharArray(givenMorseCode);
        for (Character ch : charArray) {
            switch (ch) {
                case '•':
                    sounds.add(0); // dot
                    break;
                case '-':
                    sounds.add(1); //dash
                    break;
                case ' ':
                    sounds.add(2); //space
                case '/':
                    sounds.add(3); //word end
            }
        }
        return sounds;
    }

    private List<Character> turnStringToCharArray(String givenMorseCode) {
        char[] charArray = givenMorseCode.toCharArray();
        List<Character> charList = new ArrayList<>();
        for (char ch : charArray) {
            charList.add(ch);
        }
        return charList;
    }
}
