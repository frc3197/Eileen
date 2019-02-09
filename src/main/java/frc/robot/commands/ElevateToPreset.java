package frc.robot.commands;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.RobotMap.ElevatorPreset;
import frc.robot.RobotMap.WristPreset;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Elevator;

public class ElevateToPreset extends Command {

  private final ElevatorPreset elevatorTarget;
  private final ElevatorPreset elevatorTargetWithTrigger;

  private final WristPreset wristTarget;
  private final WristPreset wristTargetWithTrigger;

  private final Trigger toggle;

  private Elevator elevator;
  private Arm arm;

  private boolean finished;

  /**
   * Sets the value of the preset to the one that is intended to be moved to
   */
  public ElevateToPreset(ElevatorPreset elevatorTarget, ElevatorPreset elevatorTargetWithTrigger,
      WristPreset wristTarget, WristPreset wristTargetWithTrigger, Trigger toggle, Elevator elevator) {
    requires(elevator);
    this.elevatorTarget = elevatorTarget;
    this.elevatorTargetWithTrigger = elevatorTargetWithTrigger;
    this.wristTarget = wristTarget;
    this.wristTargetWithTrigger = wristTargetWithTrigger;
    this.toggle = toggle;
    this.elevator = elevator;
    finished = false;
  }

  /**
   * Moves the elevator based on the number of speed, and returns the value of the
   * encoder position to the Smart Dashboard
   */
  @Override
  protected void execute() {
    double elevatorSpeed = getElevatorSpeed();
    double wristSpeed = getWristSpeed();

    // SmartDashboard.putNumber("elevatorspeed", elevatorSpeed);

    elevator.drive(elevatorSpeed);
    //uncomment for the wrist to move with a preset (to go into "hatch" or "cargo" mode)
    //arm.wrist(wristSpeed);
  }

  @Override
  protected boolean isFinished() {
    return finished;
  }

  private double getElevatorSpeed() {
    ElevatorPreset currentTarget = (toggle.get()) ? elevatorTargetWithTrigger : elevatorTarget;

    double error = elevator.getEncoderPosition() - currentTarget.pos;
    finished = Math.abs(error) < RobotMap.elevatorPresetThreshold;

    double speed = -RobotMap.elevatorDegreeSensitivity
        * Math.copySign(Math.pow(Math.abs(error), RobotMap.elevatorExponent), error);
    return speed;
  }

  private double getWristSpeed() {
    WristPreset currentTarget = (toggle.get()) ? wristTargetWithTrigger : wristTarget;

    double error = arm.getWristEncoderPosition() - currentTarget.pos;
    finished = Math.abs(error) < RobotMap.wristPresetThreshold;

    double speed = -RobotMap.wristDegreeSensitivity
        * Math.copySign(Math.pow(Math.abs(error), RobotMap.wristExponent), error);
    return speed;
  }
}
