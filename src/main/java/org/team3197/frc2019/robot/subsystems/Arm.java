package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.Channel;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.RobotMap.GyroSensitivity;
import org.team3197.frc2019.robot.RobotMap.RobotType;
import org.team3197.frc2019.robot.commands.defaults.Articulate;
import org.team3197.frc2019.robot.utilities.Drivable;
import org.team3197.frc2019.robot.utilities.FunctionCommand;

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

  public FunctionCommand resetEncoder = new FunctionCommand(this::resetEncoderPosition);
  public FunctionCommand resetGyro = new FunctionCommand(this::resetGyroAngle);

  public FunctionCommand toggleGyro = new FunctionCommand(this::toggleGyro);

  private boolean useGyro = true;
  final double kP = 5e-5;

  final double kI = 1e-6;

  final double kD = 0;

  final double kIz = 0;

  final double kFF = 0.000156;

  final double kMaxOutput = 1;

  final double kMinOutput = -1;

  final double maxRPM = 3360;

  // Smart Motion Coefficients

  final double maxVel = 2000; // rpm

  final double maxAcc = 1500;

  public Arm() {
    super();

    elbow.setIdleMode(IdleMode.kBrake);
    wrist.setIdleMode(IdleMode.kBrake);

    if (RobotMap.current == RobotType.A) {
      wrist.setInverted(false);
    } else {
      wrist.setInverted(false);
    }

    // set PID coefficients

    elbow.getPIDController().setP(kP);

    elbow.getPIDController().setI(kI);

    elbow.getPIDController().setD(kD);

    elbow.getPIDController().setIZone(kIz);

    elbow.getPIDController().setFF(kFF);

    elbow.getPIDController().setOutputRange(kMinOutput, kMaxOutput);

    final int smartMotionSlot = 0;

    elbow.getPIDController().setSmartMotionMaxVelocity(maxVel, smartMotionSlot);

    // elbow.getPIDController().setSmartMotionMinOutputVelocity(minVel,

    // smartMotionSlot);

    elbow.getPIDController().setSmartMotionMaxAccel(maxAcc, smartMotionSlot);

    // elbow.getPIDController().setSmartMotionAllowedClosedLoopError(allowedErr,
    // smartMotionSlot);

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
    if (!elbowLimit.get() && Math.abs(output) < DeadbandType.kElbow.speed) {
      // output = 0;
      if (!pidLast) {
        pidLast = true;
        referenceEncVal = elbow.getEncoder().getPosition();
      }
      // elbow.getPIDController().setReference(referenceEncVal,
      // ControlType.kSmartMotion);
      elbow.getPIDController().setReference(0, ControlType.kSmartVelocity);
    } else {
      // elbow.set(output);
      pidLast = false;
      // elbow.getPIDController().setReference(output, ControlType.kDutyCycle);
      double rpm = output * maxRPM;
      elbow.getPIDController().setReference(rpm, ControlType.kSmartVelocity);
    }
    SmartDashboard.putNumber("ElbowEncoder", getElbowEncoderPosition());

  }

  public void wrist(double speed) {
    double output = speed;

    double deltaAngle = getAngle();
    double gyroSpeed = GyroSensitivity.kArm.val * deltaAngle;
    SmartDashboard.putNumber("wristGyroSpeed", gyroSpeed);
    SmartDashboard.putNumber("deltaAngle", deltaAngle);
    SmartDashboard.putNumber("WristEncoder", getWristEncoderPosition());

    if (Math.abs(output) < DeadbandType.kWrist.speed) {// && useGyro) {
      output = gyroSpeed;
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

  private void toggleGyro() {
    useGyro = !useGyro;
  }
}
