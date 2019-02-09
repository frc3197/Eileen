package frc.robot.commands.test;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap.DriveTrainSide;
import frc.robot.subsystems.DriveTrain;

public class DriveTrainTest extends CommandGroup {

  public DriveTrainTest(DriveTrain driveTrain) {
    addSequential(new DriveTrainRampTest(new double[] { 0, .2, 0 }, 4, DriveTrainSide.RIGHT, driveTrain));
    addSequential(new DriveTrainRampTest(new double[] { 0, .2, 0 }, 4, DriveTrainSide.LEFT, driveTrain));
  }
}
