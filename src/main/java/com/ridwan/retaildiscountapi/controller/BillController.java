package com.ridwan.retaildiscountapi.controller;

import com.ridwan.retaildiscountapi.entity.request.BillRequest;
import com.ridwan.retaildiscountapi.entity.response.BillResponse;
import com.ridwan.retaildiscountapi.service.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Ridwan Mustapha
 */
@RestController
@RequestMapping(path = {"api/v1"}, produces = APPLICATION_JSON_VALUE)
@Api(tags = {"Bill"}, value = "api/v1")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping(path = "/discount", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Calculate Net Payable Amount")
    public ResponseEntity<BillResponse> getNetAmount(@Valid @RequestBody BillRequest request) {
        return ResponseEntity.ok(billService.calculateNetAmount(request));
    }
}
