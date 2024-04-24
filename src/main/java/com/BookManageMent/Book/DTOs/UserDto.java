package com.BookManageMent.Book.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDto {

    private Integer Id;

    private String name;

    private String email;

    @JsonIgnore
    private String password;
}
