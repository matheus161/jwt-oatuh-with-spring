package br.com.matheus161.authapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/open")
    public String sayHelloOpen() {
        return "Hello world from endpoint";
    }

    @GetMapping("/restricted")
    public String sayHelloRestricted() {
        return "Hello world from restricted endpoint";
    }
}
