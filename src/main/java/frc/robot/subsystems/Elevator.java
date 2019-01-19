package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Elevate;

public class Elevator extends Subsystem {
  private CANSparkMax left = new CANSparkMax(6, MotorType.kBrushless);
  private CANSparkMax right = new CANSparkMax(5, MotorType.kBrushless);

  private SpeedControllerGroup elevatorGroup = new SpeedControllerGroup(left, right);

  public Elevator() {
    super();
    left.setInverted(true);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Elevate());
  }

  public void drive(double speed) {
    elevatorGroup.set(speed);
  }
}
