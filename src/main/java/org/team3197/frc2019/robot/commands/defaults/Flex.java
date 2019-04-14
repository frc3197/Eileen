package org.team3197.frc2019.robot.commands.defaults;

import edu.wpi.first.wpilibj.command.Command;
import org.team3197.frc2019.robot.OI;
import org.team3197.frc2019.robot.subsystems.Wrist;

public class Flex extends Command {

  private Wrist wrist;

  public Flex(Wrist wrist) {
    requires(wrist);
    this.wrist = wrist;
  }

  @Override
  protected void execute() {
    double speed = OI.wristSpeed();
    wrist.drive(speed, true);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    wrist.drive(0, true);
  }
}
