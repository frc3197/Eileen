package frc.robot.commands;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.RobotMap.ArmPreset;
import frc.robot.RobotMap.ElevatorPreset;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Elevator;

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
    // slightly,
    // then move arm, then move elevator so you dont chooch stuff from 0
    if (Math.abs(elevator.getEncoderPosition()) < RobotMap.elevatorPresetThreshold) {
      addSequential(new ElevateToPreset(elevatorTarget, elevatorTargetWithTrigger, toggle, elevator));
      addSequential(new ArticulateToPreset(target, targetWithTrigger, toggle, arm), 5);
    } else if (elevatorTarget.pos < 0) {
      addSequential(new ArticulateToPreset(target, targetWithTrigger, toggle, arm));
      addSequential(new ElevateToPreset(elevatorTarget, elevatorTargetWithTrigger, toggle, elevator), 5);
    } else {
      addSequential(new ElevateToPreset(elevatorTarget, elevatorTargetWithTrigger, toggle, elevator));
      addSequential(new ArticulateToPreset(target, targetWithTrigger, toggle, arm), 5);
    }
  }
}