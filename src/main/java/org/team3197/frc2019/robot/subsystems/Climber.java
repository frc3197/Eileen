package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.team3197.frc2019.robot.RobotMap.CANSparkMaxID;
import org.team3197.frc2019.robot.commands.defaults.Climb;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
  private CANSparkMax vertical = new CANSparkMax(CANSparkMaxID.kLiftVertical.id, MotorType.kBrushless);
  private CANSparkMax horizontal = new CANSparkMax(CANSparkMaxID.kLiftHorizontal.id, MotorType.kBrushless);

  public Climber() {
    super();
    vertical.setIdleMode(IdleMode.kBrake);
    horizontal.setIdleMode(IdleMode.kCoast);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Climb(this));
  }

  public void driveVertical(double speed) {
    vertical.set(speed);
  }

  public void driveHorizontal(double speed) {
    horizontal.set(speed);
  }
}
