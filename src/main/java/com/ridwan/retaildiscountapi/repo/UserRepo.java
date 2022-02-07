package com.ridwan.retaildiscountapi.repo;

import com.ridwan.retaildiscountapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ridwan Mustapha
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByPhoneNumberOrEmail(String phoneNumber, String email);
}
