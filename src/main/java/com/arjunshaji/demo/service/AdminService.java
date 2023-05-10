package com.arjunshaji.demo.service;

import com.arjunshaji.demo.Entity.Admin;
import com.arjunshaji.demo.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Object saveAdmin(Admin admin) {
        Admin admin1 = new Admin();
        admin1.setUsername(admin.getUsername());
        admin1.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepo.save(admin1);
    }
}
