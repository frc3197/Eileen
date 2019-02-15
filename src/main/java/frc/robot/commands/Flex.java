package frc.robot.commands;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap.ElevatorPreset;
import frc.robot.RobotMap.ArmPreset;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Elevator;

public class Flex extends CommandGroup {

  public Flex(ElevatorPreset elevatorTarget, ElevatorPreset elevatorTargetWithTrigger, ArmPreset wristTarget,
      ArmPreset wristTargetWithTrigger, Trigger toggle, Elevator elevator, Arm arm) {
    super();
  }
}
