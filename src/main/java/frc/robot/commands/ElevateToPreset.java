/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import frc.robot.RobotMap;
import frc.robot.RobotMap.ElevatorPreset;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
=======
>>>>>>> parent of 2ae4e7c... elevator goto position implemented
=======
>>>>>>> parent of 2ae4e7c... elevator goto position implemented
=======
>>>>>>> parent of 2ae4e7c... elevator goto position implemented

public class ElevateToPreset extends Command {
  public ElevateToPreset() {
    requires(Robot.elevator);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    SmartDashboard.putNumber("Elevator Encoder", Robot.elevator.getEncoderPosition());
    double error = Robot.elevator.getEncoderPosition() - preset.pos;
    finished = Math.abs(error) < RobotMap.elevatorPresetThreshold;
    double speed = -Math.copySign(Math.pow(error, 2), error); // TODO check polarity
    Robot.elevator.drive(speed);
=======
>>>>>>> parent of 2ae4e7c... elevator goto position implemented
=======
>>>>>>> parent of 2ae4e7c... elevator goto position implemented
=======
>>>>>>> parent of 2ae4e7c... elevator goto position implemented
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

}
