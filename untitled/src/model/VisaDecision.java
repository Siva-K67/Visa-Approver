package model;

import java.util.List;

public class VisaDecision {

    private final boolean visaRequired;
    private final VisaType visaType;
    private final List<String> warnings;

    public VisaDecision(
            boolean visaRequired,
            VisaType visaType,
            List<String> warnings
    ) {
        this.visaRequired = visaRequired;
        this.visaType = visaType;
        this.warnings = warnings;
    }

    public boolean isVisaRequired() {
        return visaRequired;
    }

    public VisaType getVisaType() {
        return visaType;
    }

    public List<String> getWarnings() {
        return warnings;
    }
}
