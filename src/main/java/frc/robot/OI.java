package frc.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.RobotMap.ElevatorPreset;
import frc.robot.RobotMap.ArmPreset;
import frc.robot.commands.AlignTurn;
import frc.robot.commands.ElevateToPreset;
import frc.robot.commands.defaults.Flex;
import frc.robot.commands.defaults.Roll;
import frc.robot.commands.defaults.Speak;

/**
 * Initializes the joystick and specific buttons
 */
public class OI {
        private static XboxController driver;
        private static XboxController secondary;

        private static POVButton driverDPadUp;
        private static POVButton driverDPadRight;
        private static POVButton driverDPadDown;
        private static POVButton driverDPadLeft;

        private static POVButton secondaryDPadUp;
        private static POVButton secondaryDPadRight;
        private static POVButton secondaryDPadDown;
        private static POVButton secondaryDPadLeft;

        private static JoystickButton driverA;
        private static JoystickButton driverB;
        private static JoystickButton driverX;
        private static JoystickButton driverY;

        private static JoystickButton secondaryA;
        private static JoystickButton secondaryB;
        private static JoystickButton secondaryX;
        private static JoystickButton secondaryY;

        private static JoystickButton driverRightBumper;
        private static JoystickButton driverLeftBumper;
        private static JoystickButton secondaryRightBumper;
        private static JoystickButton secondaryLeftBumper;

        public static AnalogGyro gyro;

        static {
                driver = new XboxController(0);
                secondary = new XboxController(1);

                driverDPadUp = new POVButton(driver, 0);
                driverDPadRight = new POVButton(driver, 90);
                driverDPadDown = new POVButton(driver, 180);
                driverDPadLeft = new POVButton(driver, 270);

                secondaryDPadUp = new POVButton(secondary, 0);
                secondaryDPadRight = new POVButton(secondary, 90);
                secondaryDPadDown = new POVButton(secondary, 180);
                secondaryDPadLeft = new POVButton(secondary, 270);

                driverA = new JoystickButton(driver, 1);
                driverB = new JoystickButton(driver, 2);
                driverX = new JoystickButton(driver, 3);
                driverY = new JoystickButton(driver, 4);

                secondaryA = new JoystickButton(secondary, 1);
                secondaryB = new JoystickButton(secondary, 2);
                secondaryX = new JoystickButton(secondary, 3);
                secondaryY = new JoystickButton(secondary, 4);

                driverRightBumper = new JoystickButton(driver, 6);
                driverLeftBumper = new JoystickButton(driver, 5);

                secondaryRightBumper = new JoystickButton(secondary, 6);
                secondaryLeftBumper = new JoystickButton(secondary, 5);

                gyro = new AnalogGyro(RobotMap.gyroChannel);

                driverA.whenPressed(Robot.driveTrain.changeDriveMode);

                // driverB.whileHeld(new AlignTurn(Robot.driveTrain));
                driverB.whileHeld(new Speak(Robot.hatch, 1));
                driverX.whileHeld(new Speak(Robot.hatch, -1));

                // driverX.whenPressed(Robot.elevator.reset);

                driverY.whenPressed(Robot.driveTrain.changeDriveGryo);

                driverRightBumper.whileHeld(new Roll(Robot.manipulator, 1));
                driverLeftBumper.whileHeld(new Roll(Robot.manipulator, -1));

                secondaryA.whenPressed(Robot.arm.reset);

                /**
                 * If the right bumper is pushed, then the cargo intake will move. If the right
                 * bumper is not held, then the hatch mech will be in position.
                 */

                // driverDPadUp.whenPressed(new Flex(ElevatorPreset.HATCH_LEVEL_THREE,
                // ElevatorPreset.CARGO_LEVEL_THREE,
                // ArmPreset.HATCH_PRESET, ArmPreset.CARGO_ROCKET_PRESET, driverRightBumper,
                // Robot.elevator, Robot.arm));
                // driverDPadRight.whenPressed(new Flex(ElevatorPreset.HATCH_LEVEL_TWO,
                // ElevatorPreset.CARGO_LEVEL_TWO,
                // ArmPreset.HATCH_PRESET, ArmPreset.CARGO_ROCKET_PRESET, driverRightBumper,
                // Robot.elevator, Robot.arm));
                // driverDPadDown.whenPressed(new Flex(ElevatorPreset.HATCH_LEVEL_ONE,
                // ElevatorPreset.CARGO_LEVEL_ONE,
                // ArmPreset.HATCH_PRESET, ArmPreset.CARGO_ROCKET_PRESET, driverRightBumper,
                // Robot.elevator, Robot.arm));
                // driverDPadLeft.whenPressed(new Flex(ElevatorPreset.CARGO_LOADING_LEVEL,
                // ElevatorPreset.CARGO_SHIP_CARGO,
                // ArmPreset.CARGO_ROCKET_PRESET, ArmPreset.CARGO_SHIP_DUMP_PRESET,
                // driverRightBumper,
                // Robot.elevator, Robot.arm));
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
                return driver.getTriggerAxis(Hand.kRight) - driver.getTriggerAxis(Hand.kLeft);
        }

        public static double manipulatorSpeed() {
                return driver.getY(Hand.kRight);
        }

        public static double elbowSpeed() {
                return secondary.getY(Hand.kRight);
        }

        public static double wristSpeed() {
                return secondary.getY(Hand.kLeft);
        }

        public static double erectorSpeed() {
                return secondary.getTriggerAxis(Hand.kRight) - secondary.getTriggerAxis(Hand.kLeft);
        }
}
