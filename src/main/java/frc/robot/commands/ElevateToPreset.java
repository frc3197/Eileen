package frc.robot.commands;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.RobotMap.ElevatorPreset;
import frc.robot.subsystems.Elevator;

public class ElevateToPreset extends Command {

  private final ElevatorPreset target;
  private final ElevatorPreset targetWithTrigger;

  private final Trigger toggle;

  private Elevator elevator;

  private boolean finished;

  /**
   * Sets the value of the preset to the one that is intended to be moved to
   */
  public ElevateToPreset(ElevatorPreset target, ElevatorPreset targetWithTrigger, Trigger toggle, Elevator elevator) {
    requires(elevator);
    this.target = target;
    this.targetWithTrigger = targetWithTrigger;
    this.toggle = toggle;
    finished = false;
  }

  /**
   * Moves the elevator based on the number of speed, and returns the value of the
   * encoder position to the Smart Dashboard
   */
  @Override
  protected void execute() {
    double speed = getSpeed();

    SmartDashboard.putNumber("speed", speed);

    elevator.drive(speed);
  }

  @Override
  protected boolean isFinished() {
    return finished;
  }

  private double getSpeed() {
    ElevatorPreset currentTarget = (toggle.get()) ? targetWithTrigger : target;

    double error = elevator.getEncoderPosition() - currentTarget.pos;
    finished = Math.abs(error) < RobotMap.elevatorPresetThreshold;

    double speed = -RobotMap.elevatorDegreeSensitivity
        * Math.copySign(Math.pow(Math.abs(error), RobotMap.elevatorExponent), error);
    return speed;
  }
}
