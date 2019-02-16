package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.RobotMap;
import frc.robot.commands.defaults.Erect;

public class Erector extends IntermediateSubystem {

  private CANSparkMax left = new CANSparkMax(RobotMap.CANSparkMaxID.kErectorLeft.id, MotorType.kBrushless);
  private CANSparkMax right = new CANSparkMax(RobotMap.CANSparkMaxID.kErectorRight.id, MotorType.kBrushless);

  private SpeedControllerGroup erectorGroup = new SpeedControllerGroup(left, right);

  public Erector() {
    super();
    right.setInverted(true);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Erect(this));
  }

  public void drive(double speed, boolean hold) {
    erectorGroup.set(speed);
  }
}
