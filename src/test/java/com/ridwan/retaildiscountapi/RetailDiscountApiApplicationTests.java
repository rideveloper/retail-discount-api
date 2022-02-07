package com.ridwan.retaildiscountapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridwan.retaildiscountapi.entity.request.UserRequest;
import com.ridwan.retaildiscountapi.enums.UserType;
import com.ridwan.retaildiscountapi.model.User;
import com.ridwan.retaildiscountapi.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {RetailDiscountApiApplicationTests.class})
class RetailDiscountApiApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        assertThat(userService).isNotNull();
    }

    @Test
    @DisplayName("Register User Test")
    void test_register_user() throws JsonProcessingException {
        UserRequest user = new UserRequest();
        user.setFirstName("First Name");
        user.setLastName("Last Name");
        user.setEmail("email@test.com");
        user.setPhoneNumber("09029281829");
        user.setUserType(UserType.EMPLOYEE);
        user.setCreatedDate(LocalDate.now());

        User response = userService.registerUser(user);

        assertNotNull(response, "Create User response is null");

        System.out.println(objectMapper.writeValueAsString(response));
    }

}
