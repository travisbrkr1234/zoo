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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insert(Animal animal) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO zoo.animal (name, age) VALUES (?,?)");
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, String.valueOf(animal.getAge()));
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
                animal.setAge(resultSet.getInt("age"));

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