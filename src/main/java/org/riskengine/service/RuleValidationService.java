package org.riskengine.service;

import jakarta.annotation.PostConstruct;
import org.riskengine.core.ValidationEngine;
import org.riskengine.core.factory.RuleFactoryRegistry;
import org.riskengine.core.factory.SimpleRuleFactory;
import org.riskengine.core.kernel.ValidationContext;
import org.riskengine.core.model.Rule;
import org.riskengine.core.model.RuleDefinition;
import org.riskengine.core.repositories.RuleRepositoryInterface;
import org.riskengine.core.util.RuleLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleValidationService {


    RuleRepositoryInterface ruleRepository;
    ValidationEngine engine;

    @Autowired
    RuleValidationService(RuleRepositoryInterface ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @PostConstruct
    public void init() {

        RuleFactoryRegistry registry =
                new RuleFactoryRegistry(List.of(
                        new SimpleRuleFactory()
                ));

        RuleLoader loader = new RuleLoader(registry);

        List<RuleDefinition> definitions = ruleRepository.fetchRules();

        List<Rule> rules = loader.load(definitions);

        engine = new ValidationEngine(rules);


    }


    public boolean validate(ValidationContext validationContext) {
        return engine.validate(validationContext);
    }


}
