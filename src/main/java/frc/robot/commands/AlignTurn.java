package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.RobotMap.RobotDeadband;
import frc.robot.subsystems.DriveTrain;

public class AlignTurn extends Command {

    private DriveTrain driveTrain;

    private double verticalSpeed;
    private double turnSpeed;
    private NetworkTableInstance ntinst;
    private NetworkTable vision;
    private NetworkTableEntry contourXsEntry;
    private NetworkTableEntry contourAreasEntry;

    public AlignTurn(DriveTrain driveTrain) {
        ntinst = NetworkTableInstance.getDefault();
        vision = ntinst.getTable("Vision");
        contourXsEntry = vision.getEntry("contour_xs");
        contourAreasEntry = vision.getEntry("contour_areas");
        requires(driveTrain);
        this.driveTrain = driveTrain;
    }

    @Override
    protected void execute() {
        getContourParameters();
        // betterAlign();
        driveTrain.arcadeDrive(verticalSpeed, turnSpeed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    private void getContourParameters() {
        // double[] contourXs = SmartDashboard.getNumberArray("contour_xs", new double[]
        // {});
        // double[] contourAreas = SmartDashboard.getNumberArray("contour_areas", new
        // double[] {});
        Number[] defaultValues = new Number[] {};
        Number[] contourXs = contourXsEntry.getNumberArray(defaultValues);
        Number[] contourAreas = contourAreasEntry.getNumberArray(defaultValues);

        if (contourXs.length == 2) {
            double x0 = contourXs[0].doubleValue();
            double x1 = contourXs[1].doubleValue();
            double midpoint = (x0 + x1) / (2 * RobotMap.xMax) - 0.5;
            turnSpeed = -3 * Math.copySign(Math.pow(Math.abs(midpoint), 1), midpoint);
            // If the midX is greater than the target, turn left (-)
            System.out.println(contourXs[0] + " " + contourXs[1] + " " + midpoint + " " + turnSpeed);
        } else {
            turnSpeed = 0;
        }
        if (contourAreas.length == 2 && Math.abs(turnSpeed) < RobotDeadband.DRIVE_DEADBAND.speed) {
            double area0 = contourAreas[0].doubleValue();
            double area1 = contourAreas[1].doubleValue();
            double areaError = ((area0 + area1) / RobotMap.visionTargetArea) - 1;
            // verticalSpeed = -.001 * Math.copySign(Math.pow(areaError, 2), areaError);
            verticalSpeed = .6 * areaError;
            System.out.println((area0 + area1) + " " + areaError + " " + verticalSpeed);
            // If totalArea is greater than the target, go backward (-)
        } else {
            verticalSpeed = 0;
        }
        // verticalSpeed = 0;
    }

    // private void betterAlign() {
    // Number[] defaultValues = new Number[] {};
    // Number[] contourXs = contourXsEntry.getNumberArray(defaultValues);
    // Number[] contourAreas = contourAreasEntry.getNumberArray(defaultValues);
    // double left, right;
    // if (contourXs.length == 2) {
    // double x0 = contourXs[0].doubleValue();
    // double x1 = contourXs[1].doubleValue();
    // double midpoint = (x0 + x1) / (2 * RobotMap.xMax) - 0.5;

    // double area0 = contourAreas[0].doubleValue();
    // double area1 = contourAreas[1].doubleValue();
    // if (midpoint > 0) {
    // left = Math.max(area0, area1);
    // right = Math.min(area0, area1);
    // } else {
    // right = Math.max(area0, area1);
    // left = Math.min(area0, area1);
    // }

    // left = -(RobotMap.visionTargetArea - left) / left;
    // right = -(RobotMap.visionTargetArea - right) / right;
    // driveTrain.tankDrive(left, right);
    // }
    // }

}