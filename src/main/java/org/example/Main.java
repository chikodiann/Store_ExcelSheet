package org.example;

import org.example.enums.Gender;
import org.example.enums.Role;
import org.example.model.*;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static String path = "src/main/resources/sampledatafoodsales.csv";
    private static StoreReader store;
    private static Map<String, LinkedList<PersonDTO>> theQueue = new LinkedHashMap<>();
    private static Manager manager;
    public static Cashier[] cStand;

    public static List<Person> cashiers = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        //read data from the Excel sheet
        StoreReader storeReader = new StoreReader();
        System.out.println(storeReader.readFile(path));
        System.out.println("*".repeat(160));
        System.out.println();


        manager = new Manager();
        Person manager1 = new Person("Jaden", "001", Role.MANAGER, Gender.MALE, 40,new BigDecimal(1000000));
        manager1.setPassword("1111");
        //check if a manager without password can perform managerial roles
        Person manager2 = new Person("Mike", "10",Role.MANAGER,Gender.MALE, 21,new BigDecimal(2000));
        manager2.setPassword("0223");
        manager.delegate(manager1);
        store = new StoreReader();
        store.openStore(path);

        Person applicant1 = new Person("Philip", "011", Role.CASHIER,Gender.MALE,20,new BigDecimal(20));
        Person applicant2 = new Person("Rose", "012", Role.CASHIER,Gender.FEMALE,16,new BigDecimal(130));
        Person applicant3 = new Person("Caroline", "013", Role.CASHIER,Gender.FEMALE,25,new BigDecimal(2000));
        Person applicant4 = new Person("Henry", "014", Role.CASHIER,Gender.MALE,52,new BigDecimal(100));
        Person applicant5 = new Person("Angela", "015", Role.CASHIER,Gender.FEMALE,26,new BigDecimal(600));
        Person applicant6 = new Person("Kenny", "016", Role.CASHIER,Gender.MALE,30,new BigDecimal(220));
        Person applicant7 = new Person("Favor", "017", Role.CASHIER,Gender.FEMALE,24,new BigDecimal(400));

        cStand = new Cashier[1];
        cStand[0] = new Cashier();

        List<Person> applicant = new ArrayList<>();
        applicant.add(applicant1);
        applicant.add(applicant2);
        applicant.add(applicant3);
        applicant.add(applicant4);
        applicant.add(applicant5);
        applicant.add(applicant6);
        applicant.add(applicant7);

        cashiers = manager.hireEmployee(applicant, "1111");
        manager.assign();

        manager.fireEmployee(applicant3, "1111");

        Person ann = new Person("Ann", "01", Role.CUSTOMER, Gender.FEMALE, 27, new BigDecimal(50000));
        Person peter = new Person("Peter", "02", Role.CUSTOMER, Gender.MALE, 32, new BigDecimal(100000));
        Person mary = new Person("Mary", "03", Role.CUSTOMER, Gender.FEMALE, 40, new BigDecimal(20000));
        Person tim = new Person("Tim", "04", Role.CUSTOMER, Gender.MALE, 17, new BigDecimal(10000));
        Person sarah = new Person("Sarah", "05", Role.CUSTOMER, Gender.FEMALE, 19, new BigDecimal(8000));
        Person steve = new Person("Steve", "06", Role.CUSTOMER, Gender.MALE, 24, new BigDecimal(300000));

        addToQueue(peter,new Product("Arrowroot",6));
        addToQueue(ann,new Product("Carrot",2));
        addToQueue(mary,new Product("Potato Chips",7));
        addToQueue(steve,new Product("Oatmeal Raisin",3));
        addToQueue(sarah,new Product("Chocolate Chip",1));
        addToQueue(tim,new Product("Bran",10));

        processQueue();
    }
    static void processQueue() {
        List<CashierDTO> processedQueue = new LinkedList<>();
        for (Map.Entry<String, LinkedList<PersonDTO>> entry : theQueue.entrySet()) {
            LinkedList<PersonDTO> queue = entry.getValue();
            while (!queue.isEmpty()) {
                PersonDTO nextCustomer = queue.stream()
                        .max(Comparator.comparingInt(PersonDTO::getQuantity))
                        .orElseThrow(NoSuchElementException::new);
                CashierDTO sale = new CashierDTO(nextCustomer, entry.getKey());
                cStand[0].makeSales(sale, store.getStock(cStand[0].getCashier()));
                queue.remove(nextCustomer);
            }
        }
    }

    static void addToQueue(Person person, Product product) {
        String productName = product.getProductName();
        int productQuantity = product.getQuantity();
        if (theQueue.containsKey(productName)) {
            LinkedList<PersonDTO> queue = theQueue.get(productName);
            PersonDTO personDTO = new PersonDTO(person, productQuantity);
            if (queue.contains(personDTO)) {
                int index = queue.indexOf(personDTO);
                queue.get(index).setQuantity(queue.get(index).getQuantity() + productQuantity);
            } else {
                queue.add(personDTO);
            }
        } else {
            LinkedList<PersonDTO> queue = new LinkedList<>();
            queue.add(new PersonDTO(person, productQuantity));
            theQueue.put(productName, queue);
        }
    }


}