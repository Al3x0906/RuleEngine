package org.riskengine.request;

import lombok.Getter;
import lombok.Setter;
import org.riskengine.constants.TrxType;

import java.util.Map;

@Getter
@Setter
public class DebitRequest implements BaseRequest {


    Map<String, String> criteria;

    String txnId;

    TrxType type;



}
