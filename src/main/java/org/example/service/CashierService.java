package org.example.service;

import org.example.model.CashierDTO;
import org.example.model.PersonDTO;
import org.example.model.Product;

import java.util.HashMap;

public interface CashierService {
 boolean makeSales(CashierDTO cashierDTO, HashMap<String, Product> inventory);


 boolean issueReceipt(String record, PersonDTO personDTO);

}
