package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Drive extends Command {

  public Drive() {
    requires(Robot.driveTrain);
  }

  @Override
  protected void execute() {
    if (Robot.driveTrain.arcadeDrive) {
      double y = Robot.oi.joystick.getY(Hand.kRight);
      double r = Robot.oi.joystick.getX(Hand.kLeft);
      Robot.driveTrain.arcadeDrive(y, r);
    } else {
      double l = Robot.oi.joystick.getY(Hand.kLeft);
      double r = Robot.oi.joystick.getY(Hand.kRight);
      Robot.driveTrain.tankDrive(l, r);
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.driveTrain.tankDrive(0, 0);
  }
}
