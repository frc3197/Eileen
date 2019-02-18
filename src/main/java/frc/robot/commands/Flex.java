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
    // then move arm, then move elevator so you dont chooch stuff from home
    addParallel(new ElevateToPreset(elevatorTarget, elevatorTargetWithTrigger, toggle, elevator), 3);
    addSequential(new ArticulateToPreset(target, targetWithTrigger, toggle, arm));

  }

  private double getUpSpeed(Elevator elevator) {
    double currentTarget = elevator.getEncoderPosition() + 5;

    double error = elevator.getEncoderPosition() - currentTarget;

    double speed = -RobotMap.elevatorDegreeSensitivity
        * Math.copySign(Math.pow(Math.abs(error), RobotMap.elevatorExponent), error);
    return speed;
  }
}