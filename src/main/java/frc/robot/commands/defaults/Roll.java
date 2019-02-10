package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CargoManipulator;

public class Roll extends Command {

    private CargoManipulator manipulator;
    private double speed;

    public Roll(CargoManipulator manipulator, double speed) {
        requires(manipulator);
        this.manipulator = manipulator;
        this.speed = speed;
    }

    @Override
    protected void execute() {
        manipulator.drive(speed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }
}
