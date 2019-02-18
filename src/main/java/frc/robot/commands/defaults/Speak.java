package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.Hatch;

public class Speak extends Command {

  private Hatch hatch;

  public Speak(Hatch hatch) {
    requires(hatch);
    this.hatch = hatch;

  }

  /**
   * Takes the speed from OI that the hatch beak should drive at, and moves them.
   */
  @Override
  protected void execute() {
    double speed = OI.hatchSpeed();
    hatch.drive(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    hatch.drive(0);
  }
}
