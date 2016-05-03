import java.util.Scanner;

/**
 * Created by carlos.ochoa on 4/27/2016.
 */
public class Menu {

    public static void printMenu() {

        // Print a menu of choices to the user
        final StringBuilder zooMenu = new StringBuilder();

        zooMenu.append("-------------------------------------------------\n")
                .append("********000000*******0000*****0000***************\n")
                .append("***********//*******0****0***0****0**************\n")
                .append("**********000000*****0000*****0000***************\n")
                .append("-------------------------------------------------\n")
                .append("-------------Please enter a choice---------------\n")
                .append("--1. Add animal----------------------------------\n")
                .append("--2. List animals--------------------------------\n")
                .append("--3. Remove animal-------------------------------\n")
                .append("--4. Find animal---------------------------------\n")
                .append("----Type e, exit, q, or quit to leave program----\n");
        System.out.println(zooMenu);
    }

    // Get the choice from the user and invoke the corresponding method
    public static void runUserChoice() {
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();

        switch (userChoice) {
            case "1":
                AnimalService.createAnimal();
                break;

            case "2":
                AnimalService.listAnimals();
                break;

            case "3":
                break;

            case "4":
                break;

            case "9":
                break;

            case "e":
            case "exit":
            case "q":
            case "quit":
                System.exit(0);
                break;

            default:
                System.out.println("Please enter a valid option");
        }
    }

}