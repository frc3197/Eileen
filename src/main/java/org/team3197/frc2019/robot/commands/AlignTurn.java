package org.team3197.frc2019.robot.commands;

import java.util.function.ToDoubleBiFunction;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import org.team3197.frc2019.robot.RobotMap;
import org.team3197.frc2019.robot.RobotMap.DeadbandType;
import org.team3197.frc2019.robot.subsystems.DriveTrain;

public class AlignTurn extends Command {

    private DriveTrain driveTrain;

    private double verticalSpeed;
    private double turnSpeed;
    private NetworkTableInstance ntinst;
    private NetworkTable vision;
    private NetworkTableEntry contourXsEntry;
    private NetworkTableEntry contourAreasEntry;

    private int turnDirection = 1;

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
        getContourParameters();
        driveTrain.tankDrive(verticalSpeed, turnSpeed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    private void getContourParameters() {
        Number[] defaultValues = new Number[] {};
        Number[] contourXs = contourXsEntry.getNumberArray(defaultValues);
        Number[] contourAreas = contourAreasEntry.getNumberArray(defaultValues);
        boolean direction = Math
                .abs(contourXs[0].doubleValue() + contourXs[1].doubleValue()) > RobotMap.cameraPixelWidth;

        if (contourXs.length == contourAreas.length) {
            switch (contourXs.length) {
            case (0):
                // TODO Add rumble
                break;
            case (1):
                driveTrain.tankDrive(.5 * turnDirection, -.5 * turnDirection);
                break;
            case (2):
                double totalArea = contourAreas[0].doubleValue() + contourAreas[1].doubleValue();
                driveTrain.tankDrive(contourAreas[1].doubleValue() / totalArea,
                        contourAreas[0].doubleValue() / totalArea);
                turnDirection = (int) (Math.abs(contourAreas[0].doubleValue() - contourAreas[1].doubleValue())
                        / contourAreas[0].doubleValue() - contourAreas[1].doubleValue());
                break;

            }
        }
    }
}