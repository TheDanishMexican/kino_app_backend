package kea.kinoBackend.project.pricing;

import kea.kinoBackend.project.seat.Seat;
import kea.kinoBackend.project.showing.Showing;

public class FlatPricingStrategy implements PricingStrategy{
    public double getBasicSeatPrice(Seat seat, Showing showing) {
        String seatType = String.valueOf(seat.getRow().getSeatType());
        double showingPrice = showing.getPrice();

        double pricePercent = switch (seatType) {
            case "COWBOY", "STANDARD", "COUCH" -> 1.0;
            default -> 0.0;
        };

        return showingPrice * pricePercent;
    }

    public double getShowingAdjustment(Showing showing) {
        return 0.0;
    }
}
