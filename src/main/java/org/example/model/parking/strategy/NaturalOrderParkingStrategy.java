package org.example.model.parking.strategy;

import org.example.exceptions.InvalidSlotException;

import java.util.TreeSet;

public class NaturalOrderParkingStrategy implements ParkingStrategy{

    TreeSet<Integer> slotSet;

    public NaturalOrderParkingStrategy() {
        this.slotSet = new TreeSet<Integer>();
    }

    public void addSlot(Integer slotNumber) {
        this.slotSet.add(slotNumber);
    }

    public void removeSlot(Integer slotNumber) {
        this.slotSet.remove(slotNumber);
    }

    public Integer getNextSlot() {
        if(this.slotSet.isEmpty()){
            throw new InvalidSlotException("Slots are empty.");
        }
        return this.slotSet.first();
    }
}
