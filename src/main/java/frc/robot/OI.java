package frc.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.RobotMap.ElevatorPreset;
import frc.robot.commands.AlignTurn;
import frc.robot.commands.ChangeDriveMode;
import frc.robot.commands.ElevateToPreset;
import frc.robot.commands.ElevatorResetPosition;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;

/**
 * Initializes the joystick and specific buttons
 */
public class OI {
        public static XboxController joystick = new XboxController(0);

        public static POVButton dPadUp = new POVButton(joystick, 0);
        public static POVButton dPadRight = new POVButton(joystick, 90);
        public static POVButton dPadDown = new POVButton(joystick, 180);
        public static POVButton dPadLeft = new POVButton(joystick, 270);

        public static JoystickButton rightBumper = new JoystickButton(joystick, 6);

        public static AnalogGyro gyro = new AnalogGyro(RobotMap.gyroChannel);

        public static JoystickButton a = new JoystickButton(joystick, 1);

        public static JoystickButton b = new JoystickButton(joystick, 2);

        public static JoystickButton x = new JoystickButton(joystick, 3);

        public static JoystickButton y = new JoystickButton(joystick, 4);

        private static OI oi = new OI(Robot.driveTrain, Robot.elevator);

        private OI(DriveTrain driveTrain, Elevator elevator) {
                // TODO: real button numbers

                a.whenPressed(new ChangeDriveMode(driveTrain));

                b.whileHeld(new AlignTurn(driveTrain));

                x.whenPressed(new ElevatorResetPosition(elevator));

                /**
                 * If the right bumper is pushed, then the cargo intake will move. If the right
                 * bumper is not held, then the hatch mech will be in position.
                 */
                dPadUp.whenPressed(new ElevateToPreset(ElevatorPreset.HATCH_LEVEL_THREE,
                                ElevatorPreset.CARGO_LEVEL_THREE, rightBumper, elevator));
                dPadRight.whenPressed(new ElevateToPreset(ElevatorPreset.HATCH_LEVEL_TWO,
                                ElevatorPreset.CARGO_LEVEL_TWO, rightBumper, elevator));
                dPadDown.whenPressed(new ElevateToPreset(ElevatorPreset.HATCH_LEVEL_ONE, ElevatorPreset.CARGO_LEVEL_ONE,
                                rightBumper, elevator));
                dPadLeft.whenPressed(new ElevateToPreset(ElevatorPreset.CARGO_LOADING_LEVEL,
                                ElevatorPreset.CARGO_SHIP_CARGO, rightBumper, elevator));
        }
}
