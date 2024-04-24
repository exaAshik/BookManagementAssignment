package com.BookManageMent.Book.Services;

import com.BookManageMent.Book.DTOs.BookDto;
import com.BookManageMent.Book.Utils.BookPage;

import java.util.List;

public interface BookServices {

    BookDto createBook(BookDto bookDto);

    BookDto getBookById(Integer id);

    BookPage findAllBook(Integer pageNumber,
                         Integer pageSize,
                         String SortBy, String SortDir);

    Boolean deleteBook(Integer id);


    BookDto updateBook(Integer id , BookDto bookDto);


}
