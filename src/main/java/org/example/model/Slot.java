package org.example.model;

public class Slot {

    private final Integer slotNumber;
    private Car parkedCar;

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public Slot(final Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Car getParkedCar() {
        return parkedCar;
    }

    public void assignCar(Car parkedCar) {
        this.parkedCar = parkedCar;
    }
    public void unassignCar() {
        this.parkedCar = null;
    }

    public boolean isSlotFree() {
       return this.parkedCar == null;
    }

}
