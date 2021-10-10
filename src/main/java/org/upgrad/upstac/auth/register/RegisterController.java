package org.upgrad.upstac.auth.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.upgrad.upstac.exception.AppException;
import org.upgrad.upstac.users.User;

import static org.upgrad.upstac.exception.UpgradResponseStatusException.asBadRequest;

@RestController
public class RegisterController {


    private RegisterService registerService;


    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);


    @Autowired
    public RegisterController(RegisterService userService) {

        this.registerService = userService;
    }


    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public User saveUser(@RequestBody RegisterRequest registerRequest) {
        return registerService.addUser(registerRequest);
    }


    @RequestMapping(value = "/auth/doctor/register")
    public User saveDoctor(@RequestBody RegisterRequest registerRequest) {
        return registerService.addDoctor(registerRequest);
    }


    @RequestMapping(value = "/auth/tester/register")
    public User saveTester(@RequestBody RegisterRequest registerRequest) {
        return registerService.addTester(registerRequest);
    }
}