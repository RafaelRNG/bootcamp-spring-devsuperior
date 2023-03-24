package com.devsuperior.uri2621.dtos;

import com.devsuperior.uri2621.projections.ProductMinProjection;

public class ProductMinDto {

    private String name;

    public ProductMinDto() {}

    public ProductMinDto(String name) {
        this.name = name;
    }

    public ProductMinDto(ProductMinProjection projection) {
        name = projection.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Product: " + name;
    }
}