package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.test.DriveTrainTest;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.CargoManipulator;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Erector;
import frc.robot.subsystems.Hatch;

public class Robot extends TimedRobot {

  public static DriveTrain driveTrain = new DriveTrain();
  public static Elevator elevator = new Elevator();
  public static Arm arm = new Arm();
  public static CargoManipulator manipulator = new CargoManipulator();
  public static Hatch hatch = new Hatch();
  public static Erector erector = new Erector();

  public static DriveTrainTest driveTrainTest;

  public static NetworkTableInstance ntInst = NetworkTableInstance.getDefault();
  public static NetworkTable table;

  @Override
  public void robotInit() {
    // TODO test the reset on robotInit, ensure that the encoders stay at "home"
    // position through enable and disable.

    // elevator.reset.start();
    // arm.reset.start();

    CameraServer.getInstance().startAutomaticCapture();
  }

  @Override
  public void robotPeriodic() {
    driveTrain.update();
  }

  @Override
  public void disabledInit() {
    // elevator.reset.start();
    // arm.reset.start();
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
    arm.resetGyro.start();
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
