package org.example.exceptions;

public class OccupiedSlotException extends ParkingLotException{
    public OccupiedSlotException(String s) {
        super(s);
    }
}
