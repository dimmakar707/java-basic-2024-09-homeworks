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
        Map<Position, Integer> positions = new HashMap<>();
        positions.put(Position.MANAGER, 1);
        positions.put(Position.DIRECTOR, 2);
        positions.put(Position.BRANCH_DIRECTOR, 3);
        positions.put(Position.SENIOR_MANAGER, 4);
        if(positions.get(person.getPosition()) != null) {
            return true;
        }
        return false;
    }

    public boolean isEmployee(Long id) {
        Person person = persons.get(id);
        return !isManager(person);
    }

}
