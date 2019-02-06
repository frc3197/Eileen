package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

public class Elevate extends Command {

  private Elevator elevator;

  public Elevate(Elevator elevator) {
    requires(elevator);
    this.elevator = elevator;
  }

  @Override
  protected void execute() {
    double speed = Robot.oi.joystick.getTriggerAxis(Hand.kRight) - Robot.oi.joystick.getTriggerAxis(Hand.kLeft);
    elevator.drive(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    elevator.drive(0);
  }
}
