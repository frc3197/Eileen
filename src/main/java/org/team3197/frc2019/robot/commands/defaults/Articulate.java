package org.team3197.frc2019.robot.commands.defaults;

import edu.wpi.first.wpilibj.command.Command;
import org.team3197.frc2019.robot.OI;
import org.team3197.frc2019.robot.subsystems.Arm;

public class Articulate extends Command {

  private Arm arm;

  public Articulate(Arm arm) {
    requires(arm);
    this.arm = arm;
  }

  /**
   * Takes the speed from OI that the wrist and elbow should drive at, and moves
   * them.
   */
  @Override
  protected void execute() {
    double elbowSpeed = OI.elbowSpeed();
    double wristSpeed = OI.wristSpeed();
    arm.wrist(wristSpeed);
    arm.elbow(elbowSpeed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    arm.elbow(0);
    arm.wrist(0);
  }
}
