package org.example.model;

import org.example.enums.StockStat;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private String productName;
    private BigDecimal amount;
    private int quantity;
    private StockStat stockStat;
    private String category;

    public Product(String productName, BigDecimal amount, int quantity, String category) {
        this.productName = productName;
        this.amount = amount;
        this.quantity = quantity;
        this.category = category;
    }

    public Product(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public StockStat getStockStat() {
        return stockStat;
    }

    public void setStockStat(StockStat stockStat) {
        this.stockStat = stockStat;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return getProductName().equals(product.getProductName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductName());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", amount=" + amount +
                ", quantity=" + quantity +
                ", stockStat=" + stockStat +
                ", category='" + category + '\'' +
                '}';
    }
}
