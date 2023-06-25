package org.example.commands;

import org.example.OutputPrinter;
import org.example.exceptions.ParkingLotException;
import org.example.model.Car;
import org.example.model.Command;
import org.example.model.ParkingLot;
import org.example.service.ParkingLotService;

public class ParkCommandExecutor extends CommandExecutor{

    public static String commandName = "park";

    public ParkCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 2;
    }

    @Override
    public void execute(Command command) {

        Car car = new Car(command.getParams().get(0), command.getParams().get(1));
        try {
            Integer Slot = parkingLotService.park(car);
            outputPrinter.printWithNewLine("Car Parked in Slot number " + Slot);
        }
        catch (ParkingLotException ex){
            outputPrinter.parkingLotFull();
        }

    }
}
