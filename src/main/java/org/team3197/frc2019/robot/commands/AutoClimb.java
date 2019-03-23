package org.team3197.frc2019.robot.commands;

import org.team3197.frc2019.robot.RobotMap.Channel;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.RobotMap.GyroSensitivity;
import org.team3197.frc2019.robot.RobotMap.MaxSpeeds;
import org.team3197.frc2019.robot.subsystems.Climber;
import org.team3197.frc2019.robot.subsystems.Erector;
import org.team3197.frc2019.robot.utilities.FunctionCommand;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoClimb extends Command {
  private Climber climber;
  private Erector erector;

  private boolean finished;

  public AutoClimb(Climber climber, Erector erector) {
    requires(climber);
    requires(erector);
    this.climber = climber;
    this.erector = erector;
    finished = false;
  }

  @Override
  protected void initialize() {
    climber.resetGyro.start();
  }

  @Override
  protected void execute() {
    double deltaAngle = climber.getAngle();
    double gyroSpeedVert = GyroSensitivity.kArm.val * deltaAngle;
    double gyroErectorSpeed = GyroSensitivity.kArm.val * deltaAngle;

    /**
     * Should help with limiting the speeds so the robot dosent run motors at 100%
     */
    if (gyroSpeedVert > MaxSpeeds.kClimberVertical.forwardSpeed) {
      gyroSpeedVert = MaxSpeeds.kClimberVertical.forwardSpeed;
    }
    if (gyroErectorSpeed > MaxSpeeds.kErector.forwardSpeed) {
      gyroErectorSpeed = MaxSpeeds.kErector.forwardSpeed;
    }

    SmartDashboard.putNumber("deltaAngle", deltaAngle);
    SmartDashboard.putNumber("veritcalGyroSpeed", gyroSpeedVert);
    SmartDashboard.putNumber("erectorSpeedGyro", gyroErectorSpeed);

    /**
     * If the angle is negative, run the knives to correct. If the angle is
     * positive, run the vertical climber to correct.
     */
    double currentAngle = climber.getAngle();
    if (currentAngle < -DeadbandType.kClimberGyroVal.speed) {
      climber.driveVertical(gyroSpeedVert);
    } else if (currentAngle > DeadbandType.kClimberGyroVal.speed) {
      erector.drive(gyroErectorSpeed, true);
    } else {
      climber.driveVertical(MaxSpeeds.kClimberVertical.forwardSpeed * .4);
      erector.drive(MaxSpeeds.kErector.forwardSpeed * .25, true);
    }

  }

  @Override
  protected boolean isFinished() {
    return finished;
  }

  @Override
  protected void end() {
    erector.drive(0, true);
    climber.driveVertical(0);
  }

}
