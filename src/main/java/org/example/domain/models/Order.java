package org.example.domain.models;

import java.util.List;

public class Order {

    private final Long id;
    private final String description;
    private final List<Product> productList;

    private Order(Long id, String description, List<Product> productList) {
        this.id = id;
        this.description = description;
        this.productList = productList;
    }

    public Order(Order other, String description) {
        this.id = other.id;
        this.description = description;
        this.productList = other.productList;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String description;
        private List<Product> productList;

        private Builder() {
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setProductList(List<Product> productList) {
            this.productList = productList;
            return this;
        }

        public Order build() {
            return new Order(id, description, productList);
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", productList=" + productList +
                '}';
    }
}
