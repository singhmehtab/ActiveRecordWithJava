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
    public static void main(String[] args) throws PersistenceException {
        Person person1 = new Person("Mehtab1", 26, "Male", 75, 180);
        Person person2 = new Person("Mehtab2", 26, "Male", 75, 180);
        Person person3 = new Person("Mehtab3", 26, "Male", 75, 180);
        Person person4 = new Person("Mehtab4", 26, "Male", 75, 180);

        //Saving person obejcts
        person1.save();
        person2.save();
        person4.save();
        person3.save();

        //Fetching personObject based on Id
        Person newPerson =  Person.fetchById(1);
        System.out.println(newPerson);

        // updating age of person 3
        person3.setAge(19);

        try {
            //Fetching all person records
            List<Person> personList = Person.fetchRecords();
            for (Person p : personList) {
                //Updating object
                if (p.getName().equals("Mehtab2")) p.setName("UpdatedMehtabName");
                System.out.println(p);
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

}
