package org.riskengine.core;

import org.riskengine.core.kernel.ValidationContext;
import org.riskengine.core.model.Rule;
import org.riskengine.core.model.StatefulRule;

import java.util.ArrayList;
import java.util.List;

public final class ValidationEngine {

    private final List<Rule> rules;

    public ValidationEngine(List<Rule> rules) {
        this.rules = List.copyOf(rules);
    }

    public boolean validate(ValidationContext context) {

        List<StatefulRule> appliedStatefulRules = new ArrayList<>();

        for (Rule rule : rules) {
            boolean result = rule.evaluate(context);

            if (!result) {
                rollback(appliedStatefulRules, context);
                return false;
            }

            if (rule instanceof StatefulRule statefulRule) {
                appliedStatefulRules.add(statefulRule);
            }
        }

        commit(appliedStatefulRules, context);
        return true;
    }

    private void commit(List<StatefulRule> rules, ValidationContext context) {
        rules.forEach(r -> r.commit(context));
    }

    private void rollback(List<StatefulRule> rules, ValidationContext context) {
        rules.forEach(r -> r.rollback(context));
    }
}
