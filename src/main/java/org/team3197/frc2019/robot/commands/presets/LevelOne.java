package org.team3197.frc2019.robot.commands.presets;

import org.team3197.frc2019.robot.RobotMap.ElbowPreset;
import org.team3197.frc2019.robot.RobotMap.ElevatorPreset;
import org.team3197.frc2019.robot.commands.ExtendToPreset;
import org.team3197.frc2019.robot.commands.ElevateToPreset;
import org.team3197.frc2019.robot.subsystems.Elbow;
import org.team3197.frc2019.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LevelOne extends CommandGroup {

  public LevelOne(Elevator elevator, Elbow arm, Trigger toggle) {

    addParallel(new ElevateToPreset(ElevatorPreset.kHatchLevelOne, ElevatorPreset.kCargoLevelOne, toggle, elevator));
    addParallel(new ExtendToPreset(ElbowPreset.kHatchOne, ElbowPreset.kCargoOne, toggle, arm));
  }
}
