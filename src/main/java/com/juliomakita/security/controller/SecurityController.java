package com.juliomakita.security.controller;

import com.juliomakita.security.model.TokenResponse;
import com.juliomakita.security.model.User;
import com.juliomakita.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value="/token",method = RequestMethod.GET, params= {"username", "password"})
    public TokenResponse token(@RequestParam(value="username") String username , @RequestParam(value="password") String password) {
        return this.securityService.getUserToken(username, password);
    }

    @RequestMapping(value="/generateToken",method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody User user) {

        try {
            final TokenResponse userToken = this.securityService.getUserToken(user.getUsername(), user.getPassword());
            return new ResponseEntity<>(userToken, HttpStatus.OK);
        }catch (SecurityException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
