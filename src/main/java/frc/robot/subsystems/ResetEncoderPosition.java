package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.InstantCommand;

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