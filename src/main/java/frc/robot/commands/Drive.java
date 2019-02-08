package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
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
      double y = OI.driver.getY(Hand.kRight);
      double r = -OI.driver.getX(Hand.kLeft);
      driveTrain.arcadeDrive(y, r);
    } else {
      double l = OI.driver.getY(Hand.kLeft);
      double r = OI.driver.getY(Hand.kRight);
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
