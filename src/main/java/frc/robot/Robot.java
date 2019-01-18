package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot {
  public static DriveTrain driveTrain = new DriveTrain();

  public static NetworkTableInstance nt = NetworkTableInstance.getDefault();
  public static NetworkTable table;

  @Override
  public void robotInit() {
    table = nt.getTable("SmartDashboard");
    nt.startClientTeam(3197);
    table.getEntry("X").setDouble(10);
  }

  @Override
  public void robotPeriodic() {
    System.out.println(table.getEntry("X").getDouble(-10));
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
