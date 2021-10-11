package org.upgrad.upstac.testrequests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.upstac.config.security.UserLoggedInService;
import org.upgrad.upstac.exception.UpgradResponseStatusException;
import org.upgrad.upstac.users.User;

import java.util.List;

@RestController
public class TestRequestController {

    @Autowired
    private UserLoggedInService userLoggedInService;

    @Autowired
    private TestRequestService testRequestService;

    @PostMapping("/api/testrequests")
    public TestRequest createTestRequest(@RequestBody TestRequestInput inputRequest) {
        try {
            User loggedInUser = userLoggedInService.getLoggedInUser();
            return  testRequestService.saveTestRequest(loggedInUser, inputRequest);
        }catch (Exception ex) {
            throw new UpgradResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }


    @GetMapping("/api/testrequests")
    public List<TestRequest> getUserTestRequests() {
        try {
            User loggedInUser = userLoggedInService.getLoggedInUser();
            return testRequestService.getTestRequests(loggedInUser);
        } catch (Exception ex) {
            throw new UpgradResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }



}

