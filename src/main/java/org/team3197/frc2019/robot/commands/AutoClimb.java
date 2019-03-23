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

  public AnalogGyro gyro = new AnalogGyro(Channel.kClimberGyro.channel);

  public FunctionCommand resetGyro = new FunctionCommand(this::resetGyroAngle);

  public AutoClimb(Climber climber, Erector erector) {
    requires(climber);
    requires(erector);
    this.climber = climber;
    this.erector = erector;
    finished = false;
  }

  @Override
  protected void execute() {
    double deltaAngle = getAngle();
    double gyroSpeedVert = GyroSensitivity.kArm.val * deltaAngle;
    double gyroSpeedHori = GyroSensitivity.kArm.val * deltaAngle;

    /**
     * Should help with limiting the speeds so the robot dosent run motors at 100%
     */
    if (gyroSpeedVert > MaxSpeeds.kClimberVertical.forwardSpeed) {
      gyroSpeedVert = MaxSpeeds.kClimberVertical.forwardSpeed;
    }
    if (gyroSpeedHori > MaxSpeeds.kClimberHorizontal.forwardSpeed) {
      gyroSpeedHori = MaxSpeeds.kClimberHorizontal.forwardSpeed;
    }

    SmartDashboard.putNumber("deltaAngle", deltaAngle);
    SmartDashboard.putNumber("veritcalGyroSpeed", gyroSpeedVert);
    SmartDashboard.putNumber("horizontalGyroSpeed", gyroSpeedHori);

    /**
     * If the angle is negative, run the knives to correct. If the angle is
     * positive, run the vertical climber to correct.
     */
    if (getAngle() < DeadbandType.kClimberGyroVal.speed) {
      erector.drive(gyroSpeedHori, true);
    } else if (getAngle() > DeadbandType.kClimberGyroVal.speed) {
      climber.driveVertical(gyroSpeedVert);
    } else {
      resetGyroAngle();
    }

    finished = true;
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

  private double getAngle() {
    return gyro.getAngle();
  }

  private void resetGyroAngle() {
    gyro.reset();
  }
}
