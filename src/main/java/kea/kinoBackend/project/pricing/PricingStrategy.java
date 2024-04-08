package kea.kinoBackend.project.pricing;

import kea.kinoBackend.project.seat.Seat;
import kea.kinoBackend.project.showing.Showing;

public interface PricingStrategy {
    double getBasicSeatPrice(Seat seat, Showing showing);
    double getShowingAdjustment(Showing showing);
}
