package com.BookManageMent.Book.Repositories;

import com.BookManageMent.Book.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
