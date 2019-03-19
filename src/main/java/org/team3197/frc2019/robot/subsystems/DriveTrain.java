package org.team3197.frc2019.robot.subsystems;

import java.util.HashMap;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.CANSparkMaxID;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.commands.defaults.Drive;
import org.team3197.frc2019.robot.utilities.FunctionCommand;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

  public boolean arcadeDrive = true;
  public boolean useGyro = false;

  // public AnalogGyro gyro = new AnalogGyro(Channel.kDriveGyro.channel);

  // Motor Controllers
  private CANSparkMax flSparkMax = new CANSparkMax(CANSparkMaxID.kFrontLeft.id, MotorType.kBrushless);
  private CANSparkMax blSparkMax = new CANSparkMax(CANSparkMaxID.kBackLeft.id, MotorType.kBrushless);
  private CANSparkMax frSparkMax = new CANSparkMax(CANSparkMaxID.kFrontRight.id, MotorType.kBrushless);
  private CANSparkMax brSparkMax = new CANSparkMax(CANSparkMaxID.kBackRight.id, MotorType.kBrushless);

  private SpeedControllerGroup leftMaxes = new SpeedControllerGroup(flSparkMax, blSparkMax);
  private SpeedControllerGroup rightMaxes = new SpeedControllerGroup(frSparkMax, brSparkMax);

  private DifferentialDrive drive = new DifferentialDrive(leftMaxes, rightMaxes);

  public FunctionCommand changeDriveGryo = new FunctionCommand(this::toggleGyro);
  public FunctionCommand changeDriveMode = new FunctionCommand(this::toggleMode);

  public DriveTrain() {
    super();

    flSparkMax.setIdleMode(IdleMode.kBrake);
    blSparkMax.setIdleMode(IdleMode.kBrake);
    frSparkMax.setIdleMode(IdleMode.kBrake);
    brSparkMax.setIdleMode(IdleMode.kBrake);

    drive.setDeadband(DeadbandType.kDrive.speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive(this));
  }

  public void tankDrive(double l, double r) {
    drive.tankDrive(l, r, true);
  }

  public void arcadeDrive(double y, double r) {
    if (useGyro) {
      gyroDrive(y, r);
    } else {
      drive.arcadeDrive(y, r, true);
    }
  }

  //TODO: convert to PID?
  private boolean goingStraightPrevious = false;
  private double initialGyroAngle;

  private void gyroDrive(double y, double r) {
    // if (goingStraight(y, r)) {
    // if (!goingStraightPrevious) { // rising edge
    // initialGyroAngle = gyro.getAngle();
    // goingStraightPrevious = true;
    // }
    // double currentGyroAngle = gyro.getAngle();
    // double deltaAngle = (currentGyroAngle - initialGyroAngle);
    // r += GyroSensitivity.kDrive.val * Math.copySign(Math.pow(deltaAngle, 2),
    // deltaAngle);
    // SmartDashboard.putNumber("deltaAngle", deltaAngle);
    // SmartDashboard.putNumber("r", r);
    // } else {
    // goingStraightPrevious = false;
    // }
    drive.arcadeDrive(y, r, true);
  }

  private boolean goingStraight(double y, double r) {
    return (Math.abs(r) < DeadbandType.kDrive.speed);
  }

  private void toggleGyro() {
    useGyro = !useGyro;
  }

  private void toggleMode() {
    arcadeDrive = !arcadeDrive;
  }

}