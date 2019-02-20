package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.RobotMap.ArmPreset;
import frc.robot.RobotMap.DeadbandType;
import frc.robot.RobotMap.ElevatorPreset;
import frc.robot.RobotMap.GyroSensitivity;
import frc.robot.RobotMap.MaxSpeeds;
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

  public static Preferences prefs = Preferences.getInstance();

  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
    driveTrain.update();

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
    ElevatorPreset.kCargoLevelThree.pos = prefs.getDouble("kCargoLevellThree", ElevatorPreset.kCargoLevelThree.pos);
    ElevatorPreset.kCargoLoadingLevel.pos = prefs.getDouble("kCargoLoadingLevel",
        ElevatorPreset.kCargoLoadingLevel.pos);
    ElevatorPreset.kCargoShipCargo.pos = prefs.getDouble("kCargoShipCargo", ElevatorPreset.kCargoShipCargo.pos);
    ElevatorPreset.kHatchLevelOneIntermediate.pos = prefs.getDouble("kHatchLevelOneIntermediate",
        ElevatorPreset.kHatchLevelOneIntermediate.pos);

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
    elevator.reset.start();
    arm.reset.start();
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
