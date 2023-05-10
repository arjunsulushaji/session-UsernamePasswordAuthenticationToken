package com.arjunshaji.demo.service;

import com.arjunshaji.demo.Entity.Admin;
import com.arjunshaji.demo.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepo.findByUsername(username);
        if (admin == null){
            throw new UsernameNotFoundException("INVALID USERNAME OR PASSWORD");
        }
        return new User(admin.getUsername(),admin.getPassword(),new ArrayList<>());
    }
}
