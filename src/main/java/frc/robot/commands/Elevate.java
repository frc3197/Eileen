package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class Elevate extends Command {

  public Elevate() {
    requires(Robot.elevator);
  }

  @Override
  protected void execute() {
    double speed = OI.joystick.getTriggerAxis(Hand.kRight) - OI.joystick.getTriggerAxis(Hand.kLeft);
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
