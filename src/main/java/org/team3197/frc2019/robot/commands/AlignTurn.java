package org.team3197.frc2019.robot.commands;

import org.team3197.frc2019.robot.subsystems.DriveTrain;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AlignTurn extends Command {

    private DriveTrain driveTrain;

    private double verticalSpeed;
    private double turnSpeed;
    private NetworkTableInstance ntinst;
    private NetworkTable vision;
    private NetworkTableEntry contourXsEntry;
    private NetworkTableEntry contourAreasEntry;
    private int turnDirection = -1;

    public AlignTurn(DriveTrain driveTrain) {
        super();
        ntinst = NetworkTableInstance.getDefault();
        vision = ntinst.getTable("Vision");
        contourXsEntry = vision.getEntry("contour_xs");
        contourAreasEntry = vision.getEntry("contour_areas");
        requires(driveTrain);
        this.driveTrain = driveTrain;
    }

    @Override
    protected void execute() {
        System.out.println("Inside execute");
        getContourParameters();
        System.out.println("After Get Contour");
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    private void getContourParameters() {
        System.out.println("Inside the function!");
        Number[] defaultValues = new Number[] {};
        Number[] contourXs = contourXsEntry.getNumberArray(defaultValues);
        Number[] contourAreas = contourAreasEntry.getNumberArray(defaultValues);
        System.out.println(contourXs[0]);

        // boolean direction = Math
        // .abs(contourXs[0].doubleValue() + contourXs[1].doubleValue()) >
        // RobotMap.cameraPixelWidth;

        if (contourXs.length == contourAreas.length) {
            SmartDashboard.putNumber("length", contourXs.length);
            switch (contourXs.length) {
            case (0):
                System.out.println("This feature has not been simulated.");
                // TODO Add rumble
                break;
            case (1):
                System.out.println("This feature is simulated, but probably broken.");
                driveTrain.tankDrive(.5 * turnDirection, -.5 * turnDirection);
                break;
            case (2):
                System.out.println("You have done exactly what our instructions neglected to tell you.");
                double totalArea = contourAreas[0].doubleValue() + contourAreas[1].doubleValue();
                driveTrain.tankDrive(contourAreas[1].doubleValue() / totalArea,
                        contourAreas[0].doubleValue() / totalArea);
                turnDirection = (int) (Math.abs(contourAreas[0].doubleValue() - contourAreas[1].doubleValue())
                        / contourAreas[0].doubleValue() - contourAreas[1].doubleValue());
                break;
            }
        } else {
            driveTrain.tankDrive(.5 * turnDirection, -.5 * turnDirection);
        }
    }
}