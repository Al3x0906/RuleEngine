package org.riskengine.core.factory;

import org.riskengine.core.constants.RuleKind;
import org.riskengine.core.model.Rule;
import org.riskengine.core.model.RuleDefinition;
import org.riskengine.core.model.SimpleRule;

public class SimpleRuleFactory implements RuleFactory{
    @Override
    public RuleKind supports() {
        return RuleKind.VELOCITY;
    }

    @Override
    public Rule create(RuleDefinition definition) {
        return new SimpleRule(definition.ruleId(), definition.parameters());
    }
}
