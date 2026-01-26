// Null Object Pattern, Null command
package com.carrental.CarRentalApp.command;
public class NullCommand implements Execution {
    @Override
    public void execute() {
        System.out.println("Dummy Execution, no Command here");
    }
}
