package frc.robot.subsystems;

import java.util.HashMap;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.RobotMap.CANSparkMaxID;
import frc.robot.RobotMap.Channel;
import frc.robot.RobotMap.DeadbandType;
import frc.robot.RobotMap.GyroSensitivity;
import frc.robot.commands.defaults.Drive;

public class DriveTrain extends Subsystem {

  public boolean arcadeDrive = true;
  public boolean useGyro = false;

  public AnalogGyro gyro = new AnalogGyro(Channel.kDriveGyro.channel);

  // Motor Controllers
  private CANSparkMax flSparkMax = new CANSparkMax(CANSparkMaxID.kFrontLeft.id, MotorType.kBrushless);
  private CANSparkMax blSparkMax = new CANSparkMax(CANSparkMaxID.kBackLeft.id, MotorType.kBrushless);
  private CANSparkMax frSparkMax = new CANSparkMax(CANSparkMaxID.kFrontRight.id, MotorType.kBrushless);
  private CANSparkMax brSparkMax = new CANSparkMax(CANSparkMaxID.kBackRight.id, MotorType.kBrushless);

  private SpeedControllerGroup leftMaxes = new SpeedControllerGroup(flSparkMax, blSparkMax);
  private SpeedControllerGroup rightMaxes = new SpeedControllerGroup(frSparkMax, brSparkMax);

  private DifferentialDrive drive = new DifferentialDrive(leftMaxes, rightMaxes);

  HashMap<CANSparkMax, CANDigitalInput> sparkMaxPrimaryLimitSwitches = new HashMap<CANSparkMax, CANDigitalInput>();
  HashMap<CANSparkMax, CANDigitalInput> sparkMaxSecondaryLimitSwitches = new HashMap<CANSparkMax, CANDigitalInput>();

  public ResetCommand changeDriveGryo = new ResetCommand(this::toggleGyro);
  public ResetCommand changeDriveMode = new ResetCommand(this::toggleMode);

  public DriveTrain() {
    super();
    drive.setDeadband(DeadbandType.kDrive.speed);

    flSparkMax.setIdleMode(IdleMode.kCoast);
    flSparkMax.setIdleMode(IdleMode.kCoast);
    flSparkMax.setIdleMode(IdleMode.kCoast);
    flSparkMax.setIdleMode(IdleMode.kCoast);

    sparkMaxPrimaryLimitSwitches.put(flSparkMax, flSparkMax.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen));
    sparkMaxPrimaryLimitSwitches.put(blSparkMax, blSparkMax.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen));
    sparkMaxPrimaryLimitSwitches.put(frSparkMax, frSparkMax.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen));
    sparkMaxPrimaryLimitSwitches.put(brSparkMax, brSparkMax.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen));

    sparkMaxSecondaryLimitSwitches.put(flSparkMax, flSparkMax.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen));
    sparkMaxSecondaryLimitSwitches.put(blSparkMax, blSparkMax.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen));
    sparkMaxSecondaryLimitSwitches.put(frSparkMax, frSparkMax.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen));
    sparkMaxSecondaryLimitSwitches.put(brSparkMax, brSparkMax.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen));

    CANPIDController flPIDController = flSparkMax.getPIDController();
    CANPIDController blPIDController = blSparkMax.getPIDController();
    CANPIDController frPIDController = frSparkMax.getPIDController();
    CANPIDController brPIDController = brSparkMax.getPIDController();

    flPIDController.setP(RobotMap.CANSparkPID.P.val);
    blPIDController.setP(RobotMap.CANSparkPID.P.val);
    frPIDController.setP(RobotMap.CANSparkPID.P.val);
    brPIDController.setP(RobotMap.CANSparkPID.P.val);

    flPIDController.setI(RobotMap.CANSparkPID.I.val);
    blPIDController.setI(RobotMap.CANSparkPID.I.val);
    frPIDController.setI(RobotMap.CANSparkPID.I.val);
    brPIDController.setI(RobotMap.CANSparkPID.I.val);

    flPIDController.setD(RobotMap.CANSparkPID.D.val);
    blPIDController.setD(RobotMap.CANSparkPID.D.val);
    frPIDController.setD(RobotMap.CANSparkPID.D.val);
    brPIDController.setD(RobotMap.CANSparkPID.D.val);

    flPIDController.setFF(RobotMap.CANSparkPID.F.val);
    blPIDController.setFF(RobotMap.CANSparkPID.F.val);
    frPIDController.setFF(RobotMap.CANSparkPID.F.val);
    brPIDController.setFF(RobotMap.CANSparkPID.F.val);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive(this));
  }

  public void tankDrive(double l, double r) {
    drive.tankDrive(l, r, true);
  }

  private boolean goingStraightPrevious = false;
  private double initialGyroAngle;

  public void arcadeDrive(double y, double r) {
    if (useGyro) {
      gyroDrive(y, r);
    } else {
      drive.arcadeDrive(y, r, true);
    }
  }

  private void gyroDrive(double y, double r) {
    if (goingStraight(y, r)) {
      if (!goingStraightPrevious) { // rising edge
        initialGyroAngle = gyro.getAngle();
        goingStraightPrevious = true;
      }
      double currentGyroAngle = gyro.getAngle();
      // TODO: Check polarity
      double deltaAngle = (currentGyroAngle - initialGyroAngle);
      r += GyroSensitivity.kDrive.val * Math.copySign(Math.pow(deltaAngle, 2), deltaAngle);
      SmartDashboard.putNumber("deltaAngle", deltaAngle);
      SmartDashboard.putNumber("r", r);
    } else {
      goingStraightPrevious = false;
    }
    drive.arcadeDrive(y, r, true);
  }

  private boolean goingStraight(double y, double r) {
    return (Math.abs(r) < DeadbandType.kDrive.speed);
  }

  private void toggleGyro() {
    useGyro = !useGyro;
  }

  private void toggleMode() {
    arcadeDrive = !arcadeDrive;
  }

}