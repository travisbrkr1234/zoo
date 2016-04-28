import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by carlos.ochoa on 4/27/2016.
 */
public class Validation {

    public static boolean validateAnimalName(String animalName) {
        //run validation on customer input
        //return true if name is valid
        boolean animalNameIsValid = false;
        final String regexNamePattern = "\\b[a-zA-Z]+\\b";
        Pattern namePattern = Pattern.compile(regexNamePattern);

            Matcher matcher = namePattern.matcher(animalName);

            if (matcher.find()) {
                animalNameIsValid = true;
            }
        return animalNameIsValid;
    }

    public static boolean validateAnimalAge(int animalAge) {
        //run validation on customer input
        //return true if age is valid and is or is-less than 4 numbers
        boolean animalAgeIsValid = false;
        String animalAgeLength = String.valueOf(animalAge);

        if (animalAgeLength.length() <= 4) {
            animalAgeIsValid = true;
        }
        return animalAgeIsValid;
    }

}
