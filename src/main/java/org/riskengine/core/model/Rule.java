package org.riskengine.core.model;

import org.riskengine.core.kernel.ValidationContext;

public interface Rule {

    boolean evaluate(ValidationContext context);

}
