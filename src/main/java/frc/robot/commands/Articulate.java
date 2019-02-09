package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.Arm;

public class Articulate extends Command {

  private Arm arm;

  public Articulate(Arm arm) {
    requires(arm);
    this.arm = arm;
  }

  @Override
  protected void execute() {
    double elbow = OI.elbowSpeed();
    double wrist = OI.wristSpeed();
    arm.wrist(wrist);
    arm.elbow(elbow);
    /**
     * instead of 0 at the end of these statements will be replaced with an
     * encoder-specific deadzone
     */
    double armSpeed = -0.15;
    final double GEARCONSTANT = 1.07142857;
    if (arm.getWristEncoderPosition() - arm.getElbowEncoderPosition() < 0) { // If Wrist Encoder is less than Elbow
                                                                             // Encoder, move.
      arm.wrist(-armSpeed * GEARCONSTANT);
    } else if (arm.getWristEncoderPosition() - arm.getElbowEncoderPosition() > 0) { // If Wrist Encoder is more than
                                                                                    // Elbow Encoder, move.
      arm.wrist(armSpeed * GEARCONSTANT);
    }
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
  }
}
