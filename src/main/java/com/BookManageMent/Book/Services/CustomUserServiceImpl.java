package com.BookManageMent.Book.Services;

import com.BookManageMent.Book.Entities.User;
import com.BookManageMent.Book.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userRepository.findByEmail(username);

        if(user == null){
            throw  new UsernameNotFoundException("user not found with email "+ username);

        }

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("Normal");

        List<GrantedAuthority> authorityList = List.of(grantedAuthority);

        return  new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),
                authorityList);


    }
}
