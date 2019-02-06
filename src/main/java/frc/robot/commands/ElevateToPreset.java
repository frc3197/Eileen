package frc.robot.commands;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.RobotMap.ElevatorPreset;

public class ElevateToPreset extends Command {

  private final ElevatorPreset target;
  private final ElevatorPreset targetWithTrigger;

  private final Trigger toggle;

  private boolean finished;

  /**
   * Sets the value of the preset to the one that is intended to be moved to
   * 
   * @param target
   */
  public ElevateToPreset(ElevatorPreset target, ElevatorPreset targetWithTrigger, Trigger toggle) {
    requires(Robot.elevator);
    this.target = target;
    this.targetWithTrigger = targetWithTrigger;
    this.toggle = toggle;
    finished = false;
    SmartDashboard.putData(this);
  }

  /**
   * Moves the elevator based on the number of speed, and returns the value of the
   * encoder position to the Smart Dashboard
   */
  @Override
  protected void execute() {
    ElevatorPreset currentTarget = (toggle.get()) ? targetWithTrigger : target;
    double error = Robot.elevator.getEncoderPosition() - currentTarget.pos;
    finished = Math.abs(error) < RobotMap.elevatorPresetThreshold;
    // double speed = -Math.copySign(Math.pow(error, 2), error); // TODO check
    // polarity
    // double speed = -RobotMap.elevatorDegreeSensitivity * error;
    double speed = -RobotMap.elevatorDegreeSensitivity * Math.copySign(Math.sqrt(Math.abs(error)), error);
    SmartDashboard.putNumber("speed", speed);
    Robot.elevator.drive(speed);
  }

  @Override
  protected boolean isFinished() {
    return finished;
  }
}
