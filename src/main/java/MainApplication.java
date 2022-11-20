import java.util.List;

/**
 * The type Main application.
 */
public class MainApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Person person1 = new Person("Mehtab1", 26, "Male", 75, 180);
        Person person2 = new Person("Mehtab2", 26, "Male", 75, 180);
        Person person3 = new Person("Mehtab3", 26, "Male", 75, 180);
        Person person4 = new Person("Mehtab4", 26, "Male", 75, 180);
        person1.save();
        person2.save();
        person4.save();
        person3.save();
        try {
            List<Person> personList = Person.fetchRecords();
            for (Person p : personList) {
                if (p.getName().equals("Mehtab2") && p.getId() == 2) p.setName("UpdatedMehtabName");
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

}
