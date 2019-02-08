package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;

public class AlignTurn extends Command {

    private DriveTrain driveTrain;

    private double verticalSpeed;
    private double turnSpeed;

    public AlignTurn(DriveTrain driveTrain) {
        requires(driveTrain);
        this.driveTrain = driveTrain;
    }

    @Override
    protected void execute() {
        getContourParameters();
        driveTrain.arcadeDrive(verticalSpeed, turnSpeed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    private void getContourParameters() {
        double[] contourXs = SmartDashboard.getNumberArray("contour_xs", new double[] {});
        double[] contourAreas = SmartDashboard.getNumberArray("contour_areas", new double[] {});

        if (contourAreas.length == 2) {
            double areaError = ((contourAreas[0] + contourAreas[1]) - RobotMap.visionTargetArea);
            verticalSpeed = -Math.copySign(Math.pow(areaError, 2), areaError);
            // If totalArea is greater than the target, go backward (-)
        } else {
            verticalSpeed = 0;
        }

        if (contourXs.length == 2) {
            double xError = (((contourXs[0] + contourXs[1]) / 2) - RobotMap.visionTargetX);
            turnSpeed = -Math.copySign(Math.pow(xError, 2), xError);
            // If the midX is greater than the target, turn left (-)
        } else {
            turnSpeed = 0;
        }
    }
}