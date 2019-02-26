package org.team3197.frc2019.robot;

import org.team3197.frc2019.robot.RobotMap.ArmPreset;
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
    // TODO test the reset on robotInit(), ensure that the encoders stay at "home"
    // position through enable and disable

    // elevator.reset.start();
    // arm.reset.start();
    CameraServer.getInstance().startAutomaticCapture();
  }

  @Override
  public void robotPeriodic() {
    ArmPreset.kHatchOne.wristPos = prefs.getDouble("kHatchLevelOne", ArmPreset.kHatchOne.wristPos);
    ArmPreset.kHatchTwo.wristPos = prefs.getDouble("kHatchLevelTwo", ArmPreset.kHatchTwo.wristPos);
    ArmPreset.kHatchThree.wristPos = prefs.getDouble("kHatchLevelThree", ArmPreset.kHatchThree.wristPos);
    ArmPreset.kCargoOne.wristPos = prefs.getDouble("kCargoOne", ArmPreset.kCargoOne.wristPos);
    ArmPreset.kCargoTwo.wristPos = prefs.getDouble("kCargoTwo", ArmPreset.kCargoTwo.wristPos);
    ArmPreset.kCargoThree.wristPos = prefs.getDouble("kCargoThree", ArmPreset.kCargoThree.wristPos);
    ArmPreset.kCargoShip.wristPos = prefs.getDouble("kCargoShip", ArmPreset.kCargoShip.wristPos);

    ArmPreset.kHatchOne.elbowPos = prefs.getDouble("kHatchLevelOne", ArmPreset.kHatchOne.elbowPos);
    ArmPreset.kHatchTwo.elbowPos = prefs.getDouble("kHatchLevelTwo", ArmPreset.kHatchTwo.elbowPos);
    ArmPreset.kHatchThree.elbowPos = prefs.getDouble("kHatchLevelThree", ArmPreset.kHatchThree.elbowPos);
    ArmPreset.kCargoOne.elbowPos = prefs.getDouble("kCargoOne", ArmPreset.kCargoOne.elbowPos);
    ArmPreset.kCargoTwo.elbowPos = prefs.getDouble("kCargoTwo", ArmPreset.kCargoTwo.elbowPos);
    ArmPreset.kCargoThree.elbowPos = prefs.getDouble("kCargoThree", ArmPreset.kCargoThree.elbowPos);
    ArmPreset.kCargoShip.elbowPos = prefs.getDouble("kCargoShip", ArmPreset.kCargoShip.elbowPos);

    ElevatorPreset.kHatchLevelThree.pos = prefs.getDouble("kHatchLevelThree", ElevatorPreset.kHatchLevelThree.pos);
    ElevatorPreset.kCargoLevelOne.pos = prefs.getDouble("kCargoLevelOne", ElevatorPreset.kCargoLevelOne.pos);
    ElevatorPreset.kCargoLevelTwo.pos = prefs.getDouble("kCargoLevelTwo", ElevatorPreset.kCargoLevelTwo.pos);
    ElevatorPreset.kCargoLevelThree.pos = prefs.getDouble("kCargoLevelThree", ElevatorPreset.kCargoLevelThree.pos);
    ElevatorPreset.kCargoLoadingLevel.pos = prefs.getDouble("kCargoLoadingLevel",
        ElevatorPreset.kCargoLoadingLevel.pos);
    ElevatorPreset.kCargoShipCargo.pos = prefs.getDouble("kCargoShipCargo", ElevatorPreset.kCargoShipCargo.pos);
    ElevatorPreset.kLevelOneIntermediate.pos = prefs.getDouble("kHatchLevelOneIntermediate",
        ElevatorPreset.kLevelOneIntermediate.pos);

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
    if (resetEncoders) {
      elevator.reset.start();
      arm.reset.start();
      arm.resetGyro.start();
      resetEncoders = false;
    }
  }
}
