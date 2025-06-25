package com.abednego.smartIntern.service;

import com.abednego.smartIntern.dto.LoginRequestDto;
import com.abednego.smartIntern.dto.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto request);
}
