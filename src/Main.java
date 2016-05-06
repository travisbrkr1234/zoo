/**
 * Created by carlos.ochoa on 4/27/2016.
 */
public class Main {
    public static void main(String[] args) {
        AnimalDAOImpl animalDAOImpl = new AnimalDAOImpl();
        animalDAOImpl.getConnection();
        if (!animalDAOImpl.checkForAnimalTable()) {
            animalDAOImpl.createDatabase();
        }
        animalDAOImpl.closeConnection();

        while(true) { //TODO run program until system exit is called. Probably find a better way to do this?

            Menu.printMenu(); //Print menu to console for user
            Menu.runUserChoice(); //Get users choice and invoke corresponding function
        }
    }
}