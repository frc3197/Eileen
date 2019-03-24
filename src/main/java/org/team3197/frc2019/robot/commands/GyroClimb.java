package org.team3197.frc2019.robot.commands;

import org.team3197.frc2019.robot.RobotMap.GyroSensitivity;
import org.team3197.frc2019.robot.RobotMap.MaxSpeeds;
import org.team3197.frc2019.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class GyroClimb extends Command {

  private Climber climber;

  public GyroClimb(Climber climber) {
    requires(climber);
    this.climber = climber;
  }

  @Override
  protected void initialize() {
    climber.resetGyro.start();
  }

  @Override
  protected void execute() {
    double speed = GyroSensitivity.kArm.val * climber.getAngle();
    speed = Math.max(MaxSpeeds.kClimberVertical.reverseSpeed, Math.min(MaxSpeeds.kClimberVertical.forwardSpeed, speed));
    climber.driveVertical(speed);
    // climber.setReferenceVertical(, ControlType.kDutyCycle);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    climber.driveVertical(0);
  }
}
