package com.example.samplelogin.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
    @RequestMapping("/dashboard")
    public String dashboard(Model model) {

        var authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        model.addAttribute("authorities", authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList());
        model.addAttribute("userCredentials", authentication.getCredentials());
        model.addAttribute("userDetails", authentication.getDetails());
        model.addAttribute("userPrincipal", authentication.getPrincipal());

        return "dashboard";
    }
}
