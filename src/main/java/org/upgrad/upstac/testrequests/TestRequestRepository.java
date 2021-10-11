package org.upgrad.upstac.testrequests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.upgrad.upstac.users.User;
import org.upgrad.upstac.users.models.AccountStatus;

import java.util.List;
import java.util.Optional;

public interface TestRequestRepository extends JpaRepository<TestRequest, Long> {
    List<TestRequest> findByCreatedBy(User loggedInUser);
    Optional<TestRequest> findByEmail(String email);
    Optional<TestRequest> findByPhoneNumber(String phoneNumber);
}
