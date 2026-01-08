package model;

import java.util.List;
import java.util.Optional;

public class VisaRule {

    private final Optional<Country> destinationCountry;
    private final Optional<TravelPurpose> travelPurpose;
    private final Optional<Integer> minStayDays;


    private final boolean visaRequired;
    private final VisaType visaType;
    private final List<String> warnings;

    public VisaRule(
            Optional<Country> destinationCountry,
            Optional<TravelPurpose> travelPurpose,
            Optional<Integer> minStayDays,
            boolean visaRequired,
            VisaType visaType,
            List<String> warnings
    ) {
        this.destinationCountry = destinationCountry;
        this.travelPurpose = travelPurpose;
        this.minStayDays = minStayDays;
        this.visaRequired = visaRequired;
        this.visaType = visaType;
        this.warnings = warnings;
    }

    public Optional<Country> getDestinationCountry() {
        return destinationCountry;
    }

    public Optional<TravelPurpose> getTravelPurpose() {
        return travelPurpose;
    }

    public Optional<Integer> getMinStayDays() {
        return minStayDays;
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

    public boolean matches(TravelRequest request) {

        if (destinationCountry.isPresent()
                && destinationCountry.get() != request.getDestinationCountry()) {
            return false;
        }

        if (travelPurpose.isPresent()
                && travelPurpose.get() != request.getTravelPurpose()) {
            return false;
        }

        if (minStayDays.isPresent()
                && request.getStayDurationDays() < minStayDays.get()) {
            return false;
        }

        return true;
    }

}

