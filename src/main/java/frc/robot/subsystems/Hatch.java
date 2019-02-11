package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.defaults.Speak;

public class Hatch extends Subsystem {

  private CANSparkMax hatch = new CANSparkMax(RobotMap.CANSparkMaxID.kHatch.id, MotorType.kBrushed);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Speak(this));
  }

  public void drive(double speed) {
    hatch.set(speed);
  }
}
