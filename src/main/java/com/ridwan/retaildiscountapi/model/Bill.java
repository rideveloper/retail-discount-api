package com.ridwan.retaildiscountapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author Ridwan Mustapha
 */
@Data
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

//    @OneToMany(mappedBy = "bill")
//    private List<Item> items;

    private Double amount;
    private Double netAmount;
}
