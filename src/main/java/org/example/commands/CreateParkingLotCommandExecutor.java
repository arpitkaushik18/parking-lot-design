package org.example.commands;

import org.example.OutputPrinter;
import org.example.model.Command;
import org.example.model.ParkingLot;
import org.example.model.parking.strategy.NaturalOrderParkingStrategy;
import org.example.service.ParkingLotService;
import org.example.validator.IntegerValidator;

import java.util.List;

public class CreateParkingLotCommandExecutor extends CommandExecutor {

    public static final String commandName = "create_parking_lot";

    public CreateParkingLotCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService,outputPrinter);
    }

    @Override
    public boolean validate(Command command) {

        List<String> params = command.getParams();

        if(params.size() != 1 ){
            return false;
        }

        return IntegerValidator.validateInteger(params.get(0));

    }

    @Override
    public void execute(Command command) {

        final int parkingLotCapacity = Integer.parseInt(command.getParams().get(0));

        ParkingLot parkingLot = new ParkingLot(parkingLotCapacity);

        parkingLotService.createParkingLot(new NaturalOrderParkingStrategy(), parkingLot);
        outputPrinter.printWithNewLine(
                "Created a parking lot with " + parkingLot.getCapacity() + " slots");

    }
}
