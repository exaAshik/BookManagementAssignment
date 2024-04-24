package com.BookManageMent.Book.Services.ServiceImpl;

import com.BookManageMent.Book.DTOs.BookDto;
import com.BookManageMent.Book.Entities.Author;
import com.BookManageMent.Book.Entities.Book;
import com.BookManageMent.Book.Exceptions.ResourceNotFound;
import com.BookManageMent.Book.Repositories.AuthorRepository;
import com.BookManageMent.Book.Repositories.BookRepository;
import com.BookManageMent.Book.Services.BookServices;
import com.BookManageMent.Book.Utils.BookPage;
import com.BookManageMent.Book.Utils.ObjMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookServices {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public BookDto createBook(BookDto bookDto) {

        Book map = modelMapper.map(bookDto, Book.class);
        map.setAuthorList(bookDto.getAuthorList());

        Book save = bookRepository.save(map);

        BookDto map1 = modelMapper.map(save, BookDto.class);

        map1.setAuthorList(save.getAuthorList());

        return map1;
    }

    @Override
    public BookDto getBookById(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Resource Not Found"));

        System.out.println(book.getAuthorList());



        BookDto map = modelMapper.map(book, BookDto.class);

        map.setAuthorList(book.getAuthorList());
        return map;
    }

    @Override
    public BookPage findAllBook(Integer pageNumber, Integer pageSize, String SortBy, String SortDir) {

        Sort sort = SortDir.equalsIgnoreCase("asc")? Sort.by(SortBy).ascending():Sort.by(SortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<Book> all = bookRepository.findAll(pageable);

        List<BookDto> collect = all.stream().map((book) -> {
            BookDto map = modelMapper.map(book, BookDto.class);
            map.setAuthorList(book.getAuthorList());
            return map;
        }).collect(Collectors.toList());


        BookPage build = BookPage.builder()
                .content(collect)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .isLastPage(all.isLast())
                .totalElement((int) all.getTotalElements())
                .totalPages(all.getTotalPages())
                .build();



        return build;
    }

    @Override
    public Boolean deleteBook(Integer id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Resource Not Found"));

        book.setAuthorList(new HashSet<>());

        Book save = bookRepository.save(book);

        try {
            bookRepository.delete(save);
            return true;
        }catch (Exception ex){
            return false;
        }

    }

    @Override
    public BookDto updateBook(Integer id, BookDto bookDto) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Resource Not found"));

        Book book1 = ObjMapper.DtoToBoo(bookDto, book);

        Book save = bookRepository.save(book1);

        BookDto map = modelMapper.map(save, BookDto.class);

        map.setAuthorList(save.getAuthorList());

        return map;
    }


}
