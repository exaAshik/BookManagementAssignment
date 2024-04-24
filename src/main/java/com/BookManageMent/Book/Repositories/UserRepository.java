package com.BookManageMent.Book.Repositories;

import com.BookManageMent.Book.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {


    User findByEmail(String email);

}
