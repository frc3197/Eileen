package frc.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
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
        public static XboxController driver = new XboxController(0);
        public static XboxController secondary = new XboxController(1);

        public static POVButton dPadUp = new POVButton(driver, 0);
        public static POVButton dPadRight = new POVButton(driver, 90);
        public static POVButton dPadDown = new POVButton(driver, 180);
        public static POVButton dPadLeft = new POVButton(driver, 270);

        public static AnalogGyro gyro = new AnalogGyro(0);

        public static JoystickButton a = new JoystickButton(driver, 1);
        public static JoystickButton b = new JoystickButton(driver, 2);
        public static JoystickButton x = new JoystickButton(driver, 3);
        public static JoystickButton y = new JoystickButton(driver, 4);
        public static JoystickButton rightBumper = new JoystickButton(driver, 6);

        static {
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
}
