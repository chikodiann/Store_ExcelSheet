package org.example.model;

import org.example.enums.Gender;
import org.example.enums.Role;

import java.math.BigDecimal;

public class Person {
    private String name;
    private String id;
    private Role role;
    private Gender gender;
    private int age;
    private BigDecimal wallet;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person(String name, String id, Role role, Gender gender, int age, BigDecimal wallet) {
        this.name = name;
        this.id = id;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.wallet = wallet;
    }

    public Person(String name, String id, Role role, Gender gender) {
        this.name = name;
        this.id = id;
        this.role = role;
        this.gender = gender;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", role=" + role +
                ", gender=" + gender +
                ", age=" + age +
                ", wallet=" + wallet +
                '}';
    }
}
