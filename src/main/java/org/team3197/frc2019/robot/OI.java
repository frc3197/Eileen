package org.team3197.frc2019.robot;

import org.team3197.frc2019.robot.RobotMap.MaxSpeeds;
import org.team3197.frc2019.robot.commands.AlignTurn;
import org.team3197.frc2019.robot.commands.presets.Cargo;
import org.team3197.frc2019.robot.commands.presets.LevelOne;
import org.team3197.frc2019.robot.commands.presets.LevelThree;
import org.team3197.frc2019.robot.commands.presets.LevelTwo;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

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

                driverA.whenPressed(Robot.driveTrain.changeDriveMode);

                driverB.whileHeld(new AlignTurn(Robot.driveTrain));
                // secondaryX.whenPressed(Robot.elevator.reset);
                // driverB.whileHeld(new AlignTurn(Robot.driveTrain));
                secondaryX.whenPressed(Robot.arm.toggleGyro);

                // driverY.whenPressed(Robot.driveTrain.changeDriveGryo);

                secondaryY.whenPressed(Robot.arm.resetEncoder);
                secondaryY.whenPressed(Robot.elevator.reset);
                secondaryB.whenPressed(Robot.arm.resetGyro);

                /**
                 * If the right bumper is pushed, then the cargo intake will move. If the right
                 * bumper is not held, then the hatch mech will be in position.
                 */
                secondaryDPadUp.whileHeld(new LevelThree(Robot.elevator, Robot.arm, secondaryA));
                secondaryDPadRight.whileHeld(new LevelTwo(Robot.elevator, Robot.arm, secondaryA));
                secondaryDPadDown.whileHeld(new LevelOne(Robot.elevator, Robot.arm, secondaryA));
                secondaryDPadLeft.whileHeld(new Cargo(Robot.elevator, Robot.arm, secondaryA));
        }

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
                return secondary.getY(Hand.kRight) * RobotMap.elbowSpeedMultiplier;
        }

        public static double wristSpeed() {
                return -secondary.getY(Hand.kLeft) * RobotMap.wristSpeedMultiplier;

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

        public static double climberVerticalSpeed() {
                return (driverDPadUp.get() ? MaxSpeeds.kClimberVertical.forwardSpeed : 0)
                                + (driverDPadDown.get() ? MaxSpeeds.kClimberVertical.reverseSpeed : 0);
        }

        public static double climberHorizontalSpeed() {
                return (driverDPadRight.get() ? MaxSpeeds.kClimberHorizontal.forwardSpeed : 0)
                                + (driverDPadLeft.get() ? MaxSpeeds.kClimberHorizontal.reverseSpeed : 0);
        }

        public static void rumble(double speed) {
                driver.setRumble(RumbleType.kRightRumble, speed);
                driver.setRumble(RumbleType.kLeftRumble, speed);
                secondary.setRumble(RumbleType.kRightRumble, speed);
                secondary.setRumble(RumbleType.kLeftRumble, speed);
        }
}
