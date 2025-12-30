package org.riskengine.core.factory;

import org.riskengine.core.constants.RuleKind;
import org.riskengine.core.model.Rule;
import org.riskengine.core.model.RuleDefinition;

public interface RuleFactory {
    RuleKind supports();

    Rule create(RuleDefinition ruleDefinition);

}
