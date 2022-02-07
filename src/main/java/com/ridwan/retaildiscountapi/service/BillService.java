package com.ridwan.retaildiscountapi.service;

import com.ridwan.retaildiscountapi.entity.request.BillRequest;
import com.ridwan.retaildiscountapi.entity.response.BillResponse;

/**
 * @author Ridwan Mustapha
 */
public interface BillService {
    BillResponse calculateNetAmount(BillRequest request);
}
