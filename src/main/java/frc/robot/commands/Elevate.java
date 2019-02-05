package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Elevate extends Command {
  /**
   * Constructor calls the elevator object from the robot class
   */
    
  public Elevate() {
    requires(Robot.elevator);
  }
  /**
   * Takes the input from the triggers by subtracting the input of the left from the right
   * and moves the elevator accordingly
   */
  @Override
  protected void execute() {
    double speed = Robot.oi.joystick.getTriggerAxis(Hand.kRight) - Robot.oi.joystick.getTriggerAxis(Hand.kLeft);
    Robot.elevator.drive(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.elevator.drive(0);
  }
}
