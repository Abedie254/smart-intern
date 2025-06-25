package com.abednego.smartIntern.service.impl;

import com.abednego.smartIntern.dto.LoginRequestDto;
import com.abednego.smartIntern.dto.LoginResponseDto;
import com.abednego.smartIntern.dto.UserDto;
import com.abednego.smartIntern.exception.InvalidCredentialsException;
import com.abednego.smartIntern.model.User;
import com.abednego.smartIntern.repository.UserRepository;
import com.abednego.smartIntern.security.CustomUserDetails;
import com.abednego.smartIntern.security.JwtService;
import com.abednego.smartIntern.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        log.info("Authenticating user: {}", request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // Generate token using CustomUserDetails (which implements UserDetails)
        String token = jwtService.generateToken(new CustomUserDetails(user));

        // Build DTO for returning user info
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();

        // Return response with token + user details
        return LoginResponseDto.builder()
                .token(token)
                .user(userDto)
                .build();
    }
}
