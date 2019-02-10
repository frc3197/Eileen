package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Hatch extends Subsystem {

  private CANSparkMax hatch = new CANSparkMax(RobotMap.CANSparkMaxID.ERECTOR_LEFT.id, MotorType.kBrushed);

  @Override
  public void initDefaultCommand() {

    setDefaultCommand(new MySpecialCommand());
  }

  public void drive(double speed) {
    hatch.set(speed);
  }
}
