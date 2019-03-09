package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.commands.defaults.Elevate;
import org.team3197.frc2019.robot.utilities.Drivable;
import org.team3197.frc2019.robot.utilities.FunctionCommand;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem implements Drivable {
  private CANSparkMax left = new CANSparkMax(RobotMap.CANSparkMaxID.kElevatorLeft.id, MotorType.kBrushless);
  private CANSparkMax right = new CANSparkMax(RobotMap.CANSparkMaxID.kElevatorRight.id, MotorType.kBrushless);

  private SpeedControllerGroup elevatorGroup = new SpeedControllerGroup(left, right);

  private CANPIDController controller = right.getPIDController();

  private CANDigitalInput bottomLimit = left.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);
  private CANDigitalInput topLimit = right.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);

  private LimitReset limitReset = new LimitReset();
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

  private boolean lastDriving = false;

  private double encoderTarget;

  public void drive(double speed, boolean hold) {
    SmartDashboard.putNumber("speed", speed);
    SmartDashboard.putNumber("ElevatorEncoder", getEncoderPosition());

    double output = speed;
    if (!bottomLimit.get() && Math.abs(output) < DeadbandType.kElevator.speed) {
      output = DeadbandType.kElevator.speed;
    }
    // if (topLimit.get()) {
    // output = Math.min(output, 0);
    // } // If top pressed(returning a zero value), only drive negative
    // if (bottomLimit.get()) {
    // output = Math.max(output, 0);
    // } // If bottom pressed, only drive positive
    // if (getEncoderPosition() < -15) {
    // output = DeadbandType.kElevator.speed * (-15 / getEncoderPosition());
    // }

    // TODO Change me
    // if ((!bottomLimit.get() && (Math.abs(speed) < DeadbandType.kElevator.speed)))
    // {
    // if (lastDriving) {
    // lastDriving = false;
    // encoderTarget = getRawEncoderPosition();
    // }

    // if (hold) {
    // controller.setReference(encoderTarget, ControlType.kSmartMotion);
    // } else {
    // controller.setReference(speed, ControlType.kDutyCycle);
    // }
    elevatorGroup.set(output);

  }

  private double resetEncoderPosition = 0;

  public void resetEncoderPosition() {
    resetEncoderPosition = right.getEncoder().getPosition();
  }

  public double getEncoderPosition() {
    return right.getEncoder().getPosition() - resetEncoderPosition;
  }

  public double getRawEncoderPosition() {
    return right.getEncoder().getPosition();
  }

  private class LimitReset extends Trigger {

    public boolean get() {
      return bottomLimit.get();
    }
  }
}