/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Rotate;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  private CANSparkMax wrist = new CANSparkMax(RobotMap.CANSparkMaxID.WRIST.id, MotorType.kBrushless);
  private CANSparkMax elbow = new CANSparkMax(RobotMap.CANSparkMaxID.ELBOW.id, MotorType.kBrushless);

  private SpeedControllerGroup armGroup = new SpeedControllerGroup(wrist, elbow);

  public Arm() {
    super();
    wrist.setInverted(true);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Rotate(this));
  }

  public void drive(double speed) {
    armGroup.set(speed);
  }
}
