package repository;

import model.VisaRule;

import java.util.Collections;
import java.util.List;

public class RuleRepository {

    private final List<VisaRule> rules;

    public RuleRepository(List<VisaRule> rules) {
        this.rules = rules;
    }

    public List<VisaRule> getRules() {
        return Collections.unmodifiableList(rules);
    }
}
