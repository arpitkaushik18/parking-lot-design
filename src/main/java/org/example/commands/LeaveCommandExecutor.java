package org.example.commands;

import org.example.OutputPrinter;
import org.example.model.Command;
import org.example.service.ParkingLotService;

public class LeaveCommandExecutor extends CommandExecutor{

    public static String commandName = "leave";

    public LeaveCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) {

        parkingLotService.makeSlotFree(Integer.valueOf(command.getParams().get(0)));
        outputPrinter.printWithNewLine("Slot number " + Integer.valueOf(command.getParams().get(0))  + " is free");


    }
}
