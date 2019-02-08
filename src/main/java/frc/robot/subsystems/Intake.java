package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Roll;

public class Intake extends Subsystem {

  private CANSparkMax roller = new CANSparkMax(RobotMap.CANSparkMaxID.INTAKE.id, MotorType.kBrushless);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Roll(this));
  }

  public void drive(double speed) {
    roller.set(speed);
  }
}
