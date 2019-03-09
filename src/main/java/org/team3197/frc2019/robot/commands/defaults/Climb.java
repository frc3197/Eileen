package org.team3197.frc2019.robot.commands.defaults;

import org.team3197.frc2019.robot.OI;
import org.team3197.frc2019.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class Climb extends Command {
  private Climber climber;

  public Climb(Climber climber) {
    requires(climber);
    this.climber = climber;
  }

  /**
   * Takes the speed from OI that the climber knives should drive at, and moves
   * them.
   */
  @Override
  protected void execute() {
    double verticalSpeed = OI.climberVerticalSpeed();
    double horizintalSpeed = OI.climberHorizontalSpeed();
    climber.driveVertical(verticalSpeed);
    climber.driveHorizontal(horizintalSpeed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    climber.driveVertical(0);
  }
}
