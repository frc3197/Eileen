package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
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
      double y = OI.joystick.getY(Hand.kRight);
      double r = -OI.joystick.getX(Hand.kLeft);
      driveTrain.arcadeDrive(y, r);
    } else {
      double l = OI.joystick.getY(Hand.kLeft);
      double r = OI.joystick.getY(Hand.kRight);
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
