import java.util.List;

/**DAO's
 Read
 Insert
 Delete
 Update
 */
public interface AnimalDAO {

    List<Animal> getAllAnimals();
    void insert(Animal animal);
    void deleteAnimal(int animalId);
    void createDatabase();
    boolean checkForAnimals();
    boolean checkForAnimalTable();

//    Animal getAnimalsBy(int animalId);
}
