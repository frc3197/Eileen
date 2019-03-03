package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.Channel;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.RobotMap.GyroSensitivity;
import org.team3197.frc2019.robot.RobotMap.RobotType;
import org.team3197.frc2019.robot.commands.defaults.Articulate;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem implements Drivable {

  private CANSparkMax elbow = new CANSparkMax(RobotMap.CANSparkMaxID.kElbow.id, MotorType.kBrushless);
  private CANSparkMax wrist = new CANSparkMax(RobotMap.CANSparkMaxID.kWrist.id, MotorType.kBrushless);

  private CANDigitalInput elbowLimit = elbow.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);
  private CANDigitalInput wristLimit = wrist.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);

  public AnalogGyro gyro = new AnalogGyro(Channel.kWristGyro.channel);

  public ResetCommand reset = new ResetCommand(this::resetEncoderPosition);
  public ResetCommand resetGyro = new ResetCommand(this::resetGyroAngle);

  public Arm() {
    super();

    elbow.setIdleMode(IdleMode.kBrake);
    wrist.setIdleMode(IdleMode.kBrake);

    if (RobotMap.current == RobotType.A) {
      wrist.setInverted(false);
    } else {
      wrist.setInverted(false);
    }
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Articulate(this));
  }

  public void drive(double speed, boolean hold) {
    elbow(speed);
    wrist(0);
  }

  public void elbow(double speed) {
    double output = speed;

    // Stops the elbow from constaltly moving upwards when not being moved by the
    // joystick
    if (!elbowLimit.get() && Math.abs(output) < DeadbandType.kElbow.speed) {
      output = 0;
    }
    SmartDashboard.putNumber("ElbowEncoder", getElbowEncoderPosition());

    elbow.set(output);
  }

  public void wrist(double speed) {
    double output = speed;

    // Stops the wrist from constaltly moving upwards when not being moved by the
    // joystick
    // if (Math.abs(output) < DeadbandType.kWrist.speed) {
    // output = 0;// -DeadbandType.kWrist.speed;
    // }
    // SmartDashboard.putNumber("wristOutput", output);

    // gyro mode centers around 0
    // if (!wristLimit.get() && Math.abs(output) < DeadbandType.kWrist.speed) {

    double deltaAngle = getAngle();
    double gyroSpeed = GyroSensitivity.kArm.val * deltaAngle;
    SmartDashboard.putNumber("wristGyroSpeed", gyroSpeed);
    SmartDashboard.putNumber("deltaAngle", deltaAngle);
    SmartDashboard.putNumber("WristEncoder", getWristEncoderPosition());

    if (Math.abs(output) < DeadbandType.kWrist.speed) {
      // if (Math.abs(gyroSpeed) < DeadbandType.kWrist.speed) {
      // gyroSpeed = 0;
      // }
      output = gyroSpeed;
      // output = 0;
    } else {
      resetGyroAngle();
    }

    SmartDashboard.putNumber("output", output);
    wrist.set(output);
  }

  private double getAngle() {
    if (RobotMap.current == RobotType.A) {
      return gyro.getAngle();
    } else {
      return -gyro.getAngle();
    }
  }

  public void resetGyro() {
    gyro.reset();
  }

  public class ResetGyro extends InstantCommand {

    ResetGyro() {
      super();
    }

    @Override
    public void initialize() {
      resetGyro();
    }
  }

  // TODO change when spark max releases encoder reset

  double resetWristEncoderPosition = 0;
  double resetElbowEncoderPosition = 0;

  public double getElbowEncoderPosition() {
    return elbow.getEncoder().getPosition() - resetElbowEncoderPosition;
  }

  public double getWristEncoderPosition() {
    return wrist.getEncoder().getPosition() - resetWristEncoderPosition;
  }

  private double getWristEncoderPositionRaw() {
    return wrist.getEncoder().getPosition() * ((RobotMap.current == RobotType.A) ? 1 : -1);
  }

  private void resetEncoderPosition() {
    resetElbowEncoderPosition = elbow.getEncoder().getPosition();
    resetWristEncoderPosition = wrist.getEncoder().getPosition();
  }

  private void resetGyroAngle() {
    gyro.reset();
  }
}
