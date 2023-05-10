package com.arjunshaji.demo.controller;

import com.arjunshaji.demo.Entity.Admin;
import com.arjunshaji.demo.repository.AdminRepo;
import com.arjunshaji.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminService adminService;

    @PostMapping("/save")
    public Object saveAdmin(@RequestBody Admin admin){
        return adminService.saveAdmin(admin);
    }

    @PostMapping("/login")
    public String login(@RequestBody Admin admin, HttpSession session){
        try{
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(admin.getUsername(),admin.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            session.setAttribute("username",admin.getUsername());
            return "redirect:/home";
        } catch (AuthenticationException e){
            return "redirect:/login?error";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        SecurityContextHolder.clearContext();
        session.invalidate();
        return "redirect:/login";
    }
}
