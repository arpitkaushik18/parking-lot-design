package org.example.service;

import org.example.exceptions.ParkingLotException;
import org.example.model.Car;
import org.example.model.ParkingLot;
import org.example.model.Slot;
import org.example.model.parking.strategy.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLotService {

    private ParkingStrategy parkingStrategy;
    private ParkingLot parkingLot;

    public void createParkingLot(final ParkingStrategy parkingStrategy, final ParkingLot parkingLot){
        if(this.parkingLot != null){
            throw new ParkingLotException("Parking lot is already there");
        }
        this.parkingLot =  parkingLot;
        this.parkingStrategy = parkingStrategy;

        for(Integer i = 1 ; i <= parkingLot.getCapacity(); i++){
            parkingStrategy.addSlot(i);
        }
    }

    public Integer park(final Car car){
         validateParkingLotExists();
         final Integer nextFreeSlot = parkingStrategy.getNextSlot();
         parkingLot.park(nextFreeSlot,car);
         parkingStrategy.removeSlot(nextFreeSlot);
         return nextFreeSlot;
    }

    public void makeSlotFree(Integer slotNumber){
        validateParkingLotExists();
        parkingLot.makeSlotFree(slotNumber);
        parkingStrategy.addSlot(slotNumber);
    }

    public List<Slot> getOccupiedSlots(){
        validateParkingLotExists();

        Map<Integer,Slot> availableSlots = parkingLot.getSlots();

        List<Slot> occupiedSlots = new ArrayList<Slot>();

        for(int i = 1 ; i <= parkingLot.getCapacity(); i++){
            if (availableSlots.containsKey(i)){
                Slot slot = availableSlots.get(i);
                if(!slot.isSlotFree()){
                    occupiedSlots.add(slot);
                }
            }
        }

        return occupiedSlots;
    }

    public List<Slot> getSlotForGivenColor(final String color){

        validateParkingLotExists();
        List<Slot> occupiedSlots = getOccupiedSlots();


        return occupiedSlots.stream()
                .filter(slot -> slot.getParkedCar().getColor().equals(color))
                .collect(Collectors.toList());
    }

    private void validateParkingLotExists() {
        if(parkingLot == null){
            throw new ParkingLotException("No parking lot exist");
        }
    }
}
