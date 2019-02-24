package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.commands.defaults.Manipulate;

public class CargoManipulator extends Subsystem implements Drivable {

  private CANSparkMax roller = new CANSparkMax(RobotMap.CANSparkMaxID.kCargoManipulator.id, MotorType.kBrushless);

  public CargoManipulator() {
    super();
    roller.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Manipulate(this));
  }

  public void drive(double speed, boolean hold) {
    roller.set(speed);
  }
}
