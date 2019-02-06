package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class Drive extends Command {

  private DriveTrain driveTrain;

  public Drive(DriveTrain driveTrain) {
    requires(driveTrain);
    this.driveTrain = driveTrain;
  }

  @Override
  protected void execute() {
    if (driveTrain.arcadeDrive) {
      double y = Robot.oi.joystick.getY(Hand.kRight);
      double r = -Robot.oi.joystick.getX(Hand.kLeft);
      driveTrain.arcadeDrive(y, r);
    } else {
      double l = Robot.oi.joystick.getY(Hand.kLeft);
      double r = Robot.oi.joystick.getY(Hand.kRight);
      driveTrain.tankDrive(l, r);
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    driveTrain.tankDrive(0, 0);
  }
}
