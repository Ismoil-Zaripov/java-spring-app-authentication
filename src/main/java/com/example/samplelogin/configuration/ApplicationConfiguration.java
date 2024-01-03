package com.example.samplelogin.configuration;

import com.example.samplelogin.entity.Profile;
import com.example.samplelogin.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            if (profileRepository.findByUsername("ismoil").isEmpty()) {

                Profile profile = Profile.builder()
                        .id(null)
                        .firstname("Ismoil")
                        .lastname("Zaripov")
                        .username("ismoil")
                        .password(passwordEncoder.encode("ismoil"))
                        .role("ADMIN")
                        .build();

                profileRepository.save(profile);
            }
        };
    }
}
