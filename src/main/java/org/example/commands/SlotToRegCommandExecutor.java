package org.example.commands;

import org.example.OutputPrinter;
import org.example.model.Command;
import org.example.model.Slot;
import org.example.service.ParkingLotService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SlotToRegCommandExecutor extends CommandExecutor{

    public static String commandName = "slot_number_for_registration_number";

    public SlotToRegCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    @Override
    public boolean validate(Command command) {
        return command.getParams().isEmpty();
    }

    @Override
    public void execute(Command command) {

        List<Slot> slotList = parkingLotService.getOccupiedSlots();

        if (slotList.isEmpty()) {
            outputPrinter.parkingLotEmpty();
        } else {

            Optional<Slot> foundSlot = slotList.stream().filter(slot -> slot.getParkedCar().getRegistrationNumber().equals(command.getParams().get(0))).findFirst();

            if (foundSlot.isPresent()) {
                outputPrinter.printWithNewLine(foundSlot.get().getSlotNumber().toString());
            } else {
                outputPrinter.notFound();
            }

        }

    }
}
