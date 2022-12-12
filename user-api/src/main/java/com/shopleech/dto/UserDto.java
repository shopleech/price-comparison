package com.shopleech.dto;

import java.util.List;
import java.util.UUID;

public class UserDto {

    private UUID id;
    private String username;
    private String password;
    private String name;
    private List<ContactDto> contacts;

    public UserDto() {

    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    // standard getters and setters
}