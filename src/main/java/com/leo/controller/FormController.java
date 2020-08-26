package com.leo.controller;

import com.leo.model.User;
import com.leo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class FormController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView showUserList(@PageableDefault(size = 5) Pageable pageable) {
        Page<User> page = userService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("userList", page);
        modelAndView.addObject("newUser", new User());
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView addUser(@Valid @ModelAttribute("newUser")User user, BindingResult bindingResult, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/index");
        new User().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("message", "Please recheck your information");
        } else {
            userService.save(user);
            Page<User> page = userService.findAll(pageable);
            modelAndView.addObject("message", "New User added");
            modelAndView.addObject("userList", page);
            modelAndView.addObject("newUser", new User());
        }
        return modelAndView;
    }
}
