package com.aj.Munchio.dto.menu;

import jakarta.validation.constraints.NotBlank;

public class MenuUpdateRequest {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
