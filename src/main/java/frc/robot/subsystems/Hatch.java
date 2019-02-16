package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotMap;
import frc.robot.commands.defaults.Speak;

public class Hatch extends IntermediateSubystem {

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
