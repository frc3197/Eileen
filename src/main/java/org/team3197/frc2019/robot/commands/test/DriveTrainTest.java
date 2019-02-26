package org.team3197.frc2019.robot.commands.test;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team3197.frc2019.robot.RobotMap.DriveTrainSide;
import org.team3197.frc2019.robot.subsystems.DriveTrain;

public class DriveTrainTest extends CommandGroup {

  public DriveTrainTest(DriveTrain driveTrain) {
    addSequential(new DriveTrainRampTest(new double[] { 0, .2, 0 }, 4, DriveTrainSide.RIGHT, driveTrain));
    addSequential(new DriveTrainRampTest(new double[] { 0, .2, 0 }, 4, DriveTrainSide.LEFT, driveTrain));
  }
}
