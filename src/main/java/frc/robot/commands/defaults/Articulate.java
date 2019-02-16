package frc.robot.commands.defaults;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.subsystems.Arm;

public class Articulate extends Command {

  private Arm arm;

  public Articulate(Arm arm) {
    requires(arm);
    this.arm = arm;
  }

  /**
   * Takes the speed from OI that the wrist and elbow should drive at, and moves
   * them.
   */
  @Override
  protected void execute() {
    double elbowSpeed = OI.elbowSpeed();
    double wristSpeed = OI.wristSpeed();
    arm.wrist(wristSpeed);
    arm.elbow(elbowSpeed);
    // arm.wrist(arm.gravBreak(wrist.getEncoder().getPosition(), wristSpeed));
    // arm.elbow(arm.gravBreak(elbow.getEncoder().getPosition(), elbowSpeed));
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
