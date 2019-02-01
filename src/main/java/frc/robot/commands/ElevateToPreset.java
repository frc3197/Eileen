package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.RobotMap.ElevatorPreset;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevateToPreset extends Command {

  private final ElevatorPreset preset;

  private boolean finished;

  public ElevateToPreset(ElevatorPreset target) {
    requires(Robot.elevator);
    preset = target;
    finished = false;
  }

  @Override
  protected void execute() {
    SmartDashboard.putNumber("Elevator Encoder", Robot.elevator.getEncoderPosition());
    double error = Robot.elevator.getEncoderPosition() - preset.pos;
    finished = Math.abs(error) < RobotMap.elevatorPresetThreshold;
    double speed = -Math.copySign(Math.pow(error, 2), error); // TODO check polarity
    Robot.elevator.drive(speed);
  }

  @Override
  protected boolean isFinished() {
    return finished;
  }
}
