package lovedice.cactustree.pc.morsecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 9/16/2018.
 *
 * @author CactusTree
 */

/**
 * Generates vibration pattern from given morse code.
 */
class VibrationMorse {

    private final String convertedMorseCode;
    private long[] vibrationPattern;

    VibrationMorse(String convertedMorseCode) throws Exception {
        MainMorseConverter morseConverter = new MainMorseConverter(convertedMorseCode);
        this.convertedMorseCode = morseConverter.getConvertedMorseText();
        this.vibrationPattern = getVibrationPattern();
    }

    /**
     * Simple getter for returning generated vibration pannel.
     * @return vibration pattern.
     */
    long[] getPatternForVibrating() {
        return this.vibrationPattern;
    }

    /**
     * Gets vibration pattern from given string.
     * @return pattern of vibration
     */
    private long[] getVibrationPattern() throws Exception {
        return convertListtoPattern(generateVibrationPattern(convertedMorseCode));
    }

    /**
     * Generates vibration pattenr in List<Long> format.
     * @param convertedMorseCode given text to convert to.
     * @return List<Long> pattern.
     */
    private List<Long> generateVibrationPattern(String convertedMorseCode) throws Exception{
        List<Character> charList = convertGivenStringtoCharArray(convertedMorseCode);
        List<Long> patternList = new ArrayList<>();
        long short_gap = 200; // gap after single unit
        long dotLength = 200;
        long dashLength = 500;
        for (int i = 0; i < charList.size(); i++) {
            switch (charList.get(i)) {
                case '•':
                    patternList.add(dotLength);
                    patternList.add(short_gap); //Short gap required between dot and dash
                    break;
                case '-':
                    patternList.add(dashLength);
                    patternList.add(short_gap); //Short gap required between dot and dash
                    break;
                case ' ': //Space between words. always played x2
                    Thread.sleep(100);
                    //patternList.add(short_gap);
                    break;
                case '/':
                    Thread.sleep(350);
            }
        }
        return patternList;
    }

    /**
     * Converts List<Long> to long[].
     * @param givenList given list of long values
     * @return returns pattenr long[] format.
     */
    private long[] convertListtoPattern(List<Long> givenList) {
        int size = givenList.size();
        long[] pattern = new long[size];
        for (int i = 0; i < size; i++) {
            pattern[i] = givenList.get(i);
        }
        return pattern;
    }

    /**
     * Converts given morse code back to char array
     * @param givenMorseCode given morse code
     * @return string array of morse codes
     */
    private List<Character> convertGivenStringtoCharArray(String givenMorseCode) {
        char[] charArray = givenMorseCode.toCharArray();
        int sizeList = charArray.length; //Exclude here if needed
        List<Character> charList = new ArrayList<>();
        for (int i = 0; i < sizeList; i++) {
            charList.add(charArray[i]);
        }
        return charList;
    }
}
