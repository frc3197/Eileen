package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Hatch;

public class Speak extends Command {

  private Hatch hatch;

  public Speak(Hatch hatch) {
    requires(hatch);
    this.hatch = hatch;
  }

  @Override
  protected void initialize() {
  }

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
