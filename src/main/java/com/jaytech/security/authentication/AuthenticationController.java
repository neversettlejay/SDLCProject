package com.jaytech.security.authentication;

import com.jaytech.security.roles.dto.CustomHttpResponse;
import com.jaytech.security.service.implementation.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/back-end/user")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    //Register user
    @PostMapping("/sign-up")
    public ResponseEntity<CustomHttpResponse> registerUser(
            @RequestBody RegisterRequest registerRequest
    ) {
        return authenticationService.registerUser(registerRequest).getHttpStatus().is4xxClientError() ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(authenticationService.registerUser(registerRequest)) :
                ResponseEntity.ok(authenticationService.registerUser(registerRequest));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<CustomHttpResponse> authenticateUser(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        return ResponseEntity.ok(authenticationService.authenticateUser(authenticationRequest));
    }

}
