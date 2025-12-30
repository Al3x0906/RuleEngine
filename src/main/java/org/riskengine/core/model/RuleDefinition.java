package org.riskengine.core.model;

import org.riskengine.core.constants.RuleKind;

import java.util.Map;

public final class RuleDefinition {

    private final long ruleId;
    private final RuleKind kind;
    private final Map<String, Object> parameters;

    public RuleDefinition(long ruleId, RuleKind kind, Map<String, Object> parameters) {
        this.ruleId = ruleId;
        this.kind = kind;
        this.parameters = Map.copyOf(parameters);
    }

    public long ruleId() {
        return ruleId;
    }

    public RuleKind kind() {
        return kind;
    }

    public Map<String, Object> parameters() {
        return parameters;
    }
}
