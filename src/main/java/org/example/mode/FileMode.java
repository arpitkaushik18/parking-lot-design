package org.example.mode;

import org.example.OutputPrinter;
import org.example.commands.CommandExecutorFactory;
import org.example.model.Command;

import java.io.*;

public class FileMode extends Mode{

    private String fileName;
    public FileMode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter, String fileName) {
        super(commandExecutorFactory, outputPrinter);
        this.fileName = fileName;
    }

    @Override
    public void process() throws Exception {
        final File file = new File(fileName);

        final BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
        }catch (FileNotFoundException ex){
            outputPrinter.invalidFile();
            return;
        }

        String input =  reader.readLine();

        while (input != null){
            Command command = new Command(input);
            processCommand(command);
            input = reader.readLine();
        }

    }
}
