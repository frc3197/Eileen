package org.team3197.frc2019.robot.commands;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.ElbowPreset;
import org.team3197.frc2019.robot.RobotMap.MaxSpeeds;
import org.team3197.frc2019.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;

public class ArticulateToPreset extends Command {

  private final ElbowPreset target;
  private final ElbowPreset targetWithTrigger;

  private final Trigger toggle;

  private Arm arm;

  private boolean finished;

  /**
   * Sets the value of the preset to the one that is intended to be moved to
   */
  public ArticulateToPreset(ElbowPreset target, ElbowPreset targetWithTrigger, Trigger toggle, Arm arm) {
    super();
    requires(arm);
    this.target = target;
    this.targetWithTrigger = targetWithTrigger;
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
    double elbowSpeed = getElbowSpeed();

    // TODO adjust the speeds here
    arm.elbow(elbowSpeed * MaxSpeeds.kArm.forwardSpeed);
    arm.wrist(0);
  }

  @Override
  protected boolean isFinished() {
    return finished;
  }

  /**
   * Returns the speed the elbow should move at to get to the preset requested.
   * 
   * @return
   */
  private double getElbowSpeed() {
    ElbowPreset currentTarget = (toggle.get()) ? targetWithTrigger : target;

    double error = arm.getElbowEncoderPosition() - currentTarget.elbowPos;
    finished = Math.abs(error) < RobotMap.elbowPresetThreshold;

    double speed = -RobotMap.elbowDegreeSensitivity
        * Math.copySign(Math.pow(Math.abs(error), RobotMap.elbowExponent), error);
    return speed;
  }
}
