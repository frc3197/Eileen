package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class Drive extends Command {

  public Drive() {
    requires(Robot.driveTrain);
  }

  @Override
  protected void execute() {
    if (Robot.driveTrain.arcadeDrive) {
      double y = OI.joystick.getY(Hand.kRight);
      double r = OI.joystick.getX(Hand.kLeft);
      Robot.driveTrain.arcadeDrive(y, r);
    } else {
      double l = OI.joystick.getY(Hand.kLeft);
      double r = OI.joystick.getY(Hand.kRight);
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
