package com.ridwan.retaildiscountapi.config;

import com.ridwan.retaildiscountapi.entity.request.UserRequest;
import com.ridwan.retaildiscountapi.enums.UserType;
import com.ridwan.retaildiscountapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
@Slf4j
public class PreloadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserService userService) {
        return args -> {
            LocalDate date = LocalDate.of(2018, Month.AUGUST, 1);

            UserRequest user1 = new UserRequest();
            user1.setFirstName("Sam");
            user1.setLastName("White");
            user1.setEmail("sam@testmail.test");
            user1.setPhoneNumber("00016252617");
            user1.setUserType(UserType.CUSTOMER);
            user1.setCreatedDate(date);

            UserRequest user2 = new UserRequest();
            user2.setFirstName("Fatima");
            user2.setLastName("Asuma");
            user2.setEmail("fati@testmail.test");
            user2.setPhoneNumber("00016222617");
            user2.setUserType(UserType.AFFILIATE);
            user2.setCreatedDate(date);

            UserRequest user3 = new UserRequest();
            user3.setFirstName("Ben");
            user3.setLastName("Jerry");
            user3.setEmail("ben@testmail.test");
            user3.setPhoneNumber("00216252617");
            user3.setUserType(UserType.EMPLOYEE);
            user3.setCreatedDate(date);

            log.info("Preloading user"+ userService.registerUser(user1));
            log.info("Preloading user"+ userService.registerUser(user2));
            log.info("Preloading user"+ userService.registerUser(user3));

        };
    }
}
