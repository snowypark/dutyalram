
package com.projectnmt.dutyalram.controller;

import com.projectnmt.dutyalram.aop.annotation.ValidAspect;
import com.projectnmt.dutyalram.dto.OAuth2SignupReqDto;
import com.projectnmt.dutyalram.dto.SignInReqDto;
import com.projectnmt.dutyalram.dto.SignUpReqDto;
import com.projectnmt.dutyalram.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @ValidAspect
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpReqDto signUpReqDto, BindingResult bindingResult) {
        authService.signup(signUpReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInReqDto signInReqDto) {
        return ResponseEntity.ok(authService.signin(signInReqDto));
    }

    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        authService.signout(userId);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/oauth2/signup")
    public ResponseEntity<?> oAuth2Signup(@RequestBody OAuth2SignupReqDto oAuth2SignupReqDto) {
        String accessToken = authService.oAuth2Signup(oAuth2SignupReqDto);
        return ResponseEntity.created(null).body(accessToken);
    }

}

