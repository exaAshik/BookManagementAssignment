package com.BookManageMent.Book.Controllers;

import com.BookManageMent.Book.DTOs.UserDto;
import com.BookManageMent.Book.Entities.User;
import com.BookManageMent.Book.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/createUser")
    public ResponseEntity<?>createUser(@RequestBody UserDto userDto){

        User map = modelMapper.map(userDto, User.class);

        map.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User save = userRepository.save(map);

        UserDto map1 = modelMapper.map(save, UserDto.class);


        return new ResponseEntity<>(map1, HttpStatus.CREATED);

    }

}
