package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveTrain;

public class ChangeDriveGryo extends InstantCommand {

  private DriveTrain driveTrain;

  public ChangeDriveGryo(DriveTrain driveTrain) {
    requires(driveTrain);
    this.driveTrain = driveTrain;
  }

  @Override
  protected void initialize() {
    driveTrain.useGyro = !driveTrain.useGyro;
  }

}
