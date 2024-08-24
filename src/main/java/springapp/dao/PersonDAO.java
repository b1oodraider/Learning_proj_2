package springapp.dao;

import org.springframework.stereotype.Component;
import springapp.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PERSON_ID = 0;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db1";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static{
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String query = "select * from person";
            statement.executeQuery(query);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public Person getPersonById(int id) {
//        return people.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
        return null;
    }

    public void save(Person person) {
//        person.setId(++PERSON_ID);
//        people.add(person);
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO person VALUES (" + 1 + ",'"+ person.getName() +"'," + person.getAge() +",'" +
                    person.getEmail() + "')";

            statement.executeUpdate(SQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person updatedPerson) {
//        Person personToBeUpdated = getPersonById(id);
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
//        personToBeUpdated.setAge(updatedPerson.getAge());
    }

    public void delete(int id) {
//        people.removeIf(p -> p.getId() == id);
    }
}
