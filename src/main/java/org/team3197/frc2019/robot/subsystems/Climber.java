package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.CANSparkMaxID;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.commands.defaults.Climb;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem {
  private CANSparkMax vertical = new CANSparkMax(CANSparkMaxID.kLiftVertical.id, MotorType.kBrushless);
  private CANSparkMax horizontal = new CANSparkMax(CANSparkMaxID.kLiftHorizontal.id, MotorType.kBrushless);

  public Climber() {
    super();
    vertical.setIdleMode(IdleMode.kBrake);
    horizontal.setIdleMode(IdleMode.kCoast);
    vertical.setInverted(true);

    final double kP = 5e-5;
    final double kI = 1e-6;
    final double kD = 0;
    final double kIz = 0;
    final double kFF = 0.000156;
    final double kMaxOutput = 1;
    final double kMinOutput = -1;

    vertical.getPIDController().setP(kP);
    vertical.getPIDController().setI(kI);
    vertical.getPIDController().setD(kD);
    vertical.getPIDController().setIZone(kIz);
    vertical.getPIDController().setFF(kFF);
    vertical.getPIDController().setOutputRange(kMinOutput, kMaxOutput);

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Climb(this));
  }

  boolean pidLast = false;
  double referenceEncVal = 0;

  public void driveVertical(double speed) {
    SmartDashboard.putNumber("temp", vertical.getMotorTemperature());
    if (!pidLast) {
      referenceEncVal = vertical.getEncoder().getPosition();
    }
    if (pidLast = Math.abs(speed) < DeadbandType.kClimberVertical.speed) {
      vertical.getPIDController().setReference(referenceEncVal, ControlType.kPosition);
      CANDigitalInput limit = vertical.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen);
      if (limit.get()) {
        vertical.set(RobotMap.MaxSpeeds.kClimberVertical.reverseSpeed);
      }
      // vertical.getPIDController().setReference(0, ControlType.kVelocity);
    } else {
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
}
