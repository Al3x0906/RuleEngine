package org.riskengine.controllers;


import jakarta.validation.Valid;
import org.riskengine.kernel.RequestProcessor;
import org.riskengine.kernel.TrxCreateUser;
import org.riskengine.request.CreateProfileRequest;
import org.riskengine.response.BaseResponse;
import org.riskengine.response.CreateProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FinTransactionController {

    private final RequestProcessor processor;

    @Autowired
    public FinTransactionController(RequestProcessor requestProcessor) {
        this.processor = requestProcessor;
    }

    @PostMapping("/debit")
    private ResponseEntity<BaseResponse> checkDebitTransaction(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok().build();

    }

    @PostMapping("/createUser")
    private ResponseEntity<BaseResponse> createProfile(@RequestBody @Valid CreateProfileRequest request) throws Exception {
        CreateProfileResponse response = processor.exec(new TrxCreateUser(request));
        return ResponseEntity.ok(response);
    }

}
