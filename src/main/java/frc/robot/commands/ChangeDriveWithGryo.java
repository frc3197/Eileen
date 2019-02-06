package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveTrain;

public class ChangeDriveWithGryo extends InstantCommand {

  private DriveTrain driveTrain;

  public ChangeDriveWithGryo(DriveTrain driveTrain) {
    requires(driveTrain);
    this.driveTrain = driveTrain;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    driveTrain.useGyro = !driveTrain.useGyro;
  }

}
