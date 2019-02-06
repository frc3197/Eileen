package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveTrain;

public class ChangeDriveMode extends InstantCommand {

  private DriveTrain driveTrain;

  public ChangeDriveMode(DriveTrain driveTrain) {
    requires(driveTrain);
    this.driveTrain = driveTrain;
  }

  @Override
  protected void initialize() {
    driveTrain.arcadeDrive = !driveTrain.arcadeDrive;
  }

}
