package springapp.dao;

import org.springframework.stereotype.Component;
import springapp.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PERSON_ID = 0;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PERSON_ID, "Kamal", 10, "kama@mail.ru"));
        people.add(new Person(++PERSON_ID, "kamiL", 22, "kamil2890@mail.ru"));
        people.add(new Person(++PERSON_ID, "Kira", 30, "kiraYoshikage@mail.ru"));
        people.add(new Person(++PERSON_ID, "Kamal2", 10, "kamal22@mail.ru"));
    }

    public List<Person> index() {
        return people;
    }

    public Person getPersonById(int id) {
        return people.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PERSON_ID);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = getPersonById(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
        personToBeUpdated.setAge(updatedPerson.getAge());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
