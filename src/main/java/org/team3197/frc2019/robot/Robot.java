package org.team3197.frc2019.robot;

import org.team3197.frc2019.robot.commands.AlignTurn;
import org.team3197.frc2019.robot.commands.AutoClimb;
import org.team3197.frc2019.robot.commands.test.DriveTrainTest;
import org.team3197.frc2019.robot.subsystems.Arm;
import org.team3197.frc2019.robot.subsystems.CargoManipulator;
import org.team3197.frc2019.robot.subsystems.Climber;
import org.team3197.frc2019.robot.subsystems.DriveTrain;
import org.team3197.frc2019.robot.subsystems.Elevator;
import org.team3197.frc2019.robot.subsystems.Erector;
import org.team3197.frc2019.robot.subsystems.Hatch;

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
  public static final Arm arm = new Arm();
  public static final CargoManipulator manipulator = new CargoManipulator();
  public static final Hatch hatch = new Hatch();
  public static final Erector erector = new Erector();
  public static final Climber climber = new Climber();

  public static final AlignTurn alignTurn = new AlignTurn(driveTrain);
  public static final AutoClimb autoClimb = new AutoClimb(climber, erector);

  public static final PowerDistributionPanel pdp = new PowerDistributionPanel();

  public static final NetworkTableInstance ntInst = NetworkTableInstance.getDefault();

  public static final Preferences prefs = Preferences.getInstance();

  private static boolean resetEncoders = true;

  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Time", DriverStation.getInstance().getMatchTime());
    SmartDashboard.putNumber("verticalGyroSpeedReal", autoClimb.getAngle());
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
    Scheduler.getInstance().add(new DriveTrainTest(driveTrain));
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }

  private void reset() {
    arm.resetGyro.start();
    autoClimb.resetGyro.start();
    erector.resetPID.start();
    if (resetEncoders) {
      elevator.reset.start();
      arm.resetEncoder.start();
      resetEncoders = false;
    }
  }
}
