package com.example.authenticationservice.authen_service.service;

import com.example.authenticationservice.account.pojo.Account;
import com.example.authenticationservice.authen_service.entities.AuthRequest;
import com.example.authenticationservice.authen_service.entities.AuthResponse;
import com.example.authenticationservice.authen_service.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtUtils jwt;
    @Autowired
    public AuthService(final JwtUtils jwt) { this.jwt = jwt; }

    // use account from AccountService
    public AuthResponse logIn(AuthRequest authRequest, Account account) {
        BCryptPasswordEncoder passwordRequest = new BCryptPasswordEncoder();
        try {
            if (passwordRequest.matches(authRequest.getPassword(), account.getPassword())) {
                User user = User.builder()
                        .id(account.get_id())
                        .name(account.getName())
                        .build();
                String accessToken = jwt.generate(user, "ACCESS");
                String refreshToken = jwt.generate(user, "REFRESH");
                return new AuthResponse(accessToken, refreshToken, account.getName(), "Complete");
            }
            else {
                return new AuthResponse("", "", "","Unauthorized ! Password not correct or Have error in process");
            }
        }
        catch (Exception e) {
            return new AuthResponse("", "","",e.getLocalizedMessage());
        }
    }
}