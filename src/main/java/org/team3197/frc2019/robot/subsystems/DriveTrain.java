package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.team3197.frc2019.robot.RobotMap.CANSparkMaxID;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.commands.defaults.Drive;
import org.team3197.frc2019.robot.utilities.FunctionCommand;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

  public boolean arcadeDrive = true;
  public boolean useGyro = false;

  // public AnalogGyro gyro = new AnalogGyro(Channel.kDriveGyro.channel);

  // Motor Controllers
  private CANSparkMax flSparkMax = new CANSparkMax(CANSparkMaxID.kFrontLeft.id, MotorType.kBrushless);
  private CANSparkMax blSparkMax = new CANSparkMax(CANSparkMaxID.kBackLeft.id, MotorType.kBrushless);
  private CANSparkMax frSparkMax = new CANSparkMax(CANSparkMaxID.kFrontRight.id, MotorType.kBrushless);
  private CANSparkMax brSparkMax = new CANSparkMax(CANSparkMaxID.kBackRight.id, MotorType.kBrushless);

  private SpeedControllerGroup leftMaxes = new SpeedControllerGroup(flSparkMax, blSparkMax);
  private SpeedControllerGroup rightMaxes = new SpeedControllerGroup(frSparkMax, brSparkMax);

  private DifferentialDrive drive = new DifferentialDrive(leftMaxes, rightMaxes);

  public FunctionCommand changeDriveMode = new FunctionCommand(this::toggleMode);

  public DriveTrain() {
    super();

    flSparkMax.setIdleMode(IdleMode.kBrake);
    blSparkMax.setIdleMode(IdleMode.kBrake);
    frSparkMax.setIdleMode(IdleMode.kBrake);
    brSparkMax.setIdleMode(IdleMode.kBrake);

    drive.setDeadband(DeadbandType.kDrive.speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive(this));
  }

  public void tankDrive(double l, double r) {
    drive.tankDrive(l, r, true);
  }

  public void arcadeDrive(double y, double r) {
    drive.arcadeDrive(y, r, true);
  }

  private void toggleMode() {
    arcadeDrive = !arcadeDrive;
  }

}