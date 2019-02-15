package frc.robot.commands;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.RobotMap.ArmPreset;
import frc.robot.subsystems.Arm;

public class ArticulateToPreset extends Command {

  private final ArmPreset target;
  private final ArmPreset targetWithTrigger;

  private final Trigger toggle;

  private Arm arm;

  private boolean finished;

  /**
   * Sets the value of the preset to the one that is intended to be moved to
   */
  public ArticulateToPreset(ArmPreset target, ArmPreset wristTargetWithTrigger, Trigger toggle, Arm arm) {
    super();
    requires(arm);
    this.target = target;
    this.targetWithTrigger = wristTargetWithTrigger;
    this.toggle = toggle;
    this.arm = arm;
    finished = false;
  }

  /**
   * Moves the elevator based on the number of speed, and returns the value of the
   * encoder position to the Smart Dashboard
   */
  @Override
  protected void execute() {
    double speed = getWristSpeed();

    arm.wrist(speed);
    arm.elbow(-speed);
  }

  @Override
  protected boolean isFinished() {
    return finished;
  }

  private double getWristSpeed() {
    ArmPreset currentTarget = (toggle.get()) ? targetWithTrigger : target;

    double error = arm.getWristEncoderPosition() - currentTarget.wristPos;
    finished = Math.abs(error) < RobotMap.wristPresetThreshold;

    double speed = -RobotMap.wristDegreeSensitivity
        * Math.copySign(Math.pow(Math.abs(error), RobotMap.wristExponent), error);
    return speed;
  }
}
