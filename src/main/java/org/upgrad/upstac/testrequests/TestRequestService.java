package org.upgrad.upstac.testrequests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.upstac.exception.AppException;
import org.upgrad.upstac.users.User;

import java.time.LocalDate;
import java.util.List;

@Service
public class TestRequestService {

    @Autowired
    private TestRequestRepository testRequestRepository;

    public TestRequest saveTestRequest(User user, TestRequestInput testRequestInput) {
        validateTestRequest(testRequestInput, user, RequestStatus.COMPLETED);
        return transformToTestRequestEntity(testRequestInput, user, RequestStatus.INITIATED);
    }

    private void validateTestRequest(TestRequestInput inputRequest, User loggedInUser, RequestStatus status) {
        if (testRequestRepository.findByCreatedBy(loggedInUser).stream().anyMatch(
                s -> s.getStatus() != status
                        && s.getEmail().equals(inputRequest.getEmail())
                        && s.getPhoneNumber().equals(inputRequest.getPhoneNumber()))) {
            throw new AppException("Request already exists in Initiated status for the email="
                    + inputRequest.getEmail() + ",and phonenumber=" + inputRequest.getPhoneNumber());
        }
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

    public List<TestRequest> getTestRequests(User user) {

        return testRequestRepository.findByCreatedBy(user);
    }

}
