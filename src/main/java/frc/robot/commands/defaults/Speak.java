package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Hatch;

public class Speak extends Command {

  private Hatch hatch;
  private double speed;

  public Speak(Hatch hatch, double speed) {
    requires(hatch);
    this.hatch = hatch;
    this.speed = speed;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
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
