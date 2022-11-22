


import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

privileged public aspect PersonPersistance {

    private final static String CSV_LOCATION = "Person.csv";

    public static List<Person> Person.fetchRecords() throws PersistenceException {
        return PersonPersistance.fetchRecords();
    }

    public static Person Person.fetchById(Integer id) {
        return PersonPersistance.fetchRecords().stream().filter(p -> p.getId().equals(id)).findAny().get();
    }


    public void Person.save() {
        if(this.getId() != null) {
            updateRecord(this);
        }
        File file = getFile(CSV_LOCATION);
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
            CSVWriter csvWriter = new CSVWriter((writer));
            List<Person> personList = fetchRecords();
            if (personList.isEmpty()) {
                csvWriter.writeNext(getHeaders());
                this.id = 1;
            } else this.id = personList.stream().mapToInt(Person::getId).max().getAsInt() + 1;
            csvWriter.writeNext(new String[]{this.getId().toString(),
                    this.getName(), this.getAge().toString(), this.getGender(),
                    this.getWeight().toString(), this.getWeight().toString()});
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException(e.getMessage());
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


    // Now that the main method is done...
    public void Person.update() {
        updateRecord(this);
    }


    // Now that the main method is done...
    after(Person p): execution(* Person.set*(..)) && this(p) {
        p.update();
    }



    /**
     * Gets headers.
     *
     * @return the headers
     */
    private static String[] getHeaders() {
        return new String[]{"Id","Name","Age","Gender","Weight","Height"};
    }
//
//    /**
//     * Fetch records list.
//     *
//     * @return the list
//     * @throws PersistenceException the persistence exception
//     */
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
//
    private static void writeUpdatedRecords(List<Person> personList) {

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

    private static void updateRecord(Person toUpdate) {
        List<Person> personList = fetchRecords();
        for (int i = 0 ;i < personList.size(); i++) {
            if (toUpdate.equals(personList.get(i))) {
                personList.set(i, toUpdate);
            }
        }
        writeUpdatedRecords(personList);

    }
//
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
