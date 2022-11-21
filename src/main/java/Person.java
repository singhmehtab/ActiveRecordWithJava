import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Person.
 */
public class Person implements ActiveRecordInterface {


    private final static String CSV_LOCATION = "Person.csv ";
    private final static String[] columns = new String[]
            {"id", "name", "age", "gender", "weight", "height"};

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
     * @param name   the name
     * @param age    the age
     * @param gender the gender
     * @param weight the weight
     * @param height the height
     */
    public Person(String name, String age, String gender, String weight, String height) {
        super();
        this.name = name;
        this.age = Integer.parseInt(age);
        this.gender = gender;
        this.weight = Integer.parseInt(weight);
        this.height = Integer.parseInt(height);
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
        updateRecord();
    }

    /**
     * Sets age.
     *
     * @param age the age
     * @throws PersistenceException the persistence exception
     */
    public void setAge(Integer age) throws PersistenceException {
        this.age = age;
        updateRecord();
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     * @throws PersistenceException the persistence exception
     */
    public void setGender(String gender) throws PersistenceException {
        this.gender = gender;
        updateRecord();
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     * @throws PersistenceException the persistence exception
     */
    public void setWeight(Integer weight) throws PersistenceException {
        this.weight = weight;
        updateRecord();
    }

    /**
     * Sets height.
     *
     * @param height the height
     * @throws PersistenceException the persistence exception
     */
    public void setHeight(Integer height) throws PersistenceException {
        this.height = height;
        updateRecord();
    }


    private void updatePerson(Person person) {
        this.age = person.getAge();
        this.name = person.getName();
        this.gender = person.getGender();
        this.weight = person.getWeight();
        this.height = person.getHeight();
    }

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getAge() + "," + getGender() + "," + getWeight() + "," + getHeight();
    }

    /**
     * Gets headers.
     *
     * @return the headers
     */
    public String[] getHeaders() {
        return new String[]{"Id","Name","Age","Gender","Weight","Height"};
    }

    @Override
    public boolean equals(Object object) {
        Person person = (Person) object;
        return (Objects.equals(person.id, this.id));
    }

    @Override
    public void save() {

        File file = getFile(CSV_LOCATION);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
            CSVWriter csvWriter = new CSVWriter((writer));
            List<Person> personList = fetchRecords();
            if (personList.isEmpty()) {
                csvWriter.writeNext(getHeaders());
                this.id = 1;
            } else this.id = personList.size() + 1;
            csvWriter.writeNext(new String[]{this.getId().toString(), this.getName(), this.getAge().toString(), this.getGender(), this.getWeight().toString(), this.getWeight().toString()});
        } catch (IOException | PersistenceException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Fetch records list.
     *
     * @return the list
     * @throws PersistenceException the persistence exception
     */
    public static List<Person> fetchRecords() throws PersistenceException {
        File file = getFile(CSV_LOCATION);
        List<Person> personList = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                String[] columns = new String[row.length];
                int count = 0;
                for(String data : row){
                    columns[count] = data;
                    count++;
                }
                personList.add(new Person(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenceException(e.getMessage());
        }
        return personList;
    }

    private void writeUpdatedRecords(List<Person> personList) {

        File file = getFile(CSV_LOCATION);
        try {
            FileWriter writer = new FileWriter(file);
            CSVWriter csvWriter = new CSVWriter((writer));
            csvWriter.writeNext(getHeaders());
            for (Person p : personList) {
                csvWriter.writeNext(new String[]{p.getId().toString(), p.getName(), p.getAge().toString(), p.getGender(), p.getWeight().toString(), p.getWeight().toString()});
            }
            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateRecord() throws PersistenceException {
        List<Person> personList = fetchRecords();
        for (Person p : personList) {
            if (this.equals(p)) {
                p.updatePerson(this);
            }
        }
        writeUpdatedRecords(personList);

    }

    private static File getFile(String location){
        File file = new File(location);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
