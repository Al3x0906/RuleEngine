package org.riskengine.core.repositories;

import org.riskengine.core.model.RuleDefinition;

import java.util.List;

public interface RuleRepositoryInterface {
    List<RuleDefinition> fetchRules();
}
