package org.team3197.frc2019.robot.utilities;

import edu.wpi.first.wpilibj.buttons.Trigger;

public class TriggerWrapper extends Trigger {
  BooleanFunctionWrapper function;

  public TriggerWrapper(BooleanFunctionWrapper function) {
    this.function = function;
  }

  @Override
  public boolean get() {
    return function.get();
  }
}
