package Practice.ParkingLot.strategy.parking;

import Practice.ParkingLot.entities.ParkingFloor;
import Practice.ParkingLot.entities.ParkingSpot;
import Practice.ParkingLot.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle);
}
