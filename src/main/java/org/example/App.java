package org.example;

import org.example.commands.CommandExecutorFactory;
import org.example.mode.FileMode;
import org.example.service.ParkingLotService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {

        final OutputPrinter outputPrinter = new OutputPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();
        final CommandExecutorFactory commandExecutorFactory =
                new CommandExecutorFactory(parkingLotService);

            new FileMode(commandExecutorFactory, outputPrinter, args[0]).process();

    }
}
