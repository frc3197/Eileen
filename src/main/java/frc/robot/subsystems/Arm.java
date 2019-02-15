/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.RobotMap.Channel;
import frc.robot.RobotMap.DeadbandType;
import frc.robot.commands.defaults.Articulate;

public class Arm extends Subsystem {

  private CANSparkMax elbow = new CANSparkMax(RobotMap.CANSparkMaxID.kElbow.id, MotorType.kBrushless);
  private CANSparkMax wrist = new CANSparkMax(RobotMap.CANSparkMaxID.kWrist.id, MotorType.kBrushless);

  private CANDigitalInput elbowLimit = elbow.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);
  private CANDigitalInput wristLimit = wrist.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyOpen);

  public AnalogGyro gyro = new AnalogGyro(Channel.kWristGyro.channel);

  public ResetEncoderPosition reset = new ResetEncoderPosition(this);
  public ResetGyro resetGyro = new ResetGyro(gyro);

  private double lastEncoder;

  public Arm() {
    super();
    // wrist.follow(elbow, true);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Articulate(this));
  }

  public void elbow(double speed) {
    double output = speed;
    // if (!elbowLimit.get() && Math.abs(output) < DeadbandType.kElbow.speed) {
    // output = DeadbandType.kElbow.speed;
    // }
    SmartDashboard.putNumber("getElbowEncoderPosition", getElbowEncoderPosition());
    elbow.set(output);
  }

  public void wrist(double speed) {
    double output = speed;

    // gyro mode
    if (!wristLimit.get() && Math.abs(output) < DeadbandType.kWrist.speed) {

    }
    SmartDashboard.putNumber("getWristEncoderPosition", getWristEncoderPosition());
    wrist.set(output);
  }

  private class ResetGyro extends InstantCommand {
    private Gyro gyro;

    ResetGyro(Gyro gyro) {
      super();
      this.gyro = gyro;
    }

    @Override
    public void initialize() {
      gyro.reset();
    }
  }

  // TODO change when spark max releases encoder reset

  double resetWristEncoderPosition = 0;
  double resetElbowEncoderPosition = 0;

  public double getElbowEncoderPosition() {
    return elbow.getEncoder().getPosition() - resetElbowEncoderPosition;
  }

  public double getWristEncoderPosition() {
    return wrist.getEncoder().getPosition() - resetWristEncoderPosition;
  }

  private void resetElevatorPosition() {
    resetElbowEncoderPosition = elbow.getEncoder().getPosition();
    resetWristEncoderPosition = wrist.getEncoder().getPosition();
  }

  // private void neutralizeElbowGravity() {
  // double desiredElbow = elbow.getEncoder().getPosition();
  // if (desiredElbow < elbow.getEncoder().getPosition()) {
  // elbow.set(DeadbandType.kElbow.speed);
  // } else if (desiredElbow > elbow.getEncoder().getPosition()) {
  // elbow.set(-DeadbandType.kElbow.speed);
  // }
  // }
  public double gravBreak(double encoder, double controlIn) {
    if ((Math.abs(controlIn) <= .05)) {
      double ret = ((lastEncoder - encoder) / encoder);
      lastEncoder = encoder;
      return ret;
    }
    return controlIn;
  }

  public class ResetEncoderPosition extends InstantCommand {

    private Arm arm;

    public ResetEncoderPosition(Arm arm) {
      requires(arm);
      this.arm = arm;
    }

    @Override
    protected void initialize() {
      arm.resetElevatorPosition();
    }
  }
}

// private void neutralizeWristGravity() {
// double desiredWrist = wrist.getEncoder().getPosition();
// if (desiredWrist < wrist.getEncoder().getPosition()) {
// wrist.set(DeadbandType.kWrist.speed);
// } else if (desiredWrist > wrist.getEncoder().getPosition()) {
// wrist.set(-DeadbandType.kWrist.speed);
// }
// }d