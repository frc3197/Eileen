
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ResetCommand extends InstantCommand {

    private Reset reset;

    public ResetCommand(Reset reset) {
        this.reset = reset;
    }

    @Override
    protected void initialize() {
        reset.reset();
    }
}
