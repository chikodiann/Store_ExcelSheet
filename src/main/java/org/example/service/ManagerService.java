package org.example.service;

import org.example.model.Person;

import java.util.List;

public interface ManagerService {
    List<Person> hireEmployee(List<Person> applicants, String password);

    void fireEmployee(Person cashier, String password);

    Person delegate(Person person);

    void assign();
}
