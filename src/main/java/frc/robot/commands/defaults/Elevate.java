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

  /**
   * Takes the input from the triggers by subtracting the input of the left from
   * the right and moves the elevator accordingly.
   */
  @Override
  protected void execute() {
    double speed = OI.elevatorSpeed();
    elevator.drive(speed, true);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    elevator.drive(0, true);
  }
}
