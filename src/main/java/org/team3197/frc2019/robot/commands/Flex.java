package org.team3197.frc2019.robot.commands;

import org.team3197.frc2019.robot.RobotMap.ArmPreset;
import org.team3197.frc2019.robot.RobotMap.ElevatorPreset;
import org.team3197.frc2019.robot.subsystems.Arm;
import org.team3197.frc2019.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Flex extends CommandGroup {

  /**
   * Moves the elevator first, then after waiting half a second the elbow and
   * wrist begin to move to the preset requested.
   * 
   * Called to move the elevator, elbow, and wrist to a preset.
   * 
   * @param elevatorTarget
   * @param elevatorTargetWithTrigger
   * @param target
   * @param targetWithTrigger
   * @param toggle
   * @param elevator
   * @param arm
   */
  public Flex(ElevatorPreset elevatorTarget, ElevatorPreset elevatorTargetWithTrigger, ArmPreset target,
      ArmPreset targetWithTrigger, Trigger toggle, Elevator elevator, Arm arm) {
    super();
    // TODO when going to an elevator with a NEGATIVE POSITION try going up
    // slightly, then move arm, then move elevator so you dont chooch stuff from
    // home
    addParallel(new ElevateToPreset(elevatorTarget, elevatorTargetWithTrigger, toggle, elevator), 3);
    addSequential(new ArticulateToPreset(target, targetWithTrigger, toggle, arm));

  }
}