package com.exam.examserver.controller;

import com.exam.examserver.config.JwtUtils;
import com.exam.examserver.helper.UserNotFoundException;
import com.exam.examserver.model.JwtRequest;
import com.exam.examserver.model.JwtResponse;
import com.exam.examserver.model.User;
import com.exam.examserver.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private JwtUtils jwtUtils;

    //Generate Token Api
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            Authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());

        }catch (UserNotFoundException e){
            e.printStackTrace();
            throw new Exception("User not found !!!");
        }
        //User Authenticate ho chuka ha
        UserDetails user = this.userDetailService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(user);
        JwtResponse response=new JwtResponse();
        response.setToken(token);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    private void Authenticate(String username,String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e){
            throw new Exception("USER DISABLED !!!" +e.getMessage());
        }catch (BadCredentialsException e){
            throw new Exception("INVALID CREDENTIALS !!!" +e.getMessage());
        }
    }
    @GetMapping("/current-user")
    public User getCurrrentUser(Principal principal)
    {
       return ((User) this.userDetailService.loadUserByUsername(principal.getName()));
    }
}
