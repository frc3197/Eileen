package org.team3197.frc2019.robot.commands.presets;

import org.team3197.frc2019.robot.RobotMap.ElbowPreset;
import org.team3197.frc2019.robot.RobotMap.ElevatorPreset;
import org.team3197.frc2019.robot.commands.ArticulateToPreset;
import org.team3197.frc2019.robot.commands.ElevateToPreset;
import org.team3197.frc2019.robot.subsystems.Arm;
import org.team3197.frc2019.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LevelTwo extends CommandGroup {

  public LevelTwo(Elevator elevator, Arm arm, Trigger toggle) {
    // addSequential(new ElevateToPreset(ElevatorPreset.kIntermediate,
    // ElevatorPreset.kIntermediate, toggle, elevator));
    // addSequential(new ArticulateToPreset(ElbowPreset.kHatchTwo,
    // ElbowPreset.kCargoTwo, toggle, arm));
    // addSequential(new ElevateToPreset(ElevatorPreset.kHatchLevelTwo,
    // ElevatorPreset.kCargoLevelTwo, toggle, elevator));
    addParallel(new ArticulateToPreset(ElbowPreset.kHatchTwo, ElbowPreset.kCargoTwo, toggle, arm));
    addParallel(new ElevateToPreset(ElevatorPreset.kHatchLevelTwo, ElevatorPreset.kCargoLevelTwo, toggle, elevator));
  }
}
