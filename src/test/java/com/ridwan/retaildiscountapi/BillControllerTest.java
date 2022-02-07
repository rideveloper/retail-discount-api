package com.ridwan.retaildiscountapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridwan.retaildiscountapi.controller.BillController;
import com.ridwan.retaildiscountapi.entity.request.BillRequest;
import com.ridwan.retaildiscountapi.enums.ItemType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BillController.class)
public class BillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Calculate Net Payable Amount Endpoint Test")
    void test_bill_discount() throws Exception {
        BillRequest request = new BillRequest();
        request.setUserId(1L);

        List<BillRequest.Item> itemList = new ArrayList<>();
        BillRequest.Item item1 = new BillRequest.Item(200.0, ItemType.CLOTHES, "Off-white", "18127");
        BillRequest.Item item3 = new BillRequest.Item(300.0, ItemType.GROCERY, "Pepper", "12127");
        BillRequest.Item item2 = new BillRequest.Item(1000.0, ItemType.ELECTRONICS, "iPhone 11", "18327");
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        request.setItems(itemList);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/discount")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}