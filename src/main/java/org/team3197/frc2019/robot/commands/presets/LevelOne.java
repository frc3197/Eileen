package org.team3197.frc2019.robot.commands.presets;

import org.team3197.frc2019.robot.RobotMap.ElbowPreset;
import org.team3197.frc2019.robot.RobotMap.ElevatorPreset;
import org.team3197.frc2019.robot.commands.ArticulateToPreset;
import org.team3197.frc2019.robot.commands.ElevateToPreset;
import org.team3197.frc2019.robot.subsystems.Arm;
import org.team3197.frc2019.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LevelOne extends CommandGroup {

  public LevelOne(Elevator elevator, Arm arm, Trigger toggle) {

    addParallel(new ElevateToPreset(ElevatorPreset.kHatchLevelOne, ElevatorPreset.kCargoLevelOne, toggle, elevator));
    addParallel(new ArticulateToPreset(ElbowPreset.kHatchOne, ElbowPreset.kCargoOne, toggle, arm));
  }
}
