package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.RobotMap.ArmPreset;
import frc.robot.RobotMap.ElevatorPreset;
import frc.robot.RobotMap.MaxSpeed;
import frc.robot.RobotMap.MaxSpeeds;
import frc.robot.commands.Flex;

/**
 * Initializes the joystick and specific buttons
 */
public class OI {
        private static XboxController driver = new XboxController(0);
        private static XboxController secondary = new XboxController(1);

        private static POVButton driverDPadUp = new POVButton(driver, 0);
        private static POVButton driverDPadRight = new POVButton(driver, 90);
        private static POVButton driverDPadDown = new POVButton(driver, 180);
        private static POVButton driverDPadLeft = new POVButton(driver, 270);

        private static POVButton secondaryDPadUp = new POVButton(secondary, 0);
        private static POVButton secondaryDPadRight = new POVButton(secondary, 90);
        private static POVButton secondaryDPadDown = new POVButton(secondary, 180);
        private static POVButton secondaryDPadLeft = new POVButton(secondary, 270);

        private static JoystickButton driverA = new JoystickButton(driver, 1);
        private static JoystickButton driverB = new JoystickButton(driver, 2);
        private static JoystickButton driverX = new JoystickButton(driver, 3);
        private static JoystickButton driverY = new JoystickButton(driver, 4);

        private static JoystickButton secondaryA = new JoystickButton(secondary, 1);
        private static JoystickButton secondaryB = new JoystickButton(secondary, 2);
        private static JoystickButton secondaryX = new JoystickButton(secondary, 3);
        private static JoystickButton secondaryY = new JoystickButton(secondary, 4);

        private static JoystickButton driverRightBumper = new JoystickButton(driver, 6);
        private static JoystickButton driverLeftBumper = new JoystickButton(driver, 5);
        private static JoystickButton secondaryRightBumper = new JoystickButton(secondary, 6);
        private static JoystickButton secondaryLeftBumper = new JoystickButton(secondary, 5);

        static {
                // driverDPadUp.whenPressed(Robot.driveTrain.changeDriveMode);

                // driverDPadRight.whileHeld(new AlignTurn(Robot.driveTrain));

                // driverDPadLeft.whenPressed(Robot.driveTrain.changeDriveGryo);

                // /**
                // * If the right bumper is pushed, then the cargo intake will move. If the
                // right
                // * bumper is not held, then the hatch mech will be in position.
                // */

                driverA.whenPressed(Robot.driveTrain.changeDriveMode);

                // driverB.whileHeld(new AlignTurn(Robot.driveTrain));
                // secondaryX.whenPressed(Robot.elevator.reset);

                // driverY.whenPressed(Robot.driveTrain.changeDriveGryo);

                // secondaryA.whenPressed(Robot.arm.reset);
                secondaryB.whenPressed(Robot.arm.resetGyro);

                /**
                 * If the right bumper is pushed, then the cargo intake will move. If the right
                 * bumper is not held, then the hatch mech will be in position.
                 */
                secondaryDPadUp.whileHeld(new Flex(ElevatorPreset.kHatchLevelThree, ElevatorPreset.kCargoLevelThree,
                                ArmPreset.kHatchThree, ArmPreset.kCargoRocketThree, secondaryA, Robot.elevator,
                                Robot.arm));

                secondaryDPadRight.whileHeld(new Flex(ElevatorPreset.kHatchLevelTwo, ElevatorPreset.kCargoLevelTwo,
                                ArmPreset.kHatchTwo, ArmPreset.kCargoRocketTwo, secondaryA, Robot.elevator, Robot.arm));

                secondaryDPadDown.whileHeld(new Flex(ElevatorPreset.kHatchLevelOne, ElevatorPreset.kCargoLevelOne,
                                ArmPreset.kHatchOne, ArmPreset.kCargoRocketOne, secondaryA, Robot.elevator, Robot.arm));

                secondaryDPadLeft.whileHeld(new Flex(ElevatorPreset.kCargoLoadingLevel, ElevatorPreset.kCargoShipCargo,
                                ArmPreset.kCargoShipDump, ArmPreset.kCargoShipDump, secondaryA, Robot.elevator,
                                Robot.arm));

                /**
                 * Alternate for the secondary dPad, could use buttons ABXY.
                 */
                // driverA.whenPressed(new Flex(ElevatorPreset.kHatchLevelThree,
                // ElevatorPreset.kCargoLevelThree,
                // ArmPreset.kHatchThree, ArmPreset.kCargoRocketThree, driverRightBumper,
                // Robot.elevator,
                // Robot.arm));

                // driverB.whenPressed(
                // new Flex(ElevatorPreset.kHatchLevelTwo, ElevatorPreset.kCargoLevelTwo,
                // ArmPreset.kHatchTwo,
                // ArmPreset.kCargoRocketTwo, driverRightBumper, Robot.elevator, Robot.arm));

                // driverX.whenPressed(
                // new Flex(ElevatorPreset.kHatchLevelOne, ElevatorPreset.kCargoLevelOne,
                // ArmPreset.kHatchOne,
                // ArmPreset.kCargoRocketOne, driverRightBumper, Robot.elevator, Robot.arm));

                // driverY.whenPressed(new Flex(ElevatorPreset.kCargoLoadingLevel,
                // ElevatorPreset.kCargoShipCargo,
                // ArmPreset.kCargoRocket, ArmPreset.kCargoShipDump, driverRightBumper,
                // Robot.elevator,
                // Robot.arm));
        }

        // TODO add back after linking elbox and wrist
        // public static double articulateSpeed() {
        // return secondary.getY(Hand.kRight);
        // }

        public static double arcadeDriveY() {
                return driver.getY(Hand.kRight);
        }

        public static double arcadeDriveR() {
                return -driver.getX(Hand.kLeft);
        }

        public static double tankDriveLeft() {
                return driver.getY(Hand.kLeft);
        }

        public static double tankDriveRight() {
                return driver.getY(Hand.kRight);
        }

        public static double elevatorSpeed() {
                return (secondary.getTriggerAxis(Hand.kRight) - secondary.getTriggerAxis(Hand.kLeft))
                                * RobotMap.elevatorSpeedMultiplier;
        }

        public static double elbowSpeed() {
                return -secondary.getY(Hand.kRight) * RobotMap.elbowSpeedMultiplier;
        }

        public static double wristSpeed() {
                return secondary.getY(Hand.kLeft) * RobotMap.wristSpeedMultiplier;

        }

        public static double erectorSpeed() {
                return driver.getTriggerAxis(Hand.kRight) - driver.getTriggerAxis(Hand.kLeft);
        }

        public static double manipulatorSpeed() {
                return (secondaryRightBumper.get() ? MaxSpeeds.kCargo.forwardSpeed : 0)
                                + (secondaryLeftBumper.get() ? MaxSpeeds.kCargo.reverseSpeed : 0);
        }

        public static double hatchSpeed() {
                return (driverLeftBumper.get() ? MaxSpeeds.kHatch.forwardSpeed : 0)
                                + (driverRightBumper.get() ? MaxSpeeds.kHatch.reverseSpeed : 0);
                // return (driverLeftBumper.get() ? MaxSpeeds.kHatch.forwardSpeed :
                // MaxSpeeds.kHatch.reverseSpeed);
        }
}
