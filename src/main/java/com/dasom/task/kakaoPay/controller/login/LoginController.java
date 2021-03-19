package com.dasom.task.kakaoPay.controller.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {


    @PostMapping("/login")
    public ResponseEntity<String> authenticate(HttpServletResponse response) {
        //String jwt = response.getHeader(SecurityParams.JWT_HEADER_NAME);
        return new ResponseEntity<>("jwt", HttpStatus.OK);
    }


    // LOGIN SUCCESS
    @GetMapping("/loginSuccess")
    public String loginSuccess() {
        return "index";
    }
}
