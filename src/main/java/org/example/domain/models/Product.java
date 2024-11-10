package org.example.domain.models;

import java.math.BigDecimal;

public class Product {

    private final Long id;

    private final BigDecimal price;

    private final String name;

    private Product(Long id, BigDecimal price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static class Builder {
        private Long id;
        private BigDecimal price;
        private String name;

        private Builder() {
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Product build() {
            return new Product(id, price, name);
        }
    }

}
