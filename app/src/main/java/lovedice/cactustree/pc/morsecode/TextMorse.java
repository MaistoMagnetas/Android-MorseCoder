package lovedice.cactustree.pc.morsecode;

/**
 * Created by PC on 9/16/2018.
 *
 * @author CactusTree
 */

/**
 * Holds converted morse text.
 */
class TextMorse {

    private String convertedText;

    TextMorse(String inputString) {
        MainMorseConverter morseConverter = new MainMorseConverter(inputString);
        this.convertedText = morseConverter.getConvertedMorseText();
    }

    String getConvertedText() {
        return this.convertedText;
    }
}
