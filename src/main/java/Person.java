import java.util.Objects;

/**
 * The type Person.
 */
public class Person {

    private Integer id;

    private String name;

    private Integer age;

    private String gender;

    // in kg
    private Integer weight;

    // in cm
    private Integer height;

    private Person() {
    }

    /**
     * Instantiates a new Person.
     *
     * @param name   the name
     * @param age    the age
     * @param gender the gender
     * @param weight the weight
     * @param height the height
     */
    public Person(String name, Integer age, String gender, Integer weight, Integer height) {
        super();
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    /**
     * Instantiates a new Person.
     *
     * @param id     the id
     * @param name   the name
     * @param age    the age
     * @param gender the gender
     * @param weight the weight
     * @param height the height
     */
    public Person(String id, String name, String age, String gender, String weight, String height) {
        super();
        this.id = Integer.parseInt(id);
        this.name = name;
        this.age = Integer.parseInt(age);
        this.gender = gender;
        this.weight = Integer.parseInt(weight);
        this.height = Integer.parseInt(height);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public Integer getAge() {
        return this.age;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public Integer getWeight() {
        return this.weight;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public Integer getHeight() {
        return this.height;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @throws PersistenceException the persistence exception
     */
    public void setName(String name) throws PersistenceException {
        this.name = name;
        
    }

    /**
     * Sets age.
     *
     * @param age the age
     * @throws PersistenceException the persistence exception
     */
    public void setAge(Integer age) throws PersistenceException {
        this.age = age;
        
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     * @throws PersistenceException the persistence exception
     */
    public void setGender(String gender) throws PersistenceException {
        this.gender = gender;
        
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     * @throws PersistenceException the persistence exception
     */
    public void setWeight(Integer weight) throws PersistenceException {
        this.weight = weight;
        
    }

    /**
     * Sets height.
     *
     * @param height the height
     * @throws PersistenceException the persistence exception
     */
    public void setHeight(Integer height) throws PersistenceException {
        this.height = height;
        
    }


    @Override
    public boolean equals(Object object) {
        Person person = (Person) object;
        return (Objects.equals(person.id, this.id));
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getAge() + "," + getGender() + "," + getWeight() + "," + getHeight();
    }
}
