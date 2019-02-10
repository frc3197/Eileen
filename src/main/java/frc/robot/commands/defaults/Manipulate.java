
package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.CargoManipulator;

public class Manipulate extends Command {
  CargoManipulator manipulator;

  public Manipulate(CargoManipulator manipulator) {
    requires(manipulator);
    this.manipulator = manipulator;
  }

  @Override
  protected void execute() {
    double speed = OI.manipulatorSpeed();
    manipulator.drive(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    manipulator.drive(0);
  }

}
