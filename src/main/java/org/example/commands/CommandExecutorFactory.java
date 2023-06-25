package org.example.commands;

import org.example.OutputPrinter;
import org.example.model.Command;
import org.example.service.ParkingLotService;

import java.util.HashMap;

public class CommandExecutorFactory{

    private HashMap<String , CommandExecutor> commands = new HashMap<>();

    public CommandExecutorFactory(ParkingLotService parkingLotService) {
        OutputPrinter outputPrinter = new OutputPrinter();

        commands.put(
                CreateParkingLotCommandExecutor.commandName,
                new CreateParkingLotCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                ParkCommandExecutor.commandName,
                new ParkCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                LeaveCommandExecutor.commandName,
                new LeaveCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                StatusCommandExecutor.commandName,
                new StatusCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                RegToColorCommandExecutor.commandName,
                new RegToColorCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                SlotToColorCommandExecutor.commandName,
                new SlotToColorCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                SlotToRegCommandExecutor.commandName,
                new SlotToColorCommandExecutor(parkingLotService, outputPrinter));
        commands.put(
                ExitCommandExecutor.commandName,
                new ExitCommandExecutor(parkingLotService, outputPrinter));
    }

    public CommandExecutor getCommandExecutor(Command command) throws Exception {
        final CommandExecutor commandExecutor = commands.get(command.getCommand());

        if(commandExecutor == null){
            throw new Exception("Invalid command");
        }
        return commandExecutor;
    }
}
