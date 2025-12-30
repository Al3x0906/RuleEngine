package org.riskengine.core.kernel;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class TransactionContext {
    long amount;
    long count;
    String type;
    Map<String, String> transactionInfo;
}
