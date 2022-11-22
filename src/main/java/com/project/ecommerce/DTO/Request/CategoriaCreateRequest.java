package com.project.ecommerce.DTO.Request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class CategoriaCreateRequest {

    private String name;

    public CategoriaCreateRequest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
