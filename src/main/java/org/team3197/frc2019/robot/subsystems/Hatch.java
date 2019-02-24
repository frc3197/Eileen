package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.commands.defaults.Speak;

public class Hatch extends Subsystem implements Drivable {

  private CANSparkMax hatch = new CANSparkMax(RobotMap.CANSparkMaxID.kHatch.id, MotorType.kBrushed);

  public Hatch() {
    super();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Speak(this));
  }

  public void drive(double speed, boolean hold) {
    hatch.set(speed);
  }
}
