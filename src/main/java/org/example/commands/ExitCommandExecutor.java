package org.example.commands;

import org.example.OutputPrinter;
import org.example.model.Command;
import org.example.service.ParkingLotService;

public class ExitCommandExecutor extends CommandExecutor{

    public static final String commandName = "exit";
    public ExitCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().isEmpty();
    }

    @Override
    public void execute(Command command) {
        outputPrinter.end();
    }
}
