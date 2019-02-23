package org.team3197.frc2019.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;

import org.team3197.frc2019.robot.Robot;
import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.subsystems.DriveTrain;

public class AlignTurn extends Command {

    private DriveTrain driveTrain;

    private double verticalSpeed;
    private double turnSpeed;
    private NetworkTable vision;
    private NetworkTableEntry contourXsEntry;
    private NetworkTableEntry contourAreasEntry;

    public AlignTurn(DriveTrain driveTrain) {
        super();
        vision = Robot.ntInst.getTable("Vision");
        contourXsEntry = vision.getEntry("contour_xs");
        contourAreasEntry = vision.getEntry("contour_areas");
        requires(driveTrain);
        this.driveTrain = driveTrain;
    }

    @Override
    protected void execute() {
        getContourParameters();
        vision.getEntry("contour_xs").addListener((a) -> {a.}, flags);
        driveTrain.arcadeDrive(verticalSpeed, turnSpeed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    private void getContourParameters() {
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
        if (contourAreas.length == 2 && Math.abs(turnSpeed) < DeadbandType.kDrive.speed) {
            double area0 = contourAreas[0].doubleValue();
            double area1 = contourAreas[1].doubleValue();
            double areaError = ((area0 + area1) / RobotMap.visionTargetArea) - 1;
            verticalSpeed = .6 * areaError;
            System.out.println((area0 + area1) + " " + areaError + " " + verticalSpeed);
            // If totalArea is greater than the target, go backward (-)
        } else {
            verticalSpeed = 0;
        }
    }

}