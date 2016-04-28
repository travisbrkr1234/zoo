/**
 * Created by carlos.ochoa on 4/28/2016.
 */
public class AnimalService {
    //create animal in DB with data collected from user input

    public static void createAnimal() {

        Animal animal = new Animal();

        boolean stopLoop = false;
        boolean stopLoop2 = false;

        while (!stopLoop) {
            System.out.println("Please enter an animal name ex... 'Larry'");
            String animalName = GetUserInput.getAnimalName();
            boolean validAnimalName = Validation.validateAnimalName(animalName);
            if (validAnimalName) {
                animal.setName(animalName);
                stopLoop = true;
            }
        }

        while (!stopLoop2) {
            System.out.println("Please enter an animal age ex... 41");
            int animalAge = GetUserInput.getAnimalAge();
            boolean validAnimalAge = Validation.validateAnimalAge(animalAge);
            if (validAnimalAge) {
                animal.setAge(animalAge);
                stopLoop2 = true;
            }
        }
        JDBCAnimalDAO jdbcAnimalDAO = new JDBCAnimalDAO();
        jdbcAnimalDAO.getConnection();
        jdbcAnimalDAO.insert(animal); //
        jdbcAnimalDAO.closeConnection();
    }

    public static void listAnimals() {
        JDBCAnimalDAO jdbcAnimalDAO = new JDBCAnimalDAO();
        jdbcAnimalDAO.getConnection();
        jdbcAnimalDAO.select();
        jdbcAnimalDAO.closeConnection();
    }

}
