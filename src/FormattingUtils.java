/**
 * Created by carlos.ochoa on 5/3/2016.
 */
public class FormattingUtils {
    public static String capitalizeFirstLetter(String wordToFormat) {
        String formattedWord = wordToFormat.substring(0,1).toUpperCase() + wordToFormat.substring(1);
        return formattedWord;
    }
}
