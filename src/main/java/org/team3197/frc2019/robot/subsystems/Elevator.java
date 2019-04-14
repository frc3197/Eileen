package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.commands.defaults.Elevate;
import org.team3197.frc2019.robot.utilities.Drivable;
import org.team3197.frc2019.robot.utilities.FunctionCommand;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem implements Drivable {
  private CANSparkMax left = new CANSparkMax(RobotMap.CANSparkMaxID.kElevatorLeft.id, MotorType.kBrushless);
  private CANSparkMax right = new CANSparkMax(RobotMap.CANSparkMaxID.kElevatorRight.id, MotorType.kBrushless);

  private SpeedControllerGroup elevatorGroup = new SpeedControllerGroup(left, right);

  public FunctionCommand reset = new FunctionCommand(this::resetEncoderPosition);

  public final double maxRPM = 600;

  public Elevator() {
    super();

    left.setIdleMode(IdleMode.kBrake);
    right.setIdleMode(IdleMode.kBrake);

    left.setInverted(true);

    final double kP = 5e-3;
    final double kI = 1e-6;
    final double kD = 0;
    final double kIz = 0;
    final double kFF = 0.000156;
    final double kMaxOutput = 1;
    final double kMinOutput = -1;

    right.getPIDController().setP(kP);
    right.getPIDController().setI(kI);
    right.getPIDController().setD(kD);
    right.getPIDController().setIZone(kIz);
    right.getPIDController().setFF(kFF);
    right.getPIDController().setOutputRange(kMinOutput, kMaxOutput);

    left.getPIDController().setP(kP);
    left.getPIDController().setI(kI);
    left.getPIDController().setD(kD);
    left.getPIDController().setIZone(kIz);
    left.getPIDController().setFF(kFF);
    left.getPIDController().setOutputRange(kMinOutput, kMaxOutput);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Elevate(this));
  }

  public void drive(double speed, boolean hold) {
    if (hold && Math.abs(speed) < DeadbandType.kElevator.speed) {
      elevatorGroup.set(DeadbandType.kElevator.speed);
    } else {
      elevatorGroup.set(speed);
    }
  }

  public void resetEncoderPosition() {
    right.getEncoder().setPosition(0);

  }

  public double getEncoderPosition() {
    return right.getEncoder().getPosition();
  }

}