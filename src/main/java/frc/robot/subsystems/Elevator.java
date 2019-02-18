package frc.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.RobotMap.DeadbandType;
import frc.robot.commands.defaults.Elevate;

public class Elevator extends Subsystem implements Drivable {
  private CANSparkMax right = new CANSparkMax(RobotMap.CANSparkMaxID.kElevatorRight.id, MotorType.kBrushless);
  private CANSparkMax left = new CANSparkMax(RobotMap.CANSparkMaxID.kElevatorLeft.id, MotorType.kBrushless);

  // private SpeedControllerGroup elevatorGroup = new SpeedControllerGroup(right,
  // left);

  private CANPIDController controller = right.getPIDController();

  private CANDigitalInput bottomLimit = left.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);
  private CANDigitalInput topLimit = right.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);

  private LimitReset limitReset = new LimitReset();
  public ResetCommand reset = new ResetCommand(this::resetEncoderPosition);

  public Elevator() {
    super();
    // left.setInverted(true);
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
    SmartDashboard.putNumber("getElevatorEncoderPosition", getEncoderPosition());

    // TODO Change me
    if ((!bottomLimit.get() && (Math.abs(speed) < DeadbandType.kElevator.speed))) {
      if (lastDriving) {
        lastDriving = false;
        encoderTarget = getRawEncoderPosition();
      }

      if (hold) {
        controller.setReference(encoderTarget, ControlType.kSmartMotion);
      }
    } else {
      controller.setReference(speed, ControlType.kDutyCycle);
    }
    // elevatorGroup.set(speed);

  }

  // private double conditions(double speed, double deadband) {
  // return (!bottomLimit.get() && Math.abs(speed) < deadband) ? deadband : speed;
  // }

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