package com.BookManageMent.Book.Utils;

import com.BookManageMent.Book.DTOs.UserDto;
import lombok.Data;

@Data
public class LoginResponse {

    private String token;

    private boolean isAuthenticated;

    private UserDto userDto;
}
