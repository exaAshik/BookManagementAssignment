package com.BookManageMent.Book.Controllers;

import com.BookManageMent.Book.DTOs.BookDto;
import com.BookManageMent.Book.Services.BookServices;
import com.BookManageMent.Book.Utils.BookPage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class BookController {

    @Autowired
    private BookServices bookServices;

    @CrossOrigin(origins = "*")
    @PostMapping("/createBook")
    public ResponseEntity<?>createBook(@RequestBody @Valid BookDto bookDto ){

        BookDto book = bookServices.createBook(bookDto);

        return new ResponseEntity<>(book, HttpStatus.CREATED);

    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<?>getBookById(@PathVariable Integer id){

        BookDto bookById = bookServices.getBookById(id);

        return new ResponseEntity<>(bookById,HttpStatus.FOUND);

    }

    @GetMapping("/getAllBook")
    public ResponseEntity<?>getAllBook( @RequestParam(name = "pageNumber",defaultValue ="0") Integer pageNumber,
                                        @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize,
                                        @RequestParam(name = "SortBy" ,defaultValue = "Id") String SortBy,
                                        @RequestParam(name = "SortDir",defaultValue = "desc") String SortDir){


        BookPage allBook = bookServices.findAllBook(pageNumber, pageSize, SortBy, SortDir);

        return new ResponseEntity<>(allBook,HttpStatus.FOUND);


    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<?>deleteBook(@PathVariable Integer id){
        Boolean b = bookServices.deleteBook(id);

        if(b){
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Error on deleting",HttpStatus.BAD_REQUEST);
        }

    }


    @CrossOrigin(origins = "*")
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<?>updateBook(@RequestBody BookDto bookDto, @PathVariable Integer id){

        BookDto bookDto1 = bookServices.updateBook(id, bookDto);

        return new ResponseEntity<>(bookDto1,HttpStatus.OK);

    }




}
