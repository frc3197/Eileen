package org.team3197.frc2019.robot.commands.defaults;

import edu.wpi.first.wpilibj.command.Command;
import org.team3197.frc2019.robot.OI;
import org.team3197.frc2019.robot.subsystems.DriveTrain;

public class Drive extends Command {

  private DriveTrain driveTrain;

  public Drive(DriveTrain driveTrain) {
    requires(driveTrain);
    this.driveTrain = driveTrain;
  }

  /**
   * Checks to see if the drive train is set to arcade or tank, and then takes the
   * speeds from OI to move the motors at.
   */
  @Override
  protected void execute() {
    double y = OI.arcadeDriveY();
    double r = OI.arcadeDriveR();
    driveTrain.drive(y, r);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    driveTrain.drive(0, 0);
  }
}
