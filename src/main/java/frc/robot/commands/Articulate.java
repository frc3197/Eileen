package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.Arm;

public class Articulate extends Command {

  private Arm arm;

  public Articulate(Arm arm) {
    requires(arm);
    this.arm = arm;
  }

  @Override
  protected void execute() {
    double elbow = OI.elbowSpeed();
    double wrist = OI.wristSpeed();
    arm.wrist(wrist);
    arm.elbow(elbow);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    arm.elbow(0);
  }
}
