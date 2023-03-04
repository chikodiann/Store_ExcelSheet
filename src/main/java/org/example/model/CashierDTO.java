package org.example.model;

public class CashierDTO {
    private PersonDTO person;
    private String product;

    public CashierDTO(PersonDTO person, String product) {
        this.person = person;
        this.product = product;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CashierDTO{" +
                "person=" + person +
                ", product='" + product + '\'' +
                '}';
    }
}
