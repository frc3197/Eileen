package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Erector extends Subsystem {

  private CANSparkMax left = new CANSparkMax(RobotMap.CANSparkMaxID.ERECTOR_LEFT.id, MotorType.kBrushless);
  private CANSparkMax right = new CANSparkMax(RobotMap.CANSparkMaxID.ERECTOR_RIGHT.id, MotorType.kBrushless);

  @Override
  public void initDefaultCommand() {

  }
}
