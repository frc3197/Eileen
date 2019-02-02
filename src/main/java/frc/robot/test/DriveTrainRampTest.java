package frc.robot.test;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap.DriveTrainSide;

public class DriveTrainRampTest extends Command {

  private double[] speeds;
  private double duration;
  private DriveTrainSide side;
  private Timer timer;

  private double timeInterval;

  /**
   * Constructor that builds the speeds and total ramp up/ramp down duraation for
   * a set side
   * 
   * @param speed
   * @param duration
   * @param side
   */
  public DriveTrainRampTest(double[] speed, double duration, DriveTrainSide side) {
    requires(Robot.driveTrain);
    this.speeds = speed;
    this.duration = duration;
    this.side = side;
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
      Robot.driveTrain.tankDrive(speed, 0);
      break;
    case RIGHT:
      Robot.driveTrain.tankDrive(0, speed);
      break;
    case BOTH:
      Robot.driveTrain.tankDrive(speed, speed);
      break;
    }
  }

  /**
   * Returns the test speed
   * 
   * @param time
   * @return
   */
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

  /**
   * Returns whether the duration has passed or not
   */
  @Override
  protected boolean isFinished() {
    return timer.hasPeriodPassed(duration);
  }

  @Override
  protected void end() {
    Robot.driveTrain.tankDrive(0, 0);
  }
}
