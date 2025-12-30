package org.riskengine.core.util;

import org.riskengine.core.factory.RuleFactoryRegistry;
import org.riskengine.core.model.Rule;
import org.riskengine.core.model.RuleDefinition;

import java.util.ArrayList;
import java.util.List;

public final class RuleLoader {

    private final RuleFactoryRegistry factoryRegistry;

    public RuleLoader(RuleFactoryRegistry factoryRegistry) {
        this.factoryRegistry = factoryRegistry;
    }

    public List<Rule> load(List<RuleDefinition> definitions) {
        List<Rule> rules = new ArrayList<>();
        for (RuleDefinition def : definitions) {
            rules.add(factoryRegistry.createRule(def));
        }
        return rules;
    }
}

