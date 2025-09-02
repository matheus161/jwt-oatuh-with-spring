package br.com.matheus161.authapi.controller;

import br.com.matheus161.authapi.model.User;
import br.com.matheus161.authapi.security.MyToken;
import br.com.matheus161.authapi.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private IUserService service;

    public UserController(IUserService service) {
        super();
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<MyToken> login(@RequestBody User user) {
        return ResponseEntity.ok(service.userLogin(user));
    }
}
