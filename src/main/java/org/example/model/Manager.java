package org.example.model;

import org.example.Main;
import org.example.enums.Gender;
import org.example.enums.Role;
import org.example.service.ManagerService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Manager implements ManagerService {
    private Person manager;
    @Override
    public List<Person> hireEmployee(List<Person> applicants, String password) {
        List<Person> cashiers = new ArrayList<>();
        if (password.equals(manager.getPassword())){
            for (Person person:applicants) {
                if (person.getAge() >= 18 && person.getAge() <= 35) {
                    person.setRole(Role.CASHIER);
                    cashiers.add(person);
                    System.out.println("Congratulations, " + person.getName() + " you are hired");
                } else if (person.getAge() < 18) {
                    System.out.println("Sorry, we can't hire you");
                } else {
                    System.out.println("We cannot hire you");
                }
            }
        }else{
            System.out.println("Incorrect password");
        }
        return cashiers;
    }

    @Override
    public void fireEmployee(Person cashier, String password) {
        if(cashier.getRole() == Role.CASHIER){
            if(password.equals(manager.getPassword())){
                    Main.cashiers.remove(cashier);
                    System.out.println(cashier.getName() + " You have been fired");
            }else {
                System.out.println("Incorrect password");
            }
        }else {
            System.out.println(cashier.getName() + " is not a cashier");
        }

    }

    @Override
    public Person delegate(Person person) {
        if(manager==null){
            manager=person;
        }
        return this.manager;
    }

    @Override
    public void assign() {
        for (Cashier cashier: Main.cStand){
            if (Main.cashiers.size()>0){
                cashier.setCashier(Main.cashiers.get(0));
            }
        }
    }
}
