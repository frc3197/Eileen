
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.BallManipulator;

public class Manipulate extends Command {
  BallManipulator manipulator;

  public Manipulate(BallManipulator manipulator) {
    requires(manipulator);
    this.manipulator = manipulator;
  }

  @Override
  protected void execute() {
    double speed = OI.manipulatorSpeed();
    manipulator.drive(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    manipulator.drive(0);
  }

}
