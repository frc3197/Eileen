package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ChangeDriveWithGryo extends InstantCommand {

  public ChangeDriveWithGryo() {
    super();
    requires(Robot.driveTrain);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
  }

}
