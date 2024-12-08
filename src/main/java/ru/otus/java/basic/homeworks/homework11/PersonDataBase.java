package ru.otus.java.basic.homeworks.homework11;

import java.util.HashMap;
import java.util.Map;

public class PersonDataBase {
    Map<Long, Person> persons = new HashMap<>();

    Person findById(Long id) {
        return persons.get(id);
    }

    void add(Person person) {
        persons.put(person.getId(), person);
    }

    public boolean isManager(Person person) {
        switch (person.getPosition()) {
            case MANAGER:
            case DIRECTOR:
            case BRANCH_DIRECTOR:
            case SENIOR_MANAGER:
                return true;
            default:
                return false;
        }
    }

    public boolean isEmployee(Long id) {
        Person person = persons.get(id);
        return !isManager(person);
    }

}
