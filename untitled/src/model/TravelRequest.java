package model;

public class TravelRequest {

    private final Country destinationCountry;
    private final Country passportCountry;
    private final TravelPurpose travelPurpose;
    private final int stayDurationDays;

    public TravelRequest(
            Country destinationCountry,
            Country passportCountry,
            TravelPurpose travelPurpose,
            int stayDurationDays
    ) {
        this.destinationCountry = destinationCountry;
        this.passportCountry = passportCountry;
        this.travelPurpose = travelPurpose;
        this.stayDurationDays = stayDurationDays;
    }

    public Country getDestinationCountry() {
        return destinationCountry;
    }

    public Country getPassportCountry() {
        return passportCountry;
    }

    public TravelPurpose getTravelPurpose() {
        return travelPurpose;
    }

    public int getStayDurationDays() {
        return stayDurationDays;
    }
}
