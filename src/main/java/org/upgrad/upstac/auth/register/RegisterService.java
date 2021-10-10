package org.upgrad.upstac.auth.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.upgrad.upstac.exception.AppException;
import org.upgrad.upstac.exception.UpgradResponseStatusException;
import org.upgrad.upstac.users.User;
import org.upgrad.upstac.users.UserService;
import org.upgrad.upstac.users.models.AccountStatus;
import org.upgrad.upstac.users.roles.UserRole;

import java.time.LocalDateTime;

import static org.upgrad.upstac.shared.DateParser.getDateFromString;


@Service
public class RegisterService {

    @Autowired
    private UserService userService;


    private static final Logger log = LoggerFactory.getLogger(RegisterService.class);


    public User addUser(RegisterRequest registerRequest) {
        try {
            validateIfUserAlreadyExists(registerRequest);
            User newUser = transformToUserEntity(registerRequest, AccountStatus.APPROVED);
            User updatedUser = userService.saveInDatabase(newUser);
            return updatedUser;
        } catch (Exception ex) {
            throw new UpgradResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    private void validateIfUserAlreadyExists(RegisterRequest registerRequest) {
        if (userService.findByUserName(registerRequest.getUserName()) != null) {
            throw new AppException("User name already exists");
        }
        if (userService.findByEmail(registerRequest.getEmail()) != null) {
            throw new AppException("User name already exists");
        }
        if (userService.findByPhoneNumber(registerRequest.getPhoneNumber()) != null) {
            throw new AppException("User name already exists");
        }
    }

    private User transformToUserEntity(RegisterRequest registerRequest, AccountStatus status) {
        User newUser = new User();
        newUser.setUserName(registerRequest.getUserName());
        newUser.setPassword(userService.toEncrypted(registerRequest.getPassword()));
        newUser.setRoles(userService.getRoleFor(UserRole.USER));
        newUser.setCreated(LocalDateTime.now());
        newUser.setUpdated(LocalDateTime.now());
        newUser.setAddress(registerRequest.getAddress());
        newUser.setFirstName(registerRequest.getFirstName());
        newUser.setLastName(registerRequest.getLastName());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPhoneNumber(registerRequest.getPhoneNumber());
        newUser.setPinCode(registerRequest.getPinCode());
        newUser.setGender(registerRequest.getGender());
        newUser.setAddress(registerRequest.getAddress());
        newUser.setDateOfBirth(getDateFromString(registerRequest.getDateOfBirth()));
        newUser.setStatus(status);
        return newUser;
    }

    public User addDoctor(RegisterRequest registerRequest) {
        try {
            validateIfUserAlreadyExists(registerRequest);
            User newUser = transformToUserEntity(registerRequest, AccountStatus.INITIATED);
            User updatedUser = userService.saveInDatabase(newUser);
            return updatedUser;
        } catch (Exception ex) {
            throw new UpgradResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

        }

    }


    public User addTester(RegisterRequest registerRequest) {
        try {
            validateIfUserAlreadyExists(registerRequest);
            User newUser = transformToUserEntity(registerRequest, AccountStatus.INITIATED);
            User updatedUser = userService.saveInDatabase(newUser);
            return updatedUser;
        } catch (Exception ex) {
            throw new UpgradResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

        }
    }


}
