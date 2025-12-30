package org.riskengine.core.kernel;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationContext {
    private final ProfileContext profileContext;
    private final TransactionContext transactionContext;

    public ValidationContext(ProfileContext profileContext, TransactionContext transactionContext) {
        this.profileContext = profileContext;
        this.transactionContext = transactionContext;
    }


}
