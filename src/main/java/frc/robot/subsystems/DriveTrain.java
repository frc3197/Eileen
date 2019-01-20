package frc.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.RobotMap.CANSparkMaxID;
import frc.robot.commands.Drive;

public class DriveTrain extends Subsystem {

  public boolean arcadeDrive = false;

  private CANSparkMax flSparkMax = new CANSparkMax(CANSparkMaxID.FRONTLEFT.id, MotorType.kBrushless);
  private CANSparkMax blSparkMax = new CANSparkMax(CANSparkMaxID.BACKLEFT.id, MotorType.kBrushless);
  private CANSparkMax frSparkMax = new CANSparkMax(CANSparkMaxID.FRONTRIGHT.id, MotorType.kBrushless);
  private CANSparkMax brSparkMax = new CANSparkMax(CANSparkMaxID.BACKRIGHT.id, MotorType.kBrushless);

  private SpeedControllerGroup leftMaxes = new SpeedControllerGroup(flSparkMax, blSparkMax);
  private SpeedControllerGroup rightMaxes = new SpeedControllerGroup(frSparkMax, brSparkMax);

  private DifferentialDrive drive = new DifferentialDrive(leftMaxes, rightMaxes);

  // CANDigitalInput blSwitch =
  // blSparkMax.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen);
  // CANDigitalInput flSwitch =
  // flSparkMax.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen);
  // CANDigitalInput brSwitch =
  // brSparkMax.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen);
  // CANDigitalInput frSwitch =
  // frSparkMax.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen);

  // CANEncoder blEncoder = blSparkMax.getEncoder();
  // CANEncoder flEncoder = flSparkMax.getEncoder();
  // CANEncoder brEncoder = brSparkMax.getEncoder();
  // CANEncoder frEncoder = frSpar`kMax.getEncoder();

  private CANPIDController flPidContoller = flSparkMax.getPIDController();
  private CANPIDController blPidContoller = blSparkMax.getPIDController();
  private CANPIDController frPidContoller = frSparkMax.getPIDController();
  private CANPIDController brPidContoller = brSparkMax.getPIDController();

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive());
    drive.setDeadband(RobotMap.deadband);
  }

  // public void update() {
  // CANSparkMax[] sparkMaxes = { brSparkMax, blSparkMax, frSparkMax, brSparkMax
  // };
  // for (CANSparkMax sparkMax : sparkMaxes) {
  // SmartDashboard.putNumber("sparkMax" + sparkMax.getDeviceId() +
  // ".encoder.position",
  // blSparkMax.getEncoder().getPosition());
  // SmartDashboard.putNumber("sparkMax" + sparkMax.getDeviceId() +
  // ".encoder.velocity",
  // blSparkMax.getEncoder().getVelocity());
  // SmartDashboard.putBoolean("sparkMax" + sparkMax.getDeviceId() +
  // ".forwardLimit.",
  // sparkMax.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen).isLimitSwitchEnabled());
  // SmartDashboard.putBoolean("sparkMax" + sparkMax.getDeviceId() +
  // ".reverseLimit.",
  // sparkMax.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen).isLimitSwitchEnabled());
  // }
  // }`

  public void tankDrive(double l, double r) {
    drive.tankDrive(l, r, true);
  }

  public void arcadeDrive(double y, double r) {
    drive.arcadeDrive(y, r, true);
  }

}