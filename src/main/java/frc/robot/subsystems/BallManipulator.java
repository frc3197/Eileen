package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Manipulate;

public class BallManipulator extends Subsystem {

  private CANSparkMax roller = new CANSparkMax(RobotMap.CANSparkMaxID.ARM_BALL_MANIPULATOR.id, MotorType.kBrushless);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Manipulate(this));
  }

  public void drive(double speed) {
    roller.set(speed);
  }
}
