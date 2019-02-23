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

  private static boolean resetEncoders = true;

  @Override
  public void robotInit() {
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
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    resetEncoders();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    resetEncoders();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    // driveTrainTest = new DriveTrainTest();
    // driveTrainTest.start();
    resetEncoders();
  }

  @Override
  public void testPeriodic() {

  }

  private void resetEncoders() {
    if (resetEncoders) {
      elevator.reset.start();
      arm.reset.start();
      resetEncoders = false;
    }
  }
}
