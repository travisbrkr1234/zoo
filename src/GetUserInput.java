import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by carlos.ochoa on 4/27/2016.
 */
// Gets the next string provided by the user //
public class GetUserInput {
    public static String getUserInputString() {

        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        try {
            userInput = scanner.nextLine();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
        return userInput;
    }

    public static int getUserIntInput() {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        int userIntInput = -1;

        while (!stop) {
            try {
                userIntInput = scanner.nextInt();
                stop = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                scanner.nextLine();
//                e.printStackTrace();
            }
        }
        return userIntInput;
    }

    // Gets the next integer provided by the user //
    // The reason this is separate, is due to the exception being thrown if the user does not provide an int
    public static int getAnimalAge() {

        int animalAgeInput = 0;
        boolean stop = false;

        while (!stop) {
            Scanner scanner = new Scanner(System.in);
            try {
                animalAgeInput = scanner.nextInt();
                stop = true;
            } catch (InputMismatchException e) {
                scanner.hasNextInt();
                System.out.println("Please enter a valid animal age");
            }
        }
        return animalAgeInput;
    }

    public static int getAnimalHealthStatus() {

        int animalHealthStatusCode = 0;
        boolean stop = false;

        while (!stop) {
            Scanner scanner = new Scanner(System.in);
            try {
                animalHealthStatusCode = scanner.nextInt();
                stop = true;
            } catch (InputMismatchException e) {
                scanner.hasNextInt();
                System.out.println("Please enter a valid animal health status of 1, 2, 3, or 4");
            }
        }
        return animalHealthStatusCode;
    }
}