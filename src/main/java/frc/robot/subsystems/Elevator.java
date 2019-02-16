package frc.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.RobotMap.DeadbandType;
import frc.robot.commands.defaults.Elevate;

public class Elevator extends IntermediateSubystem {
  private CANSparkMax right = new CANSparkMax(RobotMap.CANSparkMaxID.kElevatorRight.id, MotorType.kBrushless);
  private CANSparkMax left = new CANSparkMax(RobotMap.CANSparkMaxID.kElevatorLeft.id, MotorType.kBrushless);

  private SpeedControllerGroup elevatorGroup = new SpeedControllerGroup(right, left);

  private CANDigitalInput bottomLimit = left.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);
  private CANDigitalInput topLimit = right.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);

  private LimitReset limitReset = new LimitReset();
  public ResetCommand reset = new ResetCommand(this::resetEncoderPosition);

  public Elevator() {
    super();
    left.setInverted(true);
    limitReset.whenActive(reset);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Elevate(this));
  }

  public void drive(double speed, boolean hold) {
    SmartDashboard.putNumber("speed", speed);
    SmartDashboard.putNumber("getElevatorEncoderPosition", getEncoderPosition());

    if (hold) {
      speed = conditions(speed, DeadbandType.kElevator.speed);
    }
    elevatorGroup.set(speed);
  }

  private double conditions(double speed, double deadband) {
    return (!bottomLimit.get() && Math.abs(speed) < deadband) ? deadband : speed;
  }

  private double resetEncoderPosition = 0;

  public void resetEncoderPosition() {
    resetEncoderPosition = right.getEncoder().getPosition();
  }

  public double getEncoderPosition() {
    return right.getEncoder().getPosition() - resetEncoderPosition;
  }

  private class LimitReset extends Trigger {

    public boolean get() {
      return bottomLimit.get();
    }
  }
}