package lovedice.cactustree.pc.morsecode;

import java.util.ArrayList;
import java.util.List;

/**
 * CLass responsible for converting between morse to alphabetic letters.
 */
public class MorseToTextConverter {

    private String morseText;
    private String convertedText;

    public MorseToTextConverter(String morseText)
    {
        this.morseText = morseText;
        this.convertedText = convertTextFromMorse();
    }

    private String getMorseText()
    {
        return morseText;
    }

    public String getConvertedString()
    {
        return convertedText;
    }

    /**
     * Converts text from morse to text.
     * @return converted list of letters.
     */
    private String convertTextFromMorse()
    {
        String[] trimmedStringsArray = getTrimmedStringsArray(getMorseText());
        StringBuilder sb = new StringBuilder();
        int stringArray = trimmedStringsArray.length;
        for(int i = 0; i < stringArray; i++)
        {
            sb.append(trimmedStringsArray[i]);
        }
        return sb.toString();
    }

    /**
     * Gets String separated by spaces to simple array.
     * @return string list
     */
    private String[] getTrimmedStringsArray(String givenString)
    {
        return givenString.trim().split("\\s+");
    }

    /**
     * Methods which converts given char to morse code letter.
     */
    private String whichLetter(String ch) {
        String returningMorseLetter = "";
        switch (ch) {
            case "•- ":
                returningMorseLetter = "a";
                break;
            case "-••• ":
                returningMorseLetter = "b";
                break;
            case "-•-• ":
                returningMorseLetter = "c";
                break;
            case "-•• ":
                returningMorseLetter = "d";
                break;
            case "• ":
                returningMorseLetter = "e";
                break;
            case "••-• ":
                returningMorseLetter = "f";
                break;
            case "--• ":
                returningMorseLetter = "g";
                break;
            case "•••• ":
                returningMorseLetter = "h";
                break;
            case "•• ":
                returningMorseLetter = "i";
                break;
            case "•--- ":
                returningMorseLetter = "j";
                break;
            case "-•- ":
                returningMorseLetter = "k";
                break;
            case "•-•• ":
                returningMorseLetter = "l";
                break;
            case "-- ":
                returningMorseLetter = "m";
                break;
            case "-• ":
                returningMorseLetter = "n";
                break;
            case "--- ":
                returningMorseLetter = "o";
                break;
            case "•--• ":
                returningMorseLetter = "p";
                break;
            case "--•- ":
                returningMorseLetter = "q";
                break;
            case "•-• ":
                returningMorseLetter = "r";
                break;
            case "••• ":
                returningMorseLetter = "s";
                break;
            case "- ":
                returningMorseLetter = "t";
                break;
            case "••- ":
                returningMorseLetter = "u";
                break;
            case "•••- ":
                returningMorseLetter = "v";
                break;
            case "•-- ":
                returningMorseLetter = "w";
                break;
            case "-••- ":
                returningMorseLetter = "x";
                break;
            case "-•-- ":
                returningMorseLetter = "y";
                break;
            case "--•• ":
                returningMorseLetter = "z";
                break;
            case "/ ":
                returningMorseLetter = " ";
                break;
            case "•---- ":
                returningMorseLetter = "1";
                break;
            case "••--- ":
                returningMorseLetter = "2";
                break;
            case "•••-- ":
                returningMorseLetter = "3";
                break;
            case "••••- ":
                returningMorseLetter = "4";
                break;
            case "••••• ":
                returningMorseLetter = "5";
                break;
            case "-•••• ":
                returningMorseLetter = "6";
                break;
            case "--••• ":
                returningMorseLetter = "7";
                break;
            case "---•• ":
                returningMorseLetter = "8";
                break;
            case "----• ":
                returningMorseLetter = "9";
                break;
            case "----- ":
                returningMorseLetter = "0";
                break;
        }
        return returningMorseLetter;
    }





}
