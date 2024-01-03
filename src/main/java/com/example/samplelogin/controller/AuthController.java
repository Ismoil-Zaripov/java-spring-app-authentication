package com.example.samplelogin.controller;

import com.example.samplelogin.dto.request.RegisterRequest;
import com.example.samplelogin.entity.Profile;
import com.example.samplelogin.repository.ProfileRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(
            Authentication authentication,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        new SecurityContextLogoutHandler()
                .logout(request, response, authentication);
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerRequest", RegisterRequest.builder().build());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute("registerRequest")
            RegisterRequest registerRequest,
            BindingResult bindingResult,
            Model model
    ) {

        if (bindingResult.hasErrors()) {
            return "redirect:/register";
        }

        if (profileRepository.findByUsername(registerRequest.username()).isPresent()) {
            model.addAttribute("userIsExists", true);
            return "redirect:/register";
        }

        Profile profile = Profile.builder()
                .username(registerRequest.username())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role("USER")
                .firstname(registerRequest.firstname())
                .lastname(registerRequest.lastname())
                .build();

        profileRepository.save(profile);

        return "redirect:/login";
    }
}
