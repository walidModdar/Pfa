package com.example.pfa5eme.controller;

import com.example.pfa5eme.Service.Impl.UserService;
import com.example.pfa5eme.model.JwtRequest;
import com.example.pfa5eme.model.JwtResponse;
import com.example.pfa5eme.Service.Impl.JwtService;
import com.example.pfa5eme.model.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;



    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
    @PostMapping("/add")
    public String add(@RequestBody User2 user2){
        jwtService.register(user2);
        return "User Added Successfully...";
    }

}
