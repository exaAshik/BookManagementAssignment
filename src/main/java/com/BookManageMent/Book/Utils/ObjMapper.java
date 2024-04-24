package com.BookManageMent.Book.Utils;

import com.BookManageMent.Book.DTOs.BookDto;
import com.BookManageMent.Book.Entities.Author;
import com.BookManageMent.Book.Entities.Book;

import java.util.Set;

public class ObjMapper {


    public static Book DtoToBoo(BookDto bookDto,Book book){

        Set<Author> authorList = bookDto.getAuthorList();
        Set<Author> authorList1 = book.getAuthorList();

        for(Author author:authorList){
            if(authorList1.contains(author))continue;
            author.setId(null);

        }
        book.setAuthorList(bookDto.getAuthorList());

        book.setYear(bookDto.getYear());
        book.setTitle(bookDto.getTitle());
        book.setPublisher(bookDto.getPublisher());
        book.setIsAcademic(bookDto.getIsAcademic());
        book.setIsbn(bookDto.getIsbn());

        return book;



    }

}
