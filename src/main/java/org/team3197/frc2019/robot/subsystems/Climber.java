package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import org.team3197.frc2019.robot.RobotMap.CANSparkMaxID;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.commands.defaults.Climb;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
  private CANSparkMax vertical = new CANSparkMax(CANSparkMaxID.kLiftVertical.id, MotorType.kBrushless);
  private CANSparkMax horizontal = new CANSparkMax(CANSparkMaxID.kLiftHorizontal.id, MotorType.kBrushless);

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
    // elbow.getPIDController().setSmartMotionMinOutputVelocity(minVel,
    // smartMotionSlot);
    vertical.getPIDController().setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
    // elbow.getPIDController().setSmartMotionAllowedClosedLoopError(allowedErr,
    // smartMotionSlot);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Climb(this));
  }

  boolean pidLast = false;
  double referenceEncVal = 0;

  public void driveVertical(double speed) {
    if (Math.abs(speed) < DeadbandType.kClimberVertical.speed) {
      // output = 0;
      if (!pidLast) {
        pidLast = true;
        referenceEncVal = vertical.getEncoder().getPosition();
      }
      // vertical.getPIDController().setReference(referenceEncVal,
      // ControlType.kSmartMotion);
      vertical.getPIDController().setReference(0, ControlType.kSmartVelocity);
    } else {
      // vertical.set(output);
      pidLast = false;
      vertical.getPIDController().setReference(speed, ControlType.kDutyCycle);
    }
  }

  public void driveHorizontal(double speed) {
    horizontal.set(speed);
  }
}
