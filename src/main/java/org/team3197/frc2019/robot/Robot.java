package org.team3197.frc2019.robot;

import org.team3197.frc2019.robot.RobotMap.ElbowPreset;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.RobotMap.ElevatorPreset;
import org.team3197.frc2019.robot.RobotMap.GyroSensitivity;
import org.team3197.frc2019.robot.RobotMap.MaxSpeeds;
import org.team3197.frc2019.robot.commands.test.DriveTrainTest;
import org.team3197.frc2019.robot.subsystems.Arm;
import org.team3197.frc2019.robot.subsystems.CargoManipulator;
import org.team3197.frc2019.robot.subsystems.DriveTrain;
import org.team3197.frc2019.robot.subsystems.Elevator;
import org.team3197.frc2019.robot.subsystems.Erector;
import org.team3197.frc2019.robot.subsystems.Hatch;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {

  public static DriveTrain driveTrain = new DriveTrain();
  public static Elevator elevator = new Elevator();
  public static Arm arm = new Arm();
  public static CargoManipulator manipulator = new CargoManipulator();
  public static Hatch hatch = new Hatch();
  public static Erector erector = new Erector();

  public static PowerDistributionPanel pdp = new PowerDistributionPanel(0);

  public static DriveTrainTest driveTrainTest;

  public static NetworkTableInstance ntInst = NetworkTableInstance.getDefault();
  public static NetworkTable table;

  public static Preferences prefs = Preferences.getInstance();

  private static boolean resetEncoders = true;

  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
    ElbowPreset.kHatchOne.elbowPos = prefs.getDouble("kHatchLevelOne", ElbowPreset.kHatchOne.elbowPos);
    ElbowPreset.kHatchTwo.elbowPos = prefs.getDouble("kHatchLevelTwo", ElbowPreset.kHatchTwo.elbowPos);
    ElbowPreset.kHatchThree.elbowPos = prefs.getDouble("kHatchLevelThree", ElbowPreset.kHatchThree.elbowPos);
    ElbowPreset.kCargoOne.elbowPos = prefs.getDouble("kCargoOne", ElbowPreset.kCargoOne.elbowPos);
    ElbowPreset.kCargoTwo.elbowPos = prefs.getDouble("kCargoTwo", ElbowPreset.kCargoTwo.elbowPos);
    ElbowPreset.kCargoThree.elbowPos = prefs.getDouble("kCargoThree", ElbowPreset.kCargoThree.elbowPos);
    ElbowPreset.kCargoShip.elbowPos = prefs.getDouble("kCargoShip", ElbowPreset.kCargoShip.elbowPos);

    ElevatorPreset.kHatchLevelThree.pos = prefs.getDouble("kHatchLevelThree", ElevatorPreset.kHatchLevelThree.pos);
    ElevatorPreset.kCargoLevelOne.pos = prefs.getDouble("kCargoLevelOne", ElevatorPreset.kCargoLevelOne.pos);
    ElevatorPreset.kCargoLevelTwo.pos = prefs.getDouble("kCargoLevelTwo", ElevatorPreset.kCargoLevelTwo.pos);
    ElevatorPreset.kCargoLevelThree.pos = prefs.getDouble("kCargoLevelThree", ElevatorPreset.kCargoLevelThree.pos);
    ElevatorPreset.kCargoLoadingLevel.pos = prefs.getDouble("kCargoLoadingLevel",
        ElevatorPreset.kCargoLoadingLevel.pos);
    ElevatorPreset.kCargoShipCargo.pos = prefs.getDouble("kCargoShipCargo", ElevatorPreset.kCargoShipCargo.pos);

    DeadbandType.kElevator.speed = prefs.getDouble("kElevatorDeadband", DeadbandType.kElevator.speed);
    DeadbandType.kElbow.speed = prefs.getDouble("kElbowDeadband", DeadbandType.kElbow.speed);
    DeadbandType.kWrist.speed = prefs.getDouble("kWristDeadband", DeadbandType.kWrist.speed);
    DeadbandType.kDrive.speed = prefs.getDouble("kDriveDeadband", DeadbandType.kDrive.speed);

    GyroSensitivity.kDrive.val = prefs.getDouble("kDriveSensitivity", GyroSensitivity.kDrive.val);
    GyroSensitivity.kArm.val = prefs.getDouble("kArmSensitivity", GyroSensitivity.kArm.val);

    MaxSpeeds.kElevator.forwardSpeed = prefs.getDouble("kElevatorSpeedUp", MaxSpeeds.kElevator.forwardSpeed);
    MaxSpeeds.kElevator.reverseSpeed = prefs.getDouble("kElevatorSpeedDown", MaxSpeeds.kElevator.reverseSpeed);
    MaxSpeeds.kHatch.forwardSpeed = prefs.getDouble("kHatchSpeed", MaxSpeeds.kHatch.forwardSpeed);
    MaxSpeeds.kCargo.forwardSpeed = prefs.getDouble("kCargoSpeedUp", MaxSpeeds.kCargo.forwardSpeed);
    MaxSpeeds.kCargo.reverseSpeed = prefs.getDouble("kCargoSpeedDown", MaxSpeeds.kCargo.reverseSpeed);
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
    reset();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    reset();
    // arm.reset.start();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    reset();
    // driveTrainTest = new DriveTrainTest();
    // driveTrainTest.start();
  }

  @Override
  public void testPeriodic() {

  }

  private void reset() {
    arm.resetGyro.start();
    if (resetEncoders) {
      elevator.reset.start();
      arm.reset.start();
      resetEncoders = false;
    }
  }
}
