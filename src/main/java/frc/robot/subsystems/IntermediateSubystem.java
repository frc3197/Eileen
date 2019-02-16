package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class IntermediateSubystem extends Subsystem {

  public abstract void drive(double speed, boolean hold);
}
