package org.example.model;

import org.example.exceptions.InvalidSlotException;
import org.example.exceptions.OccupiedSlotException;
import org.example.exceptions.ParkingLotException;

import java.util.HashMap;
import java.util.Map;

/**
 * model class to represent parking lot's functioning
 */
public class ParkingLot {

    private static final Integer MAX_CAPACITY = 100;
    private final int capacity;
    Map<Integer, Slot> slots;

    public ParkingLot(final int capacity) {
        if( capacity > MAX_CAPACITY || capacity <= 0){
            throw new ParkingLotException("Invalid capacity given for parking lot.");
        }
        this.capacity = capacity;
        this.slots = new HashMap<Integer, Slot>();
    }

    public int getCapacity() {
        return capacity;
    }

    public Map<Integer, Slot> getSlots() {
        return slots;
    }

    private Slot getSlot(Integer slotNumber){

        if(slotNumber > capacity || slotNumber <= 0){
            throw new InvalidSlotException("Invalid slot number given for slot booking.");
        }

        Map<Integer,Slot> allSlots = getSlots();
        if(!allSlots.containsKey(slotNumber)){
            allSlots.put(slotNumber, new Slot(slotNumber));
        }

        return allSlots.get(slotNumber);
    }

    public Slot park(Integer slotNumber, Car car){

        Slot slot = getSlot(slotNumber);

        if(!slot.isSlotFree()){
            throw new OccupiedSlotException("No slots are free!!");
        }

        slot.assignCar(car);
        return slot;
    }

    public Slot makeSlotFree(Integer slotNumber){
        Slot slot = getSlot(slotNumber);
        slot.unassignCar();
        return slot;
    }


}
