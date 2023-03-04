package org.example.model;

import org.example.enums.Role;
import org.example.enums.StockStat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StoreReader {
    private HashMap<String, Product> stock = new HashMap<>();

    private HashMap<String, List<String>> categoryList = new HashMap<>();

    public HashMap<String, Product> getStock(Person person) {
        if (person.getRole() == Role.CASHIER){
            return stock;
        }
        return null;
    }

    public void setStock(HashMap<String, Product> stock) {
        this.stock = stock;
    }

    public HashMap<String, List<String>> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(HashMap<String, List<String>> categoryList) {
        this.categoryList = categoryList;
    }

    public String readFile(String path) throws FileNotFoundException {
        StringBuilder collected = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = reader.readLine()) != null) {
                collected.append(line).append("\n");
            }
            return collected.toString().replace("\"","");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openStore(String path) throws FileNotFoundException {
        String storeList = readFile(path);
        HashMap<String, Product> temporaryStock = new HashMap<>();
        HashMap<String, List<String>> temporaryCategoryList = new HashMap<>();
        String[] output = storeList.split("\n");
        if (output.length > 0){
            for (int i = 1; i < output.length; i++){
                String[] breakLine = output[i].split(",");
                Product product = createProduct(breakLine);
                updateProductStatus(product);
                updateStockAndCategoryList(product, temporaryStock, temporaryCategoryList);
            }
        }
        stock = temporaryStock;
        categoryList = temporaryCategoryList;
    }

    private Product createProduct(String[] bLine) {
        String name = bLine[4];
        String category = bLine[3];
        int quantity = Integer.parseInt(bLine[5]);
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(bLine[6]));
        return new Product(name, price, quantity, category);
    }
    private void updateProductStatus(Product prod) {
        if (prod.getQuantity() > 0) {
            prod.setStockStat(StockStat.IN_STOCK);
        } else {
            prod.setStockStat(StockStat.OUT_OF_STOCK);
        }
    }
    private void updateStockAndCategoryList(Product prod, HashMap<String, Product> stock,
                                            HashMap<String, List<String>> categoryList) {
        String name = prod.getProductName();
        String category = prod.getCategory();
        if (stock.containsKey(name)) {
            int currentQuantity = stock.get(name).getQuantity();
            stock.get(name).setQuantity(currentQuantity + prod.getQuantity());
            stock.get(name).setStockStat(StockStat.IN_STOCK);
        } else {
            stock.put(name, prod);
            if (categoryList.containsKey(category)) {
                if (!categoryList.get(category).contains(name)) {
                    categoryList.get(category).add(name);
                }
            } else {
                List<String> names = new ArrayList<>();
                names.add(name);
                categoryList.put(category, names);
            }
        }
    }
}


