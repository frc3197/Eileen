package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.commands.defaults.Erect;
import org.team3197.frc2019.robot.utilities.Drivable;
import org.team3197.frc2019.robot.utilities.FunctionCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Erector extends Subsystem implements Drivable {

  private CANSparkMax left = new CANSparkMax(RobotMap.CANSparkMaxID.kErectorLeft.id, MotorType.kBrushless);
  private CANSparkMax right = new CANSparkMax(RobotMap.CANSparkMaxID.kErectorRight.id, MotorType.kBrushless);

  public Erector() {
    super();

    left.setIdleMode(IdleMode.kBrake);
    right.setIdleMode(IdleMode.kBrake);

    right.follow(left, true);

    final double kP = 5e-2;
    final double kI = 1e-4;
    final double kD = 0;
    final double kIz = 0;
    final double kFF = 0.000156;
    final double kMaxOutput = 1;
    final double kMinOutput = -1;

    // final double maxVel = 2000;
    // final double maxAcc = 1500;

    // set PID coefficients
    left.getPIDController().setP(kP);
    left.getPIDController().setI(kI);
    left.getPIDController().setD(kD);
    left.getPIDController().setIZone(kIz);
    left.getPIDController().setFF(kFF);
    left.getPIDController().setOutputRange(kMinOutput, kMaxOutput);
    right.getPIDController().setP(kP);
    right.getPIDController().setI(kI);
    right.getPIDController().setD(kD);
    right.getPIDController().setIZone(kIz);
    right.getPIDController().setFF(kFF);
    right.getPIDController().setOutputRange(kMinOutput, kMaxOutput);

    // final int smartMotionSlot = 0;
    // left.getPIDController().setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
    // left.getPIDController().setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
    // right.getPIDController().setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
    // right.getPIDController().setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Erect(this));
  }

  boolean stoppedLast = false;
  private double encoderLastLeft = 0;
  private double encoderLastRight = 0;

  public FunctionCommand resetPID = new FunctionCommand(this::resetPID);

  private void resetPID() {
    encoderLastLeft = left.getEncoder().getPosition();
    encoderLastRight = right.getEncoder().getPosition();
    stoppedLast = true;
  }

  public void drive(double speed, boolean hold) {
    // erectorGroup.set(speed);
    SmartDashboard.putNumber("speed1", speed);
    if (hold && Math.abs(speed) < DeadbandType.kErector.speed) {
      if (!stoppedLast) {
        resetPID();
      }
      right.getPIDController().setReference(encoderLastRight, ControlType.kPosition);
      left.getPIDController().setReference(encoderLastLeft, ControlType.kPosition);
    } else {
      stoppedLast = false;
      // left.getPIDController().setReference(speed, ControlType.kDutyCycle);
      left.set(speed);
    }
  }
}
