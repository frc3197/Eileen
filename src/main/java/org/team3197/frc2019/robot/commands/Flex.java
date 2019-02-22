package org.team3197.frc2019.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Flex extends CommandGroup {

  /**
   * Moves the elevator first, then after waiting half a second the elbow and
   * wrist begin to move to the preset requested.
   * 
   * Called to move the elevator, elbow, and wrist to a preset.
   */
  public Flex(Command a, Command b) {
    addParallel(a);
    addParallel(b);
  }
}