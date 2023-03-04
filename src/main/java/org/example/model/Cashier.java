package org.example.model;

import org.example.enums.Role;
import org.example.enums.StockStat;
import org.example.service.CashierService;

import java.math.BigDecimal;
import java.util.HashMap;

public class Cashier implements CashierService {
    Person cashier;

    private String id;
    static int receiptNumber = 1;


    public Cashier(Person cashier, String id) {
        this.cashier = cashier;
        this.id = id;
    }

    public Person getCashier() {
        return cashier;
    }

    public void setCashier(Person cashier) {
        if (cashier.getRole() == Role.CASHIER){
            this.cashier = cashier;
        }
    }

    public Cashier(String id) {
        this.id = id;
    }

    public Cashier() {
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean makeSales(CashierDTO cashierDTO, HashMap<String, Product> inventory) {
        if (cashier != null){
            BigDecimal total = new BigDecimal("0.0");
            String output;
            String key = cashierDTO.getProduct();
            PersonDTO salesPerson = cashierDTO.getPerson();

            if (inventory.containsKey(key)){
                Product product = inventory.get(key);
                if (StockStat.IN_STOCK.equals(product.getStockStat())) {
                    if (salesPerson.getQuantity() <= product.getQuantity()){
                        total = total.add(product.getAmount().multiply(new BigDecimal(product.getQuantity())));
                    } else {
                        total = total.add(product.getAmount().multiply(new BigDecimal(product.getQuantity())));
                        salesPerson.setQuantity(product.getQuantity());
                    }
                }
            }
            if (!total.equals(new BigDecimal("0.0"))){
                if (salesPerson.getWallet().compareTo(total) > 0) {
                    output = String.format("%5d %-20s \t \t %10.2f", salesPerson.getQuantity(),inventory.get(key).getProductName(),total);
                    salesPerson.setWallet(salesPerson.getWallet().subtract(total));
                    inventory.get(key).setQuantity(inventory.get(key).getQuantity() - salesPerson.getQuantity());
                    if (inventory.get(key).getQuantity() > 0){
                        inventory.get(key).setStockStat(StockStat.IN_STOCK);
                    }else {
                        inventory.get(key).setStockStat(StockStat.OUT_OF_STOCK);
                    }
                    issueReceipt(output, salesPerson);
                }else {
                    System.out.println("Insufficient funds");
                }
            }
        }
        return false;
    }

    @Override
    public boolean issueReceipt(String record, PersonDTO personDTO) {
        System.out.println("\t \t Customer receipt " + receiptNumber + "\n");
        System.out.printf("%5s %-20s \t \t %10s%n", "QTY", "ITEM", "TOTAL");
        System.out.println(record+"\n" + "**************");
        System.out.println("Thank you for shopping with us " + personDTO.getName());
        System.out.println();
        receiptNumber++;
        return true;
    }
}
