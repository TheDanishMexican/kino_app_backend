package kea.kinoBackend.project.pricing;

import kea.kinoBackend.project.seat.Seat;
import kea.kinoBackend.project.showing.Showing;

public class PriceManager {
    private PricingStrategy pricingStrategy;

    public PriceManager(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double getSeatPriceForShowing(Seat seat, Showing showing) {
        double basicSeatPrice = pricingStrategy.getBasicSeatPrice(seat, showing);
        double showingAdjustment = pricingStrategy.getShowingAdjustment(showing);
        return basicSeatPrice + showingAdjustment;
    }
}
