package org.riskengine.core.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Strings;
import org.riskengine.core.kernel.ValidationContext;

import java.util.Map;

@Getter
@Setter
public class SimpleRule implements StatefulRule {


    long id;
    Map<String, String> criteria;

    public SimpleRule(long id, Map<String, Object> parameters) {
        this.id = id;

        if (parameters.get("CRITERIA") instanceof Map<String, String> params){
            this.criteria = params;
        }
    }


    /*
        Sample implementation of a rule
     */
    @Override
    public boolean evaluate(ValidationContext context) {

        Map<String, String> trxInfo = context.getTransactionContext().getTransactionInfo();

        if (criteria != null) {
            for (var e : criteria.entrySet()) {
                if (!Strings.CS.equals(e.getValue(), trxInfo.get(e.getKey()))) {
                    return false;
                }
            }
        }
        return true;
    }
}
