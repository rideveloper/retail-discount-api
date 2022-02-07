package com.ridwan.retaildiscountapi.model;

import com.ridwan.retaildiscountapi.enums.ItemType;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * @author Ridwan Mustapha
 */
@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Double price;

    @Enumerated(EnumType.STRING)
    private ItemType type;

    private String name;
    private String code;

    @ManyToOne
    @JoinColumn(name="bill_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Bill bill;
}
