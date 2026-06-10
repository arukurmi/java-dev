package Practice.ParkingLot.strategy.parking;

import Practice.ParkingLot.entities.ParkingFloor;
import Practice.ParkingLot.entities.ParkingSpot;
import Practice.ParkingLot.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public class NearestParkingStrategy implements ParkingStrategy{
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicle);
            if (spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();
    }
}
