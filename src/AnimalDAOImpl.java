import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by carlos.ochoa on 4/28/2016.
 */
public class AnimalDAOImpl implements AnimalDAO {

    final String MY_SQL_INFO = "jdbc:mysql://localhost/zoo?user=root&password=password";
    Connection connection = null;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (connection == null)
                connection = DriverManager.getConnection(MY_SQL_INFO);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
        return connection;
    }

    @Override
    public List<Animal> getAllAnimals() {
        List<Animal> animalList = new LinkedList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM zoo.animal");

            Animal animal = null;
            while (resultSet.next()) {
                animal = new Animal();
                animal.setAnimalNumber(resultSet.getInt("id"));
                animal.setName(resultSet.getString("name"));
                animal.setKeeperName(resultSet.getString("keeper_name"));
                animal.setAge(resultSet.getInt("age"));
                animal.setType(resultSet.getString("type"));
                animal.setGender(resultSet.getString("gender"));
                animal.setHealth(AnimalHealthStatus.getAnimalHealthStatusByStatus(resultSet.getString("health"))); //TODO get health from DB in a better way? reverse enum lookup?
                animal.setEnclosure(resultSet.getString("enclosure"));
                animal.setOnLoan(resultSet.getBoolean("on_loan"));
                animal.setLoanLocation(resultSet.getString("loan_location"));

                animalList.add(animal);
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animalList;
    }

    public void insert(Animal animal) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO zoo.animal (name, keeper_name, age, type, gender, health, enclosure, on_loan, loan_location) VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, animal.getKeeperName());
            preparedStatement.setString(3, String.valueOf(animal.getAge()));
            preparedStatement.setString(4, animal.getType());
            preparedStatement.setString(5, animal.getGender());
            preparedStatement.setString(6, String.valueOf(animal.getHealth()));
            preparedStatement.setString(7, animal.getEnclosure());
            preparedStatement.setBoolean(8, animal.isOnLoan());
            preparedStatement.setString(9, animal.getLoanLocation());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteAnimal(int animalId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM zoo.animal WHERE id = ?");
            preparedStatement.setInt(1, animalId);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public Animal getAnimalsBy(int animalId) {
//        //get animal by name
//        //get animal by type
//        return null;
//    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            //setup logging?
        }
    }

    //Additional DB methods, not sure if it should exist here...
    @Override
    public void createDatabase() {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE DATABASE zoo");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkForAnimals() {
        boolean animalsExist = false;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM zoo.animal");
            if (!resultSet.isBeforeFirst()) {
                animalsExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return animalsExist;
    }

    @Override
    public boolean checkForAnimalTable() {
        boolean tableExists = false;
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null,"animal", new String[] {"TABLE"});
            if (resultSet.next()) {
                tableExists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableExists;
    }


}