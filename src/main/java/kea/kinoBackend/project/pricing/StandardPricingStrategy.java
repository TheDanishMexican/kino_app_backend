package kea.kinoBackend.project.pricing;

import kea.kinoBackend.project.seat.Seat;
import kea.kinoBackend.project.showing.Showing;

public class StandardPricingStrategy implements PricingStrategy {

    public double getBasicSeatPrice(Seat seat, Showing showing) {
        String seatType = String.valueOf(seat.getRow().getSeatType());
        double showingPrice = showing.getPrice();

        double pricePercent = switch (seatType) {
            case "COWBOY" -> 0.8;
            case "STANDARD" -> 1.0;
            case "COUCH" -> 1.2;
            default -> 0.0;
        };

        return showingPrice * pricePercent;
    }

    public double getShowingAdjustment(Showing showing) {
        if (showing.isIs3dMovie() || showing.isLongMovie()) {
            return 50.0;
        } else {
            return 0.0;
        }
    }
}
