package ParkingLot.strategy.fees;

import ParkingLot.entities.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket parkingTicket);
}
