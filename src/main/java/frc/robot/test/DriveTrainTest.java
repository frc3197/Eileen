package frc.robot.test;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap.DriveTrainSide;

public class DriveTrainTest extends CommandGroup {
  public DriveTrainTest() {
    addSequential(new DriveTrainRampTest(new double[] { 0, .2, 0 }, 4, DriveTrainSide.RIGHT));
    addSequential(new DriveTrainRampTest(new double[] { 0, .2, 0 }, 4, DriveTrainSide.LEFT));
  }
}
