package frc.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.Elevate;

public class Elevator extends Subsystem {
  private CANSparkMax left = new CANSparkMax(RobotMap.CANSparkMaxID.ELEVATORLEFT.id, MotorType.kBrushless);
  private CANSparkMax right = new CANSparkMax(RobotMap.CANSparkMaxID.ELEVATORRIGHT.id, MotorType.kBrushless);

  private SpeedControllerGroup elevatorGroup = new SpeedControllerGroup(left, right);

  private CANDigitalInput bottomLimit = left.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);
  private CANDigitalInput topLimit = right.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);

  public Elevator() {
    super();
    left.setInverted(true);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Elevate());
  }

  /*
   * Makes sure the elevator can't go any farther up when it hits the
   * upperLimitSwitch, and that it can't go any farther down when it hits the
   * lowerLimitSwitch
   */
  public void drive(double speed) {
    SmartDashboard.putBoolean("toplim", topLimit.get());
    SmartDashboard.putBoolean("bottomlim", bottomLimit.get());

    double output = speed;
    if (topLimit.get()) {
      output = Math.min(output, 0);
    } // If top pressed(returning a zero value), only drive negative
    if (bottomLimit.get()) {
      output = Math.max(output, 0);
    } // If bottom pressed, only drive positive
    elevatorGroup.set(output);
  }
}