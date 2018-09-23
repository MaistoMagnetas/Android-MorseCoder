package lovedice.cactustree.pc.morsecode;

import android.hardware.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates flash pattern from given morse code.
 */
class FlashMorse {

    private Camera cam;

    FlashMorse(String convertedMorseCode) throws Exception {
        MainMorseConverter morseConverter = new MainMorseConverter(convertedMorseCode);
        generateFlashPattern(morseConverter.getConvertedMorseText());
    }

    private void generateFlashPattern(String givenMorseCode) throws Exception {
        List<Character> charArray = turnStringToCharArray(givenMorseCode);
        int short_gap = 100;
        for (Character ch : charArray) {
            switch (ch) {
                case '•':
                    int dotLength = 80;
                    flashLightOn(dotLength);
                    flashLightOff(short_gap);
                    break;
                case '-':
                    int dashLength = 250;
                    flashLightOn(dashLength);
                    flashLightOff(short_gap);
                    break;
                case '/':
                    Thread.sleep(350);
                    break;
                case ' ':
                    Thread.sleep(100);
                    break;
            }
        }
    }

    private List<Character> turnStringToCharArray(String givenMorseCode) {
        char[] charArray = givenMorseCode.toCharArray();
        List<Character> charList = new ArrayList<>();
        for (char ch : charArray) {
            charList.add(ch);
        }
        return charList;
    }

    /**
     * Turns light on.
     */
    private void flashLightOn(int duration) throws Exception {
        cam = Camera.open();
        Camera.Parameters p = cam.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        cam.setParameters(p);
        cam.startPreview();
        Thread.sleep(duration);
    }

    /**
     * Turns light off
     */
    private void flashLightOff(int duration) throws Exception {
        cam.stopPreview();
        cam.release();
        cam = null;
        Thread.sleep(duration);
    }
}
