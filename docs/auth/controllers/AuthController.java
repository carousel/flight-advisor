package com.miro.flightadvisor.auth.controllers;

import com.miro.flightadvisor.auth.beans.Login;
import com.miro.flightadvisor.auth.beans.Signup;
import com.miro.flightadvisor.auth.services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private SignupService signupService;

    public AuthController(SignupService signupService) {
        this.signupService = signupService;
    }


    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginBean") Login loginBean) {
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute("signupBean") Signup signupBean) {
        return "auth/signup";
    }


    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("signupBean") Signup signupBean, BindingResult bindingResult) throws ServletException {
        if (!bindingResult.hasErrors()) {
              signupService.createNewUser(signupBean);
//            request.login(user.getUsername(), signupBean.getPassword());
            return "redirect:/todos/view";
        }
        return "auth/signup";
    }
    @GetMapping("/login-success")
    public String customLogin(@ModelAttribute("loginBean") Login loginBean, RedirectAttributes redirectAttributes)  {
        redirectAttributes.addFlashAttribute("loggedin", "You are now logged in");
        return "redirect:/todos/create";
    }


    @GetMapping("/login-errors")
    public String loginError(@Valid @ModelAttribute("loginBean") Login loginBean, BindingResult bindingResult) {
        return "auth/login";
    }

}
