package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.commands.defaults.Manipulate;
import org.team3197.frc2019.robot.utilities.Drivable;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem implements Drivable {

  private CANSparkMax roller = new CANSparkMax(RobotMap.CANSparkMaxID.kIntake.id, MotorType.kBrushless);

  public Intake() {
    roller.setIdleMode(IdleMode.kBrake);
    roller.setInverted(true);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Manipulate(this));
  }

  public void drive(double speed, boolean hold) {
    roller.set(speed);
  }
}
