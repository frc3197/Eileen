package org.team3197.frc2019.robot.commands.presets;

import org.team3197.frc2019.robot.RobotMap.ElbowPreset;
import org.team3197.frc2019.robot.RobotMap.ElevatorPreset;
import org.team3197.frc2019.robot.commands.ExtendToPreset;
import org.team3197.frc2019.robot.commands.ElevateToPreset;
import org.team3197.frc2019.robot.subsystems.Elbow;
import org.team3197.frc2019.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Cargo extends CommandGroup {

  public Cargo(Elevator elevator, Elbow arm, Trigger toggle) {
    addParallel(new ExtendToPreset(ElbowPreset.kCargoShip, ElbowPreset.kCargoShip, toggle, arm));
    addParallel(
        new ElevateToPreset(ElevatorPreset.kCargoShipCargo, ElevatorPreset.kCargoLoadingLevel, toggle, elevator));
  }
}
