package org.example.mode;

import org.example.OutputPrinter;
import org.example.commands.CommandExecutor;
import org.example.commands.CommandExecutorFactory;
import org.example.exceptions.ParkingLotException;
import org.example.model.Command;

import java.io.IOException;

public abstract class Mode {

    private final CommandExecutorFactory commandExecutorFactory;

    protected final OutputPrinter outputPrinter;

    public Mode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.outputPrinter = outputPrinter;
    }


   protected void processCommand(Command command) throws Exception {

       CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);

       if(!commandExecutor.validate(command)){
           throw new ParkingLotException("Invalid Command");
       }

       commandExecutor.execute(command);

    }

    public abstract void process() throws Exception;


}
