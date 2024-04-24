package com.BookManageMent.Book.Controllers;

import com.BookManageMent.Book.Configurattion.TokenProvider;
import com.BookManageMent.Book.DTOs.UserDto;
import com.BookManageMent.Book.Entities.User;
import com.BookManageMent.Book.Repositories.UserRepository;
import com.BookManageMent.Book.Services.CustomUserServiceImpl;
import com.BookManageMent.Book.Utils.LoginRequest;
import com.BookManageMent.Book.Utils.LoginResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private CustomUserServiceImpl customUserServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Authentication authentication(String email, String password){

        UserDetails userDetails = customUserServiceImpl.loadUserByUsername(email);

        if(userDetails == null){
            throw  new BadCredentialsException("Invalid userName ...");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){

        String email = loginRequest.getEmail();

        String password = loginRequest.getPassword();

        Authentication authentication = authentication(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwtToken = tokenProvider.generateToken(authentication);

        User byEmail = userRepository.findByEmail(tokenProvider.getEmailFromToken(jwtToken));

        UserDto map = modelMapper.map(byEmail, UserDto.class);


        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAuthenticated(true);
        loginResponse.setToken(jwtToken);
        loginResponse.setUserDto(map);


        return new ResponseEntity<>(loginResponse, HttpStatus.OK);




    }






}
