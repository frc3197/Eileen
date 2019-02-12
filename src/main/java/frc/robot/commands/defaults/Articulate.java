package frc.robot.commands.defaults;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.subsystems.Arm;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Articulate extends Command {

  private CANSparkMax elbow = new CANSparkMax(RobotMap.CANSparkMaxID.kElbow.id, MotorType.kBrushless);
  private CANSparkMax wrist = new CANSparkMax(RobotMap.CANSparkMaxID.kWrist.id, MotorType.kBrushless);

  private Arm arm = new Arm();

  public Articulate(Arm arm) {
    requires(arm);
    this.arm = arm;
  }

  @Override
  protected void execute() {
    double elbowSpeed = OI.elbowSpeed();
    double wristSpeed = OI.wristSpeed();
    arm.wrist(arm.gravBreak(wrist.getEncoder().getPosition(), wristSpeed));
    arm.elbow(arm.gravBreak(elbow.getEncoder().getPosition(), elbowSpeed));
    /**
     * instead of 0 at the end of these statements will be replaced with an
     * encoder-specific deadzone
     */
    // double armSpeed = -0.15;
    // final double GEARCONSTANT = 1.07142857;
    // if (arm.getWristEncoderPosition() - arm.getElbowEncoderPosition() < 0) {
    // /* If Wrist Encoder is less than Elbow encoder, move */
    // arm.wrist(-armSpeed * GEARCONSTANT);
    // } else if (arm.getWristEncoderPosition() - arm.getElbowEncoderPosition() > 0)
    // {
    // /* If Wrist Encoder is more than Elbow encoder, move */
    // arm.wrist(armSpeed * GEARCONSTANT);
    // }
    // double speed;
    // arm.drive(speed);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    arm.elbow(0);
    arm.wrist(0);
  }
}
