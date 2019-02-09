package frc.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.RobotMap.ElevatorPreset;
import frc.robot.commands.AlignTurn;
import frc.robot.commands.ElevateToPreset;

/**
 * Initializes the joystick and specific buttons
 */
public class OI {
        private static XboxController driver;
        private static XboxController secondary;

        private static POVButton dPadUp;
        private static POVButton dPadRight;
        private static POVButton dPadDown;
        private static POVButton dPadLeft;

        private static JoystickButton a;
        private static JoystickButton b;
        private static JoystickButton x;
        private static JoystickButton y;
        private static JoystickButton rightBumper;

        public static AnalogGyro gyro;

        static {
                driver = new XboxController(0);
                secondary = new XboxController(1);

                dPadUp = new POVButton(driver, 0);
                dPadRight = new POVButton(driver, 90);
                dPadDown = new POVButton(driver, 180);
                dPadLeft = new POVButton(driver, 270);

                a = new JoystickButton(driver, 1);
                b = new JoystickButton(driver, 2);
                x = new JoystickButton(driver, 3);
                y = new JoystickButton(driver, 4);

                rightBumper = new JoystickButton(driver, 6);

                gyro = new AnalogGyro(RobotMap.gyroChannel);

                // TODO: real button numbers

                a.whenPressed(Robot.driveTrain.changeDriveMode);

                b.whileHeld(new AlignTurn(Robot.driveTrain));

                x.whenPressed(Robot.elevator.reset);

                // y.whenPressed(new ElevateToPreset(ElevatorPreset.HATCH_LEVEL_ONE));
                /**
                 * If the right bumper is pushed, then the cargo intake will move. If the right
                 * bumper is not held, then the hatch mech will be in position.
                 */
                dPadUp.whenPressed(new ElevateToPreset(ElevatorPreset.HATCH_LEVEL_THREE,
                                ElevatorPreset.CARGO_LEVEL_THREE, rightBumper, Robot.elevator));
                dPadRight.whenPressed(new ElevateToPreset(ElevatorPreset.HATCH_LEVEL_TWO,
                                ElevatorPreset.CARGO_LEVEL_TWO, rightBumper, Robot.elevator));
                dPadDown.whenPressed(new ElevateToPreset(ElevatorPreset.HATCH_LEVEL_ONE, ElevatorPreset.CARGO_LEVEL_ONE,
                                rightBumper, Robot.elevator));
                dPadLeft.whenPressed(new ElevateToPreset(ElevatorPreset.CARGO_LOADING_LEVEL,
                                ElevatorPreset.CARGO_SHIP_CARGO, rightBumper, Robot.elevator));
        }

        // TODO add back after linking elbox and wrist
        // public static double articulateSpeed() {
        // return secondary.getY(Hand.kRight);
        // }

        public static double elbowSpeed() {
                return secondary.getY(Hand.kRight);
        }

        public static double wristSpeed() {
                return secondary.getY(Hand.kLeft);
        }

        public static double arcadeDriveY() {
                return driver.getY(Hand.kRight);
        }

        public static double arcadeDriveX() {
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
}
