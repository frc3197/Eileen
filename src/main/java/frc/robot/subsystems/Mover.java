package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class Mover extends Subsystem {

  public abstract void drive(double speed);

  public final ResetEncoderPosition reset = new ResetEncoderPosition(this);

  protected abstract void resetEncoderPosition();

  public class ResetEncoderPosition extends InstantCommand {

    private Mover m;

    protected ResetEncoderPosition(Mover m) {
      requires(m);
      this.m = m;
    }

    @Override
    protected void initialize() {
      m.resetEncoderPosition();
    }
  }
}
