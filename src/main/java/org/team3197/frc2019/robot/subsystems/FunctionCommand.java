
package org.team3197.frc2019.robot.subsystems;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class FunctionCommand extends InstantCommand {

    private FunctionWrapper reset;

    public FunctionCommand(FunctionWrapper reset) {
        this.reset = reset;
    }

    @Override
    protected void initialize() {
        reset.reset();
    }
}
