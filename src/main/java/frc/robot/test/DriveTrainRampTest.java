package frc.robot.test;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap.DriveTrainSide;
import frc.robot.subsystems.DriveTrain;

public class DriveTrainRampTest extends Command {

  private double[] speeds;
  private double duration;
  private DriveTrainSide side;
  private DriveTrain driveTrain;

  private Timer timer;

  private double timeInterval;

  /**
  *   
  */
  public DriveTrainRampTest(double[] speed, double duration, DriveTrainSide side, DriveTrain driveTrain) {
    requires(driveTrain);
    this.speeds = speed;
    this.duration = duration;
    this.side = side;
    this.driveTrain = driveTrain;
    timer = new Timer();
    timeInterval = duration / (speeds.length - 1);
  }

  @Override
  protected void initialize() {
    timer.start();
  }

  /**
   * Based on which side is given, it sets the speed of the motor to the supplied
   * value
   */
  @Override
  protected void execute() {
    double speed = getSpeed(timer.get());
    switch (side) {
    case LEFT:
      driveTrain.tankDrive(speed, 0);
      break;
    case RIGHT:
      driveTrain.tankDrive(0, speed);
      break;
    case BOTH:
      driveTrain.tankDrive(speed, speed);
      break;
    }
  }

  private double getSpeed(double time) {
    int index = (int) ((time / duration) * speeds.length);
    if (index == speeds.length - 1) {
      index--;
    }
    double startSpeed = speeds[index];
    double endSpeed = speeds[index + 1];
    double lastTime = index * duration / speeds.length;
    double speed = (endSpeed - startSpeed) * (time - lastTime) / timeInterval;
    return speed;
  }

  @Override
  protected boolean isFinished() {
    return timer.hasPeriodPassed(duration);
  }

  @Override
  protected void end() {
    driveTrain.tankDrive(0, 0);
  }
}
