package service;

import model.TravelRequest;
import model.VisaDecision;
import model.VisaRule;
import repository.RuleRepository;

public class VisaEvaluator {

    private final RuleRepository ruleRepository;

    public VisaEvaluator(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public VisaDecision evaluate(TravelRequest request) {

        for (VisaRule rule : ruleRepository.getRules()) {
            if (rule.matches(request)) {
                return new VisaDecision(
                        rule.isVisaRequired(),
                        rule.getVisaType(),
                        rule.getWarnings()
                );
            }
        }

        throw new IllegalStateException("No matching visa rule found");
    }
}
