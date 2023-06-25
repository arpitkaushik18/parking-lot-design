package org.example.commands;

import org.example.OutputPrinter;
import org.example.model.Car;
import org.example.model.Command;
import org.example.model.Slot;
import org.example.service.ParkingLotService;

import java.util.List;

public class StatusCommandExecutor extends CommandExecutor{

    public static String commandName = "status";
    public StatusCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 0;
    }

    @Override
    public void execute(Command command) {

        List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();

        outputPrinter.statusHeader();

        for (Slot slot : occupiedSlots){

            Car parkedCar = slot.getParkedCar();
            final String slotNumber = slot.getSlotNumber().toString();

            outputPrinter.printWithNewLine(padString(slotNumber, 12)
                    + padString(parkedCar.getRegistrationNumber(), 19) + parkedCar.getColor());

        }

    }

    private static String padString(final String word, final int length) {
        String newWord = word;
        for(int count = word.length(); count < length; count++) {
            newWord = newWord + " ";
        }
        return newWord;
    }
}
