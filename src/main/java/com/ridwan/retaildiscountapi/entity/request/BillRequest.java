package com.ridwan.retaildiscountapi.entity.request;

import com.ridwan.retaildiscountapi.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Ridwan Mustapha
 */
@Data
public class BillRequest {
    private Long userId;
    private List<Item> items;

    @Data
    @AllArgsConstructor
    public static class Item {
        private Double price;
        private ItemType type;
        private String name;
        private String code;
    }
}
