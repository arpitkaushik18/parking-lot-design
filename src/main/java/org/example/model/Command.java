package org.example.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    private String commandName;
    private List<String> params;

    public Command(String inputLine) {
        List<String> tokenList = Arrays.stream(inputLine.trim().split(" "))
                .filter(token -> (token.length() > 0))
                .collect(Collectors.toList());

        commandName = tokenList.get(0).toLowerCase();
        tokenList.remove(0);
        params = tokenList;

    }

    public String getCommand() {
        return commandName;
    }

    public List<String> getParams() {
        return params;
    }
}
