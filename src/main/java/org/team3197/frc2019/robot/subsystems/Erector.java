package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.commands.defaults.Erect;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Erector extends Subsystem implements Drivable {

  private CANSparkMax left = new CANSparkMax(RobotMap.CANSparkMaxID.kErectorLeft.id, MotorType.kBrushless);
  private CANSparkMax right = new CANSparkMax(RobotMap.CANSparkMaxID.kErectorRight.id, MotorType.kBrushless);

  private SpeedControllerGroup erectorGroup = new SpeedControllerGroup(left, right);

  public Erector() {
    super();

    left.setIdleMode(IdleMode.kBrake);
    right.setIdleMode(IdleMode.kBrake);

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
