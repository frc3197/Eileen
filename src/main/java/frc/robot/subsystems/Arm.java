/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Articulate;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  private CANSparkMax elbow = new CANSparkMax(RobotMap.CANSparkMaxID.ARM_ELBOW.id, MotorType.kBrushless);
  private CANSparkMax wrist = new CANSparkMax(RobotMap.CANSparkMaxID.ARM_WRIST.id, MotorType.kBrushless);

  public Arm() {
    super();
    // wrist.follow(elbow, true);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Articulate(this));
  }

  public void elbow(double speed) {
    if (Math.abs(speed) < RobotMap.deadband) {
      speed = 0;
    }
    // SmartDashboard.putNumber("elbow", speed);
    elbow.set(speed);
  }

  public void wrist(double speed) {
    if (Math.abs(speed) < RobotMap.deadband) {
      speed = 0;
    }
    // SmartDashboard.putNumber("wrist", speed);
    wrist.set(speed);
  }

  double resetEncoderPosition = 0;

  public void resetElbowPosition() {
    resetEncoderPosition = elbow.getEncoder().getPosition();
  }

  public void resetWristPosition() {
    resetEncoderPosition = wrist.getEncoder().getPosition();
  }

  public double getElbowEncoderPosition() {
    return elbow.getEncoder().getPosition() - resetEncoderPosition;
  }

  public double getWristEncoderPosition() {
    return wrist.getEncoder().getPosition() - resetEncoderPosition;
  }
}
