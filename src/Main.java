/**
 * Created by carlos.ochoa on 4/27/2016.
 */
public class Main {
    public static void main(String[] args) {
        while(true) {
            Menu.printMenu(); //Print menu to console for user
            Menu.runUserChoice(); //Get users choice and invoke corresponding function
        }
    }
}