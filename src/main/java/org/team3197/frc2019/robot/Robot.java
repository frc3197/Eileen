package org.team3197.frc2019.robot;

import org.team3197.frc2019.robot.subsystems.Climber;
import org.team3197.frc2019.robot.subsystems.DriveTrain;
import org.team3197.frc2019.robot.subsystems.Elbow;
import org.team3197.frc2019.robot.subsystems.Elevator;
import org.team3197.frc2019.robot.subsystems.Erector;
import org.team3197.frc2019.robot.subsystems.Hatch;
import org.team3197.frc2019.robot.subsystems.Intake;
import org.team3197.frc2019.robot.subsystems.Wrist;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

  public static final DriveTrain driveTrain = new DriveTrain();
  public static final Elevator elevator = new Elevator();
  public static final Elbow elbow = new Elbow();
  public static final Wrist wrist = new Wrist();
  public static final Intake manipulator = new Intake();
  public static final Hatch hatch = new Hatch();
  public static final Erector erector = new Erector();
  public static final Climber climber = new Climber();

  public static final PowerDistributionPanel pdp = new PowerDistributionPanel();

  public static final NetworkTableInstance ntInst = NetworkTableInstance.getDefault();

  public static final Preferences prefs = Preferences.getInstance();

  private static boolean resetEncoders = true;

  @Override
  public void robotInit() {
    CameraServer.getInstance().addAxisCamera("driver-camera", "10.31.97.7");
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Time", DriverStation.getInstance().getMatchTime());
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
    reset();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    reset();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    reset();
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }

  private void reset() {
    wrist.resetGyro.start();
    erector.resetPID.start();
    if (resetEncoders) {
      elevator.reset.start();
      elbow.resetEncoder.start();
      resetEncoders = false;
    }
  }
}
