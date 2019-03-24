package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import org.team3197.frc2019.robot.RobotMap.CANSparkMaxID;
import org.team3197.frc2019.robot.RobotMap.Channel;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.commands.defaults.Climb;
import org.team3197.frc2019.robot.utilities.FunctionCommand;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
  private CANSparkMax vertical = new CANSparkMax(CANSparkMaxID.kLiftVertical.id, MotorType.kBrushless);
  private CANSparkMax horizontal = new CANSparkMax(CANSparkMaxID.kLiftHorizontal.id, MotorType.kBrushless);

  private AnalogGyro gyro = new AnalogGyro(Channel.kClimberGyro.channel);

  public FunctionCommand resetGyro = new FunctionCommand(this::resetGyroAngle);

  public Climber() {
    super();
    vertical.setIdleMode(IdleMode.kBrake);
    horizontal.setIdleMode(IdleMode.kCoast);

    final double kP = 5e-5;
    final double kI = 1e-6;
    final double kD = 0;
    final double kIz = 0;
    final double kFF = 0.000156;
    final double kMaxOutput = 1;
    final double kMinOutput = -1;
    final double maxRPM = 5700;

    // Smart Motion Coefficients
    final double maxVel = 2000; // rpm
    final double maxAcc = 1500;

    // set PID coefficients
    vertical.getPIDController().setP(kP);
    vertical.getPIDController().setI(kI);
    vertical.getPIDController().setD(kD);
    vertical.getPIDController().setIZone(kIz);
    vertical.getPIDController().setFF(kFF);
    vertical.getPIDController().setOutputRange(kMinOutput, kMaxOutput);

    final int smartMotionSlot = 0;
    vertical.getPIDController().setSmartMotionMaxVelocity(maxVel, smartMotionSlot);

    vertical.getPIDController().setSmartMotionMaxAccel(maxAcc, smartMotionSlot);

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Climb(this));
  }

  boolean pidLast = false;
  double referenceEncVal = 0;

  public void driveVertical(double speed) {
    // SmartDashboard.putNumber("encoderValueOfTheVerticalClimber",
    // vertical.getEncoder().getPosition());
    if (Math.abs(speed) < DeadbandType.kClimberVertical.speed) {
      // if (!pidLast) {
      // pidLast = true;
      // referenceEncVal = vertical.getEncoder().getPosition();
      // }

      // vertical.getPIDController().setReference(referenceEncVal,
      // ControlType.kPosition); // Try replacing 0 with
      // referenceEval
      vertical.getPIDController().setReference(0, ControlType.kPosition);
    } else {
      pidLast = false;
      vertical.getEncoder().setPosition(0);
      vertical.getPIDController().setReference(speed, ControlType.kDutyCycle);
    }
  }

  public void setReferenceVertical(double value, ControlType type) {
    vertical.getPIDController().setReference(value, type);
  }

  public void driveHorizontal(double speed) {
    horizontal.set(speed);
  }

  public double getAngle() {
    return gyro.getAngle();
  }

  private void resetGyroAngle() {
    gyro.reset();
  }
}
