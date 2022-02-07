package com.ridwan.retaildiscountapi.service.impl;

import com.ridwan.retaildiscountapi.entity.request.UserRequest;
import com.ridwan.retaildiscountapi.enums.Status;
import com.ridwan.retaildiscountapi.enums.UserType;
import com.ridwan.retaildiscountapi.exception.CustomException;
import com.ridwan.retaildiscountapi.model.User;
import com.ridwan.retaildiscountapi.repo.UserRepo;
import com.ridwan.retaildiscountapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * @author Ridwan Mustapha
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserType getUserType(Long userId) {
        Optional<User> optionalUser = repo.findById(userId);
        if (optionalUser.isEmpty())
            throw new CustomException("User not found", HttpStatus.NOT_FOUND, Status.FAILED);

        return optionalUser.get().getUserType();
    }

    @Override
    public User getUser(Long userId) {
        Optional<User> optionalUser = repo.findById(userId);
        if (optionalUser.isEmpty())
            throw new CustomException("User not found", HttpStatus.NOT_FOUND, Status.FAILED);

        return optionalUser.get();
    }

    @Override
    public boolean isCustomerValidForDiscount(Long userId) {
        Optional<User> optionalUser = repo.findById(userId);
        if (optionalUser.isEmpty())
            throw new CustomException("User not found", HttpStatus.NOT_FOUND, Status.FAILED);

        User user = optionalUser.get();

        if (!user.getUserType().equals(UserType.CUSTOMER))
            throw new CustomException("User not a customer", HttpStatus.BAD_REQUEST, Status.FAILED);

        if (getNumberOfYears(user.getCreatedDate()) >= 2)
            return true;

        return false;
    }

    @Override
    public User registerUser(UserRequest request) {
        if (isUserExisting(request))
            throw new CustomException("User Already Exists", HttpStatus.BAD_REQUEST, Status.FAILED);

        //create user model
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCreatedDate(request.getCreatedDate());
        user.setUserType(request.getUserType());

        repo.saveAndFlush(user);

        return user;
    }

    private int getNumberOfYears(LocalDate date) {
        long age = LocalDate.from(date).until(LocalDate.now(), ChronoUnit.YEARS);
        return (int) age;
    }

    private boolean isUserExisting(UserRequest request) {
        //Checks if user exists by querying unique fields
        return repo.findByPhoneNumberOrEmail(request.getPhoneNumber(),
                request.getEmail()) != null;
    }
}
