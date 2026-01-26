package com.carrental.CarRentalApp.command;
public class CommandExecutor {
    public static void execute(Execution command) {
        if (command == null) {
            command = new NullCommand();
        }
        command.execute();
    }
}