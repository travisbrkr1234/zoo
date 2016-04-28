import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by carlos.ochoa on 4/27/2016.
 */
public class GetUserInput {
    //get user input for animal name
    public static String getAnimalName() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    public static int getAnimalAge() {
        //get user input for animal age
        int animalAgeInput = 0;
        boolean stop = false;

        while (!stop) {
            Scanner scanner = new Scanner(System.in);
            try {
                animalAgeInput = scanner.nextInt();
                stop = true;
            } catch (InputMismatchException e) {
                scanner.hasNextInt();
            }
        }
        return animalAgeInput;
    }

}