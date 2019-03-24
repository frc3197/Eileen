package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.commands.defaults.Elevate;
import org.team3197.frc2019.robot.utilities.Drivable;
import org.team3197.frc2019.robot.utilities.FunctionCommand;
import org.team3197.frc2019.robot.utilities.TriggerWrapper;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem implements Drivable {
  private CANSparkMax left = new CANSparkMax(RobotMap.CANSparkMaxID.kElevatorLeft.id, MotorType.kBrushless);
  private CANSparkMax right = new CANSparkMax(RobotMap.CANSparkMaxID.kElevatorRight.id, MotorType.kBrushless);

  private SpeedControllerGroup elevatorGroup = new SpeedControllerGroup(left, right);

  private CANDigitalInput bottomLimit = left.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);
  private CANDigitalInput topLimit = right.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);

  private TriggerWrapper limitReset = new TriggerWrapper(bottomLimit::get);
  public FunctionCommand reset = new FunctionCommand(this::resetEncoderPosition);

  public Elevator() {
    super();

    left.setIdleMode(IdleMode.kBrake);
    right.setIdleMode(IdleMode.kBrake);

    left.follow(right, true);
    limitReset.whenActive(reset);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Elevate(this));
  }

  public void drive(double speed, boolean hold) {
    // SmartDashboard.putNumber("speed", speed);
    // SmartDashboard.putNumber("ElevatorEncoder", getEncoderPosition());

    double output = speed;

    if (Math.abs(speed) < DeadbandType.kElevator.speed) {
      right.getPIDController().setReference(0, ControlType.kSmartMotion);
    } else {
      right.getPIDController().setReference(speed, ControlType.kDutyCycle);
    }

    elevatorGroup.set(output);
  }

  public void resetEncoderPosition() {
    right.getEncoder().setPosition(0);

  }

  public double getEncoderPosition() {
    return right.getEncoder().getPosition();
  }

}