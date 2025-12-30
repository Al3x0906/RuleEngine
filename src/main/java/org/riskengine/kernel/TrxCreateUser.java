package org.riskengine.kernel;

import org.riskengine.request.CreateProfileRequest;
import org.riskengine.response.CreateProfileResponse;

public class TrxCreateUser extends Trx {

    private final String trxId;
    private final String name;
    private final String mobile;


    private final CreateProfileResponse response;

    public TrxCreateUser(CreateProfileRequest request) {
        this.trxId = request.getTxnId();
        this.name = request.getName();
        this.mobile = request.getMobile();
        this.response = new CreateProfileResponse();
    }

    @Override
    public CreateProfileResponse call() throws Exception {

        return response;
    }
}
