package com.example.controller;

import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    // --- 1. Home Page / Redirection ---
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String homePage() {
        return "home"; // home.jsp: Links to /register and /login
    }

    // --- 2. Registration Logic ---
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration"; // registration.jsp: The registration form
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model) {
        
        if (result.hasErrors()) {
            return "registration"; 
        }

        if (userDao.existsByUsername(user.getUsername())) {
            result.rejectValue("username", "error.user", "Username already exists.");
            return "registration";
        }
        
        userDao.save(user);
        model.addAttribute("message", "Registration successful! Please login.");
        return "home";
    }

    // --- 3. Login Logic ---
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginUser", new User());
        return "login"; // login.jsp: The login form
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("loginUser") User loginAttempt, 
                            HttpSession session, 
                            Model model) {
        
        // 1. No username/password provided
        if (loginAttempt.getUsername() == null || loginAttempt.getUsername().isEmpty() || 
            loginAttempt.getPassword() == null || loginAttempt.getPassword().isEmpty()) {
            model.addAttribute("error", "There was no username/password provided.");
            return "login";
        }

        User storedUser = userDao.findByUsername(loginAttempt.getUsername());

        // 2. No such user in the system
        if (storedUser == null) {
            model.addAttribute("error", "There is no such user in the system.");
            return "login";
        }

        // 3. The combination of username/password is wrong
        if (!storedUser.getPassword().equals(loginAttempt.getPassword())) {
            model.addAttribute("error", "The combination of username/password is wrong.");
            return "login";
        }

        // Login successful: Store username in session
        session.setAttribute("userName", storedUser.getUsername());

        // Redirect to Welcome Page
        return "redirect:/welcome"; 
    }

    // --- 4. Welcome Page & Security ---
    @GetMapping("/welcome")
    public String welcomePage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("userName");

        // Security requirement: Cannot be accessed directly without logging in.
        if (username == null) {
            return "redirect:/login"; 
        }

        // Welcome message: Eg: Welcome John - if John is the user name
        model.addAttribute("welcomeMessage", "Welcome " + username);
        return "welcome"; // welcome.jsp
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, SessionStatus status) {
        session.invalidate();
        status.setComplete(); 
        return "redirect:/login";
    }
}