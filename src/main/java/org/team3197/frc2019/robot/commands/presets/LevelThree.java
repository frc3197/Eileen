package org.team3197.frc2019.robot.commands.presets;

import org.team3197.frc2019.robot.RobotMap.ElbowPreset;
import org.team3197.frc2019.robot.RobotMap.ElevatorPreset;
import org.team3197.frc2019.robot.commands.ExtendToPreset;
import org.team3197.frc2019.robot.commands.ElevateToPreset;
import org.team3197.frc2019.robot.subsystems.Elbow;
import org.team3197.frc2019.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LevelThree extends CommandGroup {

  public LevelThree(Elevator elevator, Elbow arm, Trigger toggle) {
    addParallel(
        new ElevateToPreset(ElevatorPreset.kHatchLevelThree, ElevatorPreset.kCargoLevelThree, toggle, elevator));
    addParallel(new ExtendToPreset(ElbowPreset.kHatchThree, ElbowPreset.kCargoThree, toggle, arm));
  }
}
