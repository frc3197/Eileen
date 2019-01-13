package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class Drive extends Command {
  public Drive() {
    requires(Robot.driveTrain);
  }

  @Override
  protected void execute() {
    double l = OI.joystick.getRawAxis(1);
    double r = OI.joystick.getRawAxis(5);
    // Robot.driveTrain.drive(l, r);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    // Robot.driveTrain.drive(0, 0);
  }
}
