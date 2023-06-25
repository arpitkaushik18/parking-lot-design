package org.example.commands;

import org.example.OutputPrinter;
import org.example.model.Car;
import org.example.model.Command;
import org.example.model.Slot;
import org.example.service.ParkingLotService;

import java.util.List;
import java.util.stream.Collectors;

public class RegToColorCommandExecutor extends CommandExecutor{

    public static String commandName = "registration_numbers_for_cars_with_colour";

    public RegToColorCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 1;
    }

    @Override
    public void execute(Command command) {

        List<Slot> slotList = parkingLotService.getSlotForGivenColor(command.getParams().get(0));

        if (slotList.isEmpty()) {
            outputPrinter.parkingLotEmpty();
        } else {

            String result = slotList.stream().map(slot -> slot.getParkedCar().getRegistrationNumber()).collect(Collectors.joining(","));
            outputPrinter.printWithNewLine(result);

        }

    }
}
