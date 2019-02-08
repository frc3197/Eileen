package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Arm;

public class Articulate extends Command {

  private Arm arm;

  public Articulate(Arm arm) {
    requires(arm);
    this.arm = arm;
  }

  @Override
  protected void initialize() {
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
}
