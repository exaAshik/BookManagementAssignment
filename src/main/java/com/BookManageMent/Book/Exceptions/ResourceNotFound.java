package com.BookManageMent.Book.Exceptions;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String message) {
        super(message);
    }
}
