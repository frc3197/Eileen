package frc.robot.commands.test;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Arm;

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