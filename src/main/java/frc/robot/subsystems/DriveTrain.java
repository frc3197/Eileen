package frc.robot.subsystems;

import java.util.HashMap;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.RobotMap.CANSparkMaxID;
import frc.robot.commands.Drive;

public class DriveTrain extends Subsystem implements PIDOutput {

  public boolean arcadeDrive = false;

  private CANSparkMax flSparkMax = new CANSparkMax(CANSparkMaxID.FRONTLEFT.id, MotorType.kBrushless);
  private CANSparkMax blSparkMax = new CANSparkMax(CANSparkMaxID.BACKLEFT.id, MotorType.kBrushless);
  private CANSparkMax frSparkMax = new CANSparkMax(CANSparkMaxID.FRONTRIGHT.id, MotorType.kBrushless);
  private CANSparkMax brSparkMax = new CANSparkMax(CANSparkMaxID.BACKRIGHT.id, MotorType.kBrushless);

  private SpeedControllerGroup leftMaxes = new SpeedControllerGroup(flSparkMax, blSparkMax);
  private SpeedControllerGroup rightMaxes = new SpeedControllerGroup(frSparkMax, brSparkMax);

  private DifferentialDrive drive = new DifferentialDrive(leftMaxes, rightMaxes);

  // private CANEncoder blEncoder = blSparkMax.getEncoder();
  // private CANEncoder flEncoder = flSparkMax.getEncoder();
  // private CANEncoder brEncoder = brSparkMax.getEncoder();
  // private CANEncoder frEncoder = frSparkMax.getEncoder();

  HashMap<CANSparkMax, CANDigitalInput> sparkMaxPrimaryLimitSwitches = new HashMap<CANSparkMax, CANDigitalInput>();
  HashMap<CANSparkMax, CANDigitalInput> sparkMaxSecondaryLimitSwitches = new HashMap<CANSparkMax, CANDigitalInput>();

  public DriveTrain() {
    super();
    drive.setDeadband(RobotMap.deadband);

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
    setDefaultCommand(new Drive());
  }

  public void update() {
    CANSparkMax[] sparkMaxes = { brSparkMax, blSparkMax, frSparkMax, brSparkMax };
    double[] ids = new double[sparkMaxes.length];
    double[] encoderPosition = new double[sparkMaxes.length];
    double[] encoderVelocity = new double[sparkMaxes.length];
    boolean[] primaryLimits = new boolean[sparkMaxes.length];
    boolean[] secondaryLimits = new boolean[sparkMaxes.length];
    for (int i = 0; i < sparkMaxes.length; i++) {
      ids[i] = sparkMaxes[i].getDeviceId();
      CANEncoder encoder = sparkMaxes[i].getEncoder();
      encoderPosition[i] = encoder.getPosition();
      encoderVelocity[i] = encoder.getVelocity();
      primaryLimits[i] = sparkMaxPrimaryLimitSwitches.get(sparkMaxes[i]).get();
      secondaryLimits[i] = sparkMaxSecondaryLimitSwitches.get(sparkMaxes[i]).get();
    }
    SmartDashboard.putNumberArray("driveTrainSparkMaxIds", ids);
    SmartDashboard.putBooleanArray("driveTrainSparkMaxPrimaryLimits", primaryLimits);
    SmartDashboard.putBooleanArray("driveTrainSparkMaxSecondaryLimits", secondaryLimits);
    SmartDashboard.putNumberArray("driveTrainSparkMaxEncoderPosition", encoderPosition);
    SmartDashboard.putNumberArray("driveTrainSparkMaxEncoderVelocity", encoderVelocity);
  }

  public void tankDrive(double l, double r) {
    drive.tankDrive(l, r, true);
  }

  public void arcadeDrive(double y, double r) {
    drive.arcadeDrive(y, r, true);
  }

  /**
   * VisionOutput
   */
  public void pidWrite(double output) {
    arcadeDrive(0, output);
  }

}