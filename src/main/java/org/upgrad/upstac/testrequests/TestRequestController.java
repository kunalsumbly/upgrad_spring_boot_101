package org.upgrad.upstac.testrequests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.upstac.config.security.UserLoggedInService;
import org.upgrad.upstac.users.User;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TestRequestController {

    @Autowired
    private UserLoggedInService userLoggedInService;

    @Autowired
    private TestRequestRepository testRequestRepository;

    @PostMapping("/api/testrequests")
    public TestRequest createTestRequest(@RequestBody TestRequestInput inputRequest){
        User loggedInUser = userLoggedInService.getLoggedInUser();
        TestRequest testRequest = transformToTestRequestEntity(inputRequest, loggedInUser, RequestStatus.INITIATED);
        return testRequestRepository.save(testRequest);
    }

    @GetMapping("/api/testrequests")
    public List<TestRequest> getUserTestRequests(){
        User loggedInUser = userLoggedInService.getLoggedInUser();
        return testRequestRepository.findByCreatedBy(loggedInUser);
    }

    private TestRequest transformToTestRequestEntity(TestRequestInput inputRequest, User loggedInUser, RequestStatus status) {
        TestRequest testRequest = new TestRequest();
        testRequest.setCreated(LocalDate.now());
        testRequest.setAddress(inputRequest.getAddress());
        testRequest.setCreatedBy(loggedInUser);
        testRequest.setEmail(inputRequest.getEmail());
        testRequest.setGender(inputRequest.getGender());
        testRequest.setPhoneNumber(inputRequest.getPhoneNumber());
        testRequest.setStatus(status);
        testRequest.setAge(inputRequest.getAge());
        testRequest.setPinCode(inputRequest.getPinCode());
        testRequest.setName(inputRequest.getName());
        return testRequest;
    }

}

