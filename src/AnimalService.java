/**
 * Created by carlos.ochoa on 4/28/2016.
 */
public class AnimalService {
    //create animal in DB with data collected from user input

    public static void createAnimal() {

        Animal animal = new Animal();

        boolean stopNameLoop = false;
        boolean stopAgeLoop = false;
        boolean stopTypeLoop= false;
        boolean stopGenderLoop = false;

        while (!stopNameLoop) {
            System.out.println("Please enter an animal name ex... 'Larry'");
            String animalName = GetUserInput.getUserInputString();
            boolean validAnimalName = Validation.validateAnimalName(animalName);
            if (validAnimalName) {
                animal.setName(FormattingUtils.capitalizeFirstLetter(animalName));
                stopNameLoop = true;
            }
        }

        while (!stopAgeLoop) {
            System.out.println("Please enter an animal age ex... 41");
            int animalAge = GetUserInput.getAnimalAge();
            boolean validAnimalAge = Validation.validateAnimalAge(animalAge);
            if (validAnimalAge) {
                animal.setAge(animalAge);
                stopAgeLoop = true;
            }
        }

        while (!stopTypeLoop) {
            System.out.println("Please enter an animal type ex. example-type");
            String animalType = GetUserInput.getUserInputString();
            boolean validAnimalAge = Validation.validateAnimalType(animalType);
            if (validAnimalAge) {
                animal.setType(animalType);
                stopTypeLoop = true;
            }
        }

        while (!stopGenderLoop) {
            System.out.println("Please enter the animals gender, m or f");
            String animalGender = GetUserInput.getUserInputString();
            boolean validAnimalGender = Validation.validateAnimalGender(animalGender);
            if (validAnimalGender) {
                if (animalGender.equals("m") || animalGender.equals("M")) {
                    animal.setGender("Male");
                }
                if (animalGender.equals("f") || animalGender.equals("F")) {
                    animal.setGender("Female");
                }
                stopGenderLoop = true;
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
