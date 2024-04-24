package com.BookManageMent.Book.DTOs;

import com.BookManageMent.Book.Entities.Author;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class BookDto {

    private Integer Id;

    @NotBlank(message = "Must need to give title of book")
    private String title;

    @NotBlank(message = "Must need to give isbn")
    private String isbn;

    @NotBlank(message = "Must need to give publisher info")
    private String publisher;

    @NotBlank(message = "Must need to send Book publish year info")
    private String year;


    private Boolean isAcademic;

    @NotEmpty(message = "Must need to give minimum 1 Author Information")
    Set<Author> authorList= new HashSet<>();

}
