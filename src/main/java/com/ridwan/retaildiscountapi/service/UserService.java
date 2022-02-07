package com.ridwan.retaildiscountapi.service;

import com.ridwan.retaildiscountapi.entity.request.UserRequest;
import com.ridwan.retaildiscountapi.enums.UserType;
import com.ridwan.retaildiscountapi.model.User;

/**
 * @author Ridwan Mustapha
 */
public interface UserService {
    UserType getUserType(Long userId);
    User getUser(Long userId);
    boolean isCustomerValidForDiscount(Long userId);
    User registerUser(UserRequest request);
}
