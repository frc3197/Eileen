package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.test.DriveTrainTest;

public class Robot extends TimedRobot {
  public static DriveTrain driveTrain = new DriveTrain();
  public static Elevator elevator = new Elevator();

  public static NetworkTableInstance ntInst = NetworkTableInstance.getDefault();
  public static NetworkTable table;

  public static OI oi = new OI();

  // public static DriveTrainTest driveTrainTest;

  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("rate", oi.gyro.getRate());
    SmartDashboard.putNumber("angle", oi.gyro.getAngle());
    driveTrain.update();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    // driveTrainTest = new DriveTrainTest();
    // driveTrainTest.start();
  }

  @Override
  public void testPeriodic() {

  }
}
