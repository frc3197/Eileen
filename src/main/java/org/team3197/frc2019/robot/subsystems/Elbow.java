package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.commands.defaults.Extend;
import org.team3197.frc2019.robot.utilities.Drivable;
import org.team3197.frc2019.robot.utilities.FunctionCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elbow extends Subsystem implements Drivable {

  private CANSparkMax elbow = new CANSparkMax(RobotMap.CANSparkMaxID.kElbow.id, MotorType.kBrushless);

  public FunctionCommand resetEncoder = new FunctionCommand(this::resetEncoderPosition);

  final double maxRPM = 5000;

  public Elbow() {
    super();

    elbow.setIdleMode(IdleMode.kBrake);

    elbow.setInverted(false);

    final double kP = 2e-4;
    final double kI = 1e-7;
    final double kD = 0.00015;
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
    setDefaultCommand(new Extend(this));
  }

  boolean pidLast = false;
  double referenceEncVal = 0;

  public void drive(double speed, boolean hold) {
    double output = -speed;

    if (!pidLast) {
      referenceEncVal = elbow.getEncoder().getPosition();
    }

    if (pidLast = Math.abs(output) < DeadbandType.kElbow.speed) {
      // elbow.getPIDController().setReference(referenceEncVal,
      // ControlType.kPosition);
      elbow.getPIDController().setReference(0, ControlType.kVelocity);
    } else {
      double rpm = output * maxRPM;
      SmartDashboard.putNumber("rpm", rpm);
      // if (hold) {
      // elbow.getPIDController().setReference(rpm, ControlType.kSmartVelocity);
      // } else {
      // elbow.set(output);
      // }
      elbow.set(output);
    }
    // elbow.set(output);
    // elbow.getPIDController().setReference(maxRPM * output,
    // ControlType.kVelocity);

    SmartDashboard.putNumber("ElbowEncoder", getElbowEncoderPosition());
    SmartDashboard.putNumber("ElbowVelocity", getVelocity());
  }

  double resetElbowEncoderPosition = 0;

  public double getElbowEncoderPosition() {
    return elbow.getEncoder().getPosition() - resetElbowEncoderPosition;
  }

  public double getVelocity() {
    return elbow.getEncoder().getVelocity();
  }

  private void resetEncoderPosition() {
    resetElbowEncoderPosition = elbow.getEncoder().getPosition();
  }
}
