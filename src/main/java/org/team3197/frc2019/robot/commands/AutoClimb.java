package org.team3197.frc2019.robot.commands;

import org.team3197.frc2019.robot.subsystems.Climber;
import org.team3197.frc2019.robot.subsystems.Erector;

import edu.wpi.first.wpilibj.command.Command;

public class AutoClimb extends Command {
  private Climber climber;
  private Erector erector;

  public AutoClimb(Climber climber, Erector erector) {
    requires(climber);
    requires(erector);
    this.climber = climber;
    this.erector = erector;
  }

  @Override
  protected void execute() {

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
