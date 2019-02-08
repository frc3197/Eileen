
package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.BallManipulator;

public class Manipulate extends Command {
  BallManipulator intake;

  public Manipulate(BallManipulator intake) {
    requires(intake);
    this.intake = intake;
  }

  @Override
  protected void execute() {
    double speed = OI.intakeSpeed();
    intake.drive(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    intake.drive(0);
  }

}
