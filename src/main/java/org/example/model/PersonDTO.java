package org.example.model;

import java.math.BigDecimal;

public class PersonDTO implements  Comparable<PersonDTO> {
    private Person person;

    private int quantity;

    public PersonDTO(Person person, int quantity) {
        this.person = person;
        this.quantity = quantity;
    }
    public BigDecimal getWallet(){
        return person.getWallet();
    }
    public void setWallet(BigDecimal wallet){
        this.person.setWallet(wallet);
    }
    public String getName(){
        return person.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "person=" + person +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public int compareTo(PersonDTO o) {
        if (this.quantity > o.getQuantity()) return 1;
        else if (this.quantity < o.getQuantity()) return -1;
        else return 0;
    }
}
