package Practice.ParkingLot.strategy.fees;

import Practice.ParkingLot.entities.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket parkingTicket);
}
