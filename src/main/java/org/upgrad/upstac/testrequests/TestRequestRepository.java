package org.upgrad.upstac.testrequests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.upgrad.upstac.users.User;

import java.util.List;

public interface TestRequestRepository extends JpaRepository<TestRequest, Long> {
    List<TestRequest> findByCreatedBy(User loggedInUser);
}
