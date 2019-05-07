package org.team3197.frc2019.robot.commands;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.ElbowPreset;
import org.team3197.frc2019.robot.subsystems.Elbow;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;

public class ExtendToPreset extends Command {

  private final ElbowPreset target;
  private final ElbowPreset targetWithTrigger;

  private final Trigger toggle;

  private Elbow elbow;

  private boolean finished;

  /**
   * Sets the value of the preset to the one that is intended to be moved to
   */
  public ExtendToPreset(ElbowPreset target, ElbowPreset targetWithTrigger, Trigger toggle, Elbow elbow) {
    super();
    requires(elbow);
    this.target = target;
    this.targetWithTrigger = targetWithTrigger;
    this.toggle = toggle;
    this.elbow = elbow;
    finished = false;
  }

  /**
   * Moves the elevator based on the number of speed, and returns the value of the
   * encoder position to the Smart Dashboard
   */
  @Override
  protected void execute() {
    double elbowSpeed = getElbowSpeed();
    elbow.drive(elbowSpeed, false);
  }

  @Override
  protected boolean isFinished() {
    return finished;
  }

  @Override
  protected void end() {
    elbow.drive(0, true);
  }

  /**
   * Returns the speed the elbow should move at to get to the preset requested.
   * 
   * @return
   */
  private double getElbowSpeed() {
    ElbowPreset currentTarget = (toggle.get()) ? targetWithTrigger : target;

    double error = elbow.getElbowEncoderPosition() - currentTarget.elbowPos;
    finished = Math.abs(error) < RobotMap.elbowPresetThreshold;
    // finished = Math.abs(elbow.getVelocity()) <
    // RobotMap.elbowVelocityPresetThreshold;

    double speed = -RobotMap.elbowDegreeSensitivity * error;
    return speed;
  }
}
