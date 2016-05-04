/**
 * Created by carlos.ochoa on 4/28/2016.
 */
public class AnimalService {
    //create animal in DB with data collected from user input

    public static void createAnimal() {

        Animal animal = new Animal();

        //TODO condense these loops where possible
        boolean stopNameLoop = false;
        boolean stopKeeperNameLoop = false;
        boolean stopAgeLoop = false;
        boolean stopTypeLoop= false;
        boolean stopGenderLoop = false;
        boolean stopHealthStatusLoop = false;
        boolean stopEnclosureLoop = false;
        boolean stopOnLoanLoop = false;
        boolean stopLoanLocationLoop = false;

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
            boolean validAnimalType = Validation.validateAnimalType(animalType);
            if (validAnimalType) {
                animal.setType(animalType);
                stopTypeLoop = true;
            }
        }

        while (!stopGenderLoop) {
            System.out.println("Please enter the animals gender, m or f");
            String animalGender = GetUserInput.getUserInputString();
            boolean validAnimalGender = Validation.validateAnimalGender(animalGender);
            //TODO validation in validation class, not here
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

        while (!stopHealthStatusLoop) {
            Menu.printHealthStatusMenu();
            int animalHealthStatusCode = GetUserInput.getAnimalHealthStatus();

            //TODO clean this up
            //Since enum has its own "sort of" validation, I skipped implementing an additional validator
            if (AnimalHealthStatus.animalHealthStatusExists(animalHealthStatusCode)) {
                animal.setHealth(AnimalHealthStatus.getAnimalHealthStatusByStatusCode(animalHealthStatusCode));
                stopHealthStatusLoop = true;
            }
        }

        while (!stopEnclosureLoop) {
            System.out.println("Please enter the type of enclosure the animal is in: \n examples:\n" +
                    "pen \n cage \n window \n other");

            String enclosureUserInput = GetUserInput.getUserInputString();
            if (enclosureUserInput.equalsIgnoreCase("pen") || enclosureUserInput.equalsIgnoreCase("cage") || enclosureUserInput.equalsIgnoreCase("window") || enclosureUserInput.equalsIgnoreCase("other") ) {
                animal.setEnclosure(enclosureUserInput.toLowerCase());
                stopEnclosureLoop = true;
            }
        }

        while (!stopOnLoanLoop) {
            System.out.println("Is the animal on loan to another zoo? Please enter 'yes' or 'no'");
            String onLoanInput = GetUserInput.getUserInputString();
            boolean onLoanInputValid = Validation.validateOnLoanInput(onLoanInput);
            //TODO change the gender validation logic as seen here?
            if (onLoanInputValid) {
                if (onLoanInput.equalsIgnoreCase("yes")) {
                    animal.setOnLoan(true);
                }
                if (onLoanInput.equalsIgnoreCase("no")) {
                    animal.setOnLoan(false);
                }
                stopOnLoanLoop = true;
            }
        }

        if (animal.isOnLoan()) {
            while (!stopLoanLocationLoop) {
                //TODO What type of validation needed here? if any?
                System.out.println("Where is the animal on loan to?");
                String loanLocation = GetUserInput.getUserInputString();
                animal.setLoanLocation(loanLocation);
                stopLoanLocationLoop = true;
            }
        }

        System.out.println("Saving animal to database");
        JDBCAnimalDAO jdbcAnimalDAO = new JDBCAnimalDAO();
        jdbcAnimalDAO.getConnection();
        jdbcAnimalDAO.insert(animal); //
        jdbcAnimalDAO.closeConnection();
    }

    public static void printAnimalsToConsole() {
        JDBCAnimalDAO jdbcAnimalDAO = new JDBCAnimalDAO();
        jdbcAnimalDAO.getConnection();
        boolean animalsEmpty = jdbcAnimalDAO.checkForAnimals();
        if (!animalsEmpty) {
            jdbcAnimalDAO.select();
        } else  {
            System.out.println("There are no animals, please add one first");
        }
        jdbcAnimalDAO.closeConnection();
    }

    public static void removeAnimal() {
        JDBCAnimalDAO jdbcAnimalDAO = new JDBCAnimalDAO();
        jdbcAnimalDAO.getConnection();
        boolean animalsExist = jdbcAnimalDAO.checkForAnimals();

        if (!animalsExist) {
            System.out.println("Please enter the id of an animal that you would like to remove");
            int animalId = GetUserInput.getUserIntInput();
            jdbcAnimalDAO.delete(animalId);
            jdbcAnimalDAO.closeConnection();
            System.out.println("Animal id: " + animalId + " removed");
        } else  {
            System.out.println("There are no animals to remove");
        }
    }
}
