package com.ridwan.retaildiscountapi.service.impl;

import com.ridwan.retaildiscountapi.entity.request.BillRequest;
import com.ridwan.retaildiscountapi.entity.response.BillResponse;
import com.ridwan.retaildiscountapi.enums.UserType;
import com.ridwan.retaildiscountapi.model.Bill;
import com.ridwan.retaildiscountapi.model.Item;
import com.ridwan.retaildiscountapi.repo.BillRepo;
import com.ridwan.retaildiscountapi.repo.ItemRepo;
import com.ridwan.retaildiscountapi.service.BillService;
import com.ridwan.retaildiscountapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ridwan Mustapha
 */
@Service
@Slf4j
public class BillServiceImpl implements BillService {

    private final UserService userService;
    private final BillRepo billRepo;
    private final ItemRepo itemRepo;

    public BillServiceImpl(UserService userService, BillRepo billRepo, ItemRepo itemRepo) {
        this.userService = userService;
        this.billRepo = billRepo;
        this.itemRepo = itemRepo;
    }

    @Override
    public BillResponse calculateNetAmount(BillRequest request) {
        BillResponse response = new BillResponse();
        Bill bill = new Bill();
        bill.setUser(userService.getUser(request.getUserId()));

        Double netAmount = 0.0;

        int percentage = getUserDiscountPercentage(request.getUserId());
        Double amount = calculateItemTotal(request.getItems());

        if (percentage == 0) {
            netAmount = amount;
            response.setAmount(amount);
        } else {

            Double discount = ((amount * percentage) / 100);
            netAmount = amount - discount;
            response.setAmount(netAmount);
        }

        bill.setAmount(amount);
        bill.setNetAmount(netAmount);

        bill = billRepo.saveAndFlush(bill);
        saveBillItems(bill, request.getItems());

        log.info("response : "+response);
        return response;
    }

    private int getUserDiscountPercentage(Long userId) {
        UserType userType = userService.getUserType(userId);
        int percentage = 0;
        switch (userType) {
            case EMPLOYEE:
                percentage = 30;
                break;
            case AFFILIATE:
                percentage =  10;
                break;
            case CUSTOMER:
                if (userService.isCustomerValidForDiscount(userId)) {
                    percentage =  5;
                }
                break;
        }

        return percentage;
    }

    private Double calculateItemTotal(List<BillRequest.Item> items) {
        Double total = 0.0;
        for (BillRequest.Item item: items) {
            total += item.getPrice();
        }

        return addItemDiscount(total);
    }

    private Double addItemDiscount(double total) {
        int x = (int) total / 100;
        int y = x * 5;

        return total - y;
    }

    private void saveBillItems(Bill bill, List<BillRequest.Item> items) {
        for (BillRequest.Item item : items) {
            Item itemModel = new Item();
            itemModel.setBill(bill);
            itemModel.setCode(item.getCode());
            itemModel.setName(item.getName());
            itemModel.setPrice(item.getPrice());
            itemModel.setType(item.getType());

            itemRepo.saveAndFlush(itemModel);
        }
    }
}
