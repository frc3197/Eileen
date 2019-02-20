
package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.CargoManipulator;

public class Manipulate extends Command {
  private CargoManipulator manipulator;

  public Manipulate(CargoManipulator manipulator) {
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
