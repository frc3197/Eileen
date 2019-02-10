package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.defaults.Erect;

public class Erector extends Subsystem {

  private CANSparkMax left = new CANSparkMax(RobotMap.CANSparkMaxID.ERECTOR_LEFT.id, MotorType.kBrushless);
  private CANSparkMax right = new CANSparkMax(RobotMap.CANSparkMaxID.ERECTOR_RIGHT.id, MotorType.kBrushless);

  private SpeedControllerGroup erectorGroup = new SpeedControllerGroup(left, right);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Erect(this));
  }

  public void drive(double speed) {
    erectorGroup.set(speed);
  }
}
