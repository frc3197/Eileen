
package org.team3197.frc2019.robot.commands.defaults;

import edu.wpi.first.wpilibj.command.Command;
import org.team3197.frc2019.robot.OI;
import org.team3197.frc2019.robot.subsystems.Intake;

public class Manipulate extends Command {
  private Intake manipulator;

  public Manipulate(Intake manipulator) {
    requires(manipulator);
    this.manipulator = manipulator;

  }

  /**
   * Takes the speed from OI that the crgo intake should drive at, and moves them.
   */
  @Override
  protected void execute() {
    double speed = OI.manipulatorSpeed();
    manipulator.drive(speed, true);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    manipulator.drive(0, true);
  }

}
