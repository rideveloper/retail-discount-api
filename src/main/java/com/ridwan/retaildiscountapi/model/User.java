package com.ridwan.retaildiscountapi.model;

import com.ridwan.retaildiscountapi.enums.UserType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author Ridwan Mustapha
 */
@Data
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private LocalDate createdDate;

    @OneToMany(mappedBy = "user")
    private Set<Bill> bills;
}
