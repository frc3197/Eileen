package org.team3197.frc2019.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.Channel;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.RobotMap.GyroSensitivity;
import org.team3197.frc2019.robot.commands.defaults.Flex;
import org.team3197.frc2019.robot.utilities.Drivable;
import org.team3197.frc2019.robot.utilities.FunctionCommand;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Wrist extends Subsystem implements Drivable {
  private CANSparkMax wrist = new CANSparkMax(RobotMap.CANSparkMaxID.kWrist.id, MotorType.kBrushless);

  public AnalogGyro gyro = new AnalogGyro(Channel.kWristGyro.channel);

  public FunctionCommand resetGyro = new FunctionCommand(this::resetGyroAngle);

  public FunctionCommand toggleGyro = new FunctionCommand(this::toggleGyro);

  private boolean useGyro = true;

  public Wrist() {
    final double kP = 5e-5;
    final double kI = 1e-6;
    final double kD = 0;
    final double kIz = 0;
    final double kFF = 0.000156;

    wrist.setIdleMode(IdleMode.kBrake);

    wrist.setInverted(true);
    wrist.getPIDController().setP(kP);
    wrist.getPIDController().setI(kI);
    wrist.getPIDController().setD(kD);
    wrist.getPIDController().setIZone(kIz);
    wrist.getPIDController().setFF(kFF);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Flex(this));
  }

  public void drive(double speed, boolean hold) {
    double output = speed;

    double deltaAngle = getAngle();
    SmartDashboard.putNumber("getAngle()", getAngle());
    double gyroSpeed = GyroSensitivity.kArm.val * deltaAngle;

    if (Math.abs(output) < DeadbandType.kWrist.speed) {
      if (useGyro) {
        wrist.set(-gyroSpeed);
      } else {
        wrist.getPIDController().setReference(0, ControlType.kVelocity);
      }
    } else {
      resetGyroAngle();
      SmartDashboard.putNumber("WristSpeed", output);
      wrist.set(-output);
    }

  }

  private double getAngle() {
    return gyro.getAngle();

  }

  public void resetGyro() {
    gyro.reset();
  }

  public class ResetGyro extends InstantCommand {

    ResetGyro() {
      super();
    }

    @Override
    public void initialize() {
      resetGyro();
    }
  }

  private void resetGyroAngle() {
    gyro.reset();
  }

  private void toggleGyro() {
    useGyro = !useGyro;
  }
}
