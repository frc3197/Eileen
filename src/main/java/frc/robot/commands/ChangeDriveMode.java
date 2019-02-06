package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ChangeDriveMode extends InstantCommand {

  public ChangeDriveMode() {
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() {
    Robot.driveTrain.arcadeDrive = !Robot.driveTrain.arcadeDrive;
  }

}
