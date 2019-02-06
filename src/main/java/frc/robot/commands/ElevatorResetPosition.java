package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Elevator;

public class ElevatorResetPosition extends InstantCommand {

    private Elevator elevator;

    public ElevatorResetPosition(Elevator elevator) {
        super();
        this.elevator = elevator;
        requires(elevator);
    }

    @Override
    protected void initialize() {
        elevator.resetElevatorPosition();
    }

}