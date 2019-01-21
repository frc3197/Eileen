package frc.robot.subsystems;

import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;

import java.util.HashMap;
import java.util.Map;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

  // private CANEncoder blEncoder = blSparkMax.getEncoder();
  // private CANEncoder flEncoder = flSparkMax.getEncoder();
  // private CANEncoder brEncoder = brSparkMax.getEncoder();
  // private CANEncoder frEncoder = frSparkMax.getEncoder();

  // private CANPIDController flPidContoller = flSparkMax.getPIDController();
  // private CANPIDController blPidContoller = blSparkMax.getPIDController();
  // private CANPIDController frPidContoller = frSparkMax.getPIDController();
  // private CANPIDController brPidContoller = brSparkMax.getPIDController();

  HashMap<CANSparkMax, CANDigitalInput> sparkMaxPrimaryLimitSwitches = new HashMap<CANSparkMax, CANDigitalInput>();
  HashMap<CANSparkMax, CANDigitalInput> sparkMaxSecondaryLimitSwitches = new HashMap<CANSparkMax, CANDigitalInput>();

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive());
    drive.setDeadband(RobotMap.deadband);

    sparkMaxPrimaryLimitSwitches.put(flSparkMax, flSparkMax.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen));
    sparkMaxPrimaryLimitSwitches.put(blSparkMax, blSparkMax.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen));
    sparkMaxPrimaryLimitSwitches.put(frSparkMax, frSparkMax.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen));
    sparkMaxPrimaryLimitSwitches.put(brSparkMax, brSparkMax.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen));

    sparkMaxSecondaryLimitSwitches.put(flSparkMax, flSparkMax.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen));
    sparkMaxSecondaryLimitSwitches.put(blSparkMax, blSparkMax.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen));
    sparkMaxSecondaryLimitSwitches.put(frSparkMax, frSparkMax.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen));
    sparkMaxSecondaryLimitSwitches.put(brSparkMax, brSparkMax.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyOpen));

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

}