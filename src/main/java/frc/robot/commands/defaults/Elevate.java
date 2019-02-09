package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.Elevator;

public class Elevate extends Command {

  private Elevator elevator;

  public Elevate(Elevator elevator) {
    requires(elevator);
    this.elevator = elevator;
  }

  @Override
  protected void execute() {
    double speed = OI.elevatorSpeed();
    elevator.drive(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    elevator.drive(0);
  }
}
