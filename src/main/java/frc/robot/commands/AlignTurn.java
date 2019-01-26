package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class AlignTurn extends Command {

    private double verticalSpeed;
    private double turnSpeed;

    public AlignTurn() {
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        getContourParameters();
        Robot.driveTrain.arcadeDrive(verticalSpeed, turnSpeed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.driveTrain.arcadeDrive(0, 0);
    }

    private void getContourParameters() {
        double[] contourXs = SmartDashboard.getNumberArray("contour_xs", new double[] {});
        double[] contourAreas = SmartDashboard.getNumberArray("contour_areas", new double[] {});

        if (contourAreas.length == 2) {
            double areaError = ((contourAreas[0] + contourAreas[1]) - RobotMap.visionTargetArea);
            verticalSpeed = -Math.copySign(Math.pow(areaError, 2), areaError);
            // If totalArea is greater than the target, go backward (-)
        } else {
            System.out.println("contourAreas 0");
            verticalSpeed = 0;
        }

        if (contourXs.length == 2) {
            double xError = (((contourXs[0] + contourXs[1]) / 2) - RobotMap.visionTargetX);
            turnSpeed = -Math.copySign(Math.pow(xError, 2), xError);
            // If the midX is greater than the target, turn left (-)
        } else {
            System.out.println("contourXs 0");
            turnSpeed = 0;
        }
    }
}