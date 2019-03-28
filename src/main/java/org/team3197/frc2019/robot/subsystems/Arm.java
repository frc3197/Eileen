package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.Channel;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.RobotMap.GyroSensitivity;
import org.team3197.frc2019.robot.commands.defaults.Articulate;
import org.team3197.frc2019.robot.utilities.Drivable;
import org.team3197.frc2019.robot.utilities.FunctionCommand;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem implements Drivable {

  private CANSparkMax elbow = new CANSparkMax(RobotMap.CANSparkMaxID.kElbow.id, MotorType.kBrushless);
  private CANSparkMax wrist = new CANSparkMax(RobotMap.CANSparkMaxID.kWrist.id, MotorType.kBrushless);

  public AnalogGyro gyro = new AnalogGyro(Channel.kWristGyro.channel);

  public FunctionCommand resetEncoder = new FunctionCommand(this::resetEncoderPosition);
  public FunctionCommand resetGyro = new FunctionCommand(this::resetGyroAngle);

  public FunctionCommand toggleGyro = new FunctionCommand(this::toggleGyro);

  private boolean useGyro = true;

  final double maxRPM = 6000;

  public Arm() {
    super();

    elbow.setIdleMode(IdleMode.kBrake);
    wrist.setIdleMode(IdleMode.kBrake);

    elbow.setInverted(true);
    wrist.setInverted(false);

    final double kP = 5e-5;
    final double kI = 1e-6;
    final double kD = 0;
    final double kIz = 0;
    final double kFF = 0.000156;
    final double kMaxOutput = 1;
    final double kMinOutput = -1;

    elbow.getPIDController().setP(kP);
    elbow.getPIDController().setI(kI);
    elbow.getPIDController().setD(kD);
    elbow.getPIDController().setIZone(kIz);
    elbow.getPIDController().setFF(kFF);
    elbow.getPIDController().setOutputRange(kMinOutput, kMaxOutput);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Articulate(this));
  }

  public void drive(double speed, boolean hold) {
    elbow(speed);
    wrist(0);
  }

  boolean pidLast = false;
  double referenceEncVal = 0;

  public void elbow(double speed) {
    double output = speed;

    // Stops the elbow from constantly moving upwards when not being moved by the
    // joystick
    if (Math.abs(output) < DeadbandType.kElbow.speed) {
      if (!pidLast) {
        pidLast = true;
        referenceEncVal = elbow.getEncoder().getPosition();
      }

      elbow.getPIDController().setReference(0, ControlType.kVelocity);
    } else {
      pidLast = false;
      double rpm = output * maxRPM;
      elbow.getPIDController().setReference(rpm, ControlType.kVelocity);
    }
    // SmartDashboard.putNumber("ElbowEncoder", getElbowEncoderPosition());

  }

  public void wrist(double speed) {
    double output = speed;

    double deltaAngle = getAngle();
    double gyroSpeed = GyroSensitivity.kArm.val * deltaAngle;
    // SmartDashboard.putNumber("wristGyroSpeed", gyroSpeed);
    // SmartDashboard.putNumber("deltaAngle", deltaAngle);
    // SmartDashboard.putNumber("WristEncoder", getWristEncoderPosition());

    if (useGyro && Math.abs(output) < DeadbandType.kWrist.speed) {
      output = gyroSpeed;
    } else {
      resetGyroAngle();
    }

    wrist.set(output);
  }

  private double getAngle() {
    return gyro.getAngle();

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

  private void resetEncoderPosition() {
    resetElbowEncoderPosition = elbow.getEncoder().getPosition();
    resetWristEncoderPosition = wrist.getEncoder().getPosition();
  }

  private void resetGyroAngle() {
    gyro.reset();
  }

  private void toggleGyro() {
    useGyro = !useGyro;
  }
}
