package org.riskengine.core.factory;

import org.riskengine.core.constants.RuleKind;
import org.riskengine.core.model.Rule;
import org.riskengine.core.model.RuleDefinition;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class RuleFactoryRegistry {

    private final Map<RuleKind, RuleFactory> factories = new EnumMap<>(RuleKind.class);

    public RuleFactoryRegistry(List<RuleFactory> ruleFactories) {
        for (RuleFactory factory : ruleFactories) {
            factories.put(factory.supports(), factory);
        }
    }

    public Rule createRule(RuleDefinition definition) {
        RuleFactory factory = factories.get(definition.kind());
        if (factory == null) {
            throw new IllegalStateException("No factory for rule kind: " + definition.kind());
        }
        return factory.create(definition);
    }
}
