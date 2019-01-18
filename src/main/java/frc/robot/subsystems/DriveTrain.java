package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.RobotMap.CANSparkMaxID;
import frc.robot.commands.Drive;

public class DriveTrain extends Subsystem {
  // SpeedControllerGroup left = new SpeedControllerGroup(
  // new CANSparkMax(CANSparkMaxID.FRONTLEFT.id, MotorType.kBrushless),
  // new CANSparkMax(CANSparkMaxID.BACKLEFT.id, MotorType.kBrushless));
  // SpeedControllerGroup right = new SpeedControllerGroup(
  // new CANSparkMax(CANSparkMaxID.FRONTRIGHT.id, MotorType.kBrushless),
  // new CANSparkMax(CANSparkMaxID.BACKRIGHT.id, MotorType.kBrushless));

  // DifferentialDrive drive = new DifferentialDrive(left, right);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive());
    // drive.setDeadband(RobotMap.deadband);
  }

  public void drive(double l, double r) {
    // drive.tankDrive(l, r, true);
  }

}
