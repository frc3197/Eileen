package org.team3197.frc2019.robot.commands.defaults;

import edu.wpi.first.wpilibj.command.Command;
import org.team3197.frc2019.robot.OI;
import org.team3197.frc2019.robot.subsystems.Elbow;

public class Extend extends Command {

  private Elbow elbow;

  public Extend(Elbow elbow) {
    requires(elbow);
    this.elbow = elbow;
  }

  @Override
  protected void execute() {
    double speed = OI.elbowSpeed();
    elbow.drive(speed, true);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    elbow.drive(0, true);
  }
}
