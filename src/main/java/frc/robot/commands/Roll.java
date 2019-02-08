
package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.Intake;

public class Roll extends Command {
  Intake intake;

  public Roll(Intake intake) {
    requires(intake);
    this.intake = intake;
  }

  @Override
  protected void execute() {
    double speed = 0;// OI.secondary.getTriggerAxis(Hand.kRight) -
                     // OI.secondary.getTriggerAxis(Hand.kLeft);
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
