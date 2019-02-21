package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.Channel;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.RobotMap.GyroSensitivity;
import org.team3197.frc2019.robot.commands.defaults.Articulate;

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

    double deltaAngle = gyro.getAngle();
    double gyroSpeed = GyroSensitivity.kArm.val * deltaAngle;
    SmartDashboard.putNumber("wristGyroSpeed", gyroSpeed);
    SmartDashboard.putNumber("deltaAngle", deltaAngle);
    SmartDashboard.putNumber("WristEncoder", getWristEncoderPosition());

    if (Math.abs(output) < DeadbandType.kWrist.speed) {
      output = gyroSpeed;
    } else {
      resetGyroAngle();
    }

    wrist.set(output);
  }

  double resetWristEncoderPosition = 0;
  double resetElbowEncoderPosition = 0;

  public double getElbowEncoderPosition() {
    return elbow.getEncoder().getPosition() - resetElbowEncoderPosition;
  }

  public double getWristEncoderPosition() {
    return wrist.getEncoder().getPosition() - resetWristEncoderPosition;
  }

  private void resetEncoderPosition() {
    resetElbowEncoderPosition = elbow.getEncoder().getPosition();
    resetWristEncoderPosition = wrist.getEncoder().getPosition();
  }

  private void resetGyroAngle() {
    gyro.reset();
  }
}
