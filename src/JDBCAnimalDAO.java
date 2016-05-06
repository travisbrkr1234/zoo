import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by carlos.ochoa on 4/28/2016.
 */
public class JDBCAnimalDAO {

    final String MY_SQL_INFO = "jdbc:mysql://localhost/zoo?user=#####&password=#####";
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

    public List<Animal> select() {
        List<Animal> animalList = new LinkedList<Animal>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM zoo.animal");

            Animal animal = null;
            while (resultSet.next()) {
                animal = new Animal();
                animal.setAnimalNumber(resultSet.getInt("id"));
                animal.setName(resultSet.getString("name"));
                animal.setName(resultSet.getString("keeper_name"));
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
        System.out.println(animalList);
        return animalList;
    }

    public void delete(int animalId) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM zoo.animal WHERE id = ?");
            preparedStatement.setInt(1, animalId);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkForAnimals() {
        JDBCAnimalDAO jdbcAnimalDAO = new JDBCAnimalDAO();
        jdbcAnimalDAO.getConnection();
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

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            //setup logging?
        }
    }
}