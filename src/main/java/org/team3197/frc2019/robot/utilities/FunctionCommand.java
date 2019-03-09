
package org.team3197.frc2019.robot.utilities;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class FunctionCommand extends InstantCommand {

    private FunctionWrapper function;

    public FunctionCommand(FunctionWrapper function) {
        this.function = function;
    }

    @Override
    protected void initialize() {
        function.call();
    }
}
