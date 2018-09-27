package lovedice.cactustree.pc.morsecode;

/**
 * Created by PC on 9/16/2018.
 *
 * @author CactusTree
 */

/**
 * Responsible for converting alphabetic text to morse code.
 */
class MainMorseConverter {

    private String textToConvert;
    private String convertedMorseText;

    MainMorseConverter(String textToConvert) {
        this.textToConvert = textToConvert;
        String convertedString = morseCodeConverter(getCharArrayFromString(getTextToConvert()));
        setConvertedMorseText(convertedString);
    }

    private String getTextToConvert() {
        return textToConvert;
    }

    /**
     * Gets converted morse text
     *
     * @return returns converted morse cod text
     */
    String getConvertedMorseText() {
        return convertedMorseText;
    }

    private void setConvertedMorseText(String convertedMorseText) {
        this.convertedMorseText = convertedMorseText;
    }

    /**
     * Text to morse code converter.
     *
     * @return returns converted morse code.
     */
    private String morseCodeConverter(char[] charArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            if (i != charArray.length - 1) {
                sb.append(whichLetter(charArray[i]));
            } else {
                String lastLetter = whichLetter(charArray[i]);
                sb.append(lastLetter.substring(0, lastLetter.length()-1));
            }
        }
        return sb.toString();
    }

    /**
     * Converts string to char[] arrray.
     *
     * @param text simple given stirng.
     * @return returning char array.
     */
    private char[] getCharArrayFromString(String text) {
        char[] charArray = text.toCharArray();
        return charArray;
    }

    /**
     * Methods which converts given char to morse code letter.
     */
    private String whichLetter(char singleChar) {
        String returningMorseLetter = "";
        switch (singleChar) {
            case 'a':
                returningMorseLetter = "•- ";
                break;
            case 'b':
                returningMorseLetter = "-••• ";
                break;
            case 'c':
                returningMorseLetter = "-•-• ";
                break;
            case 'd':
                returningMorseLetter = "-•• ";
                break;
            case 'e':
                returningMorseLetter = "• ";
                break;
            case 'f':
                returningMorseLetter = "••-• ";
                break;
            case 'g':
                returningMorseLetter = "--• ";
                break;
            case 'h':
                returningMorseLetter = "•••• ";
                break;
            case 'i':
                returningMorseLetter = "•• ";
                break;
            case 'j':
                returningMorseLetter = "•--- ";
                break;
            case 'k':
                returningMorseLetter = "-•- ";
                break;
            case 'l':
                returningMorseLetter = "•-•• ";
                break;
            case 'm':
                returningMorseLetter = "-- ";
                break;
            case 'n':
                returningMorseLetter = "-• ";
                break;
            case 'o':
                returningMorseLetter = "--- ";
                break;
            case 'p':
                returningMorseLetter = "•--• ";
                break;
            case 'q':
                returningMorseLetter = "--•- ";
                break;
            case 'r':
                returningMorseLetter = "•-• ";
                break;
            case 's':
                returningMorseLetter = "••• ";
                break;
            case 't':
                returningMorseLetter = "- ";
                break;
            case 'u':
                returningMorseLetter = "••- ";
                break;
            case 'v':
                returningMorseLetter = "•••- ";
                break;
            case 'w':
                returningMorseLetter = "•-- ";
                break;
            case 'x':
                returningMorseLetter = "-••- ";
                break;
            case 'y':
                returningMorseLetter = "-•-- ";
                break;
            case 'z':
                returningMorseLetter = "--•• ";
                break;
            case ' ':
                returningMorseLetter = "/";
                break;
            case '1':
                returningMorseLetter = "•---- ";
                break;
            case '2':
                returningMorseLetter = "••--- ";
                break;
            case '3':
                returningMorseLetter = "•••-- ";
                break;
            case '4':
                returningMorseLetter = "••••- ";
                break;
            case '5':
                returningMorseLetter = "••••• ";
                break;
            case '6':
                returningMorseLetter = "-•••• ";
                break;
            case '7':
                returningMorseLetter = "--••• ";
                break;
            case '8':
                returningMorseLetter = "---•• ";
                break;
            case '9':
                returningMorseLetter = "----• ";
                break;
            case '0':
                returningMorseLetter = "----- ";
                break;
        }
        return returningMorseLetter;
    }
}
