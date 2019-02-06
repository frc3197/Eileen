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

/**
 * Initializes the joystick and specific buttons
 */
public class OI {
  public XboxController joystick = new XboxController(0);

  public POVButton dPadUp = new POVButton(joystick, 0);
  public POVButton dPadRight = new POVButton(joystick, 90);
  public POVButton dPadDown = new POVButton(joystick, 180);
  public POVButton dPadLeft = new POVButton(joystick, 270);

  public JoystickButton rightBumper = new JoystickButton(joystick, 6);

  public AnalogGyro gyro = new AnalogGyro(0);

  public JoystickButton a = new JoystickButton(joystick, 1);

  public JoystickButton b = new JoystickButton(joystick, 2);

  public JoystickButton x = new JoystickButton(joystick, 3);

  public JoystickButton y = new JoystickButton(joystick, 4);

  public OI() {
    // TODO: real button numbers

    a.whenPressed(new ChangeDriveMode());

    b.whileHeld(new AlignTurn(Robot.driveTrain));

    x.whenPressed(new ElevatorResetPosition(Robot.elevator));

    // y.whenPressed(new ElevateToPreset(ElevatorPreset.HATCH_LEVEL_ONE));
    /**
     * If the right bumper is pushed, then the cargo intake will move. If the right
     * bumper is not held, then the hatch mech will be in position.
     */
    dPadUp.whenPressed(
        new ElevateToPreset(ElevatorPreset.HATCH_LEVEL_THREE, ElevatorPreset.CARGO_LEVEL_THREE, rightBumper));
    dPadRight
        .whenPressed(new ElevateToPreset(ElevatorPreset.HATCH_LEVEL_TWO, ElevatorPreset.CARGO_LEVEL_TWO, rightBumper));
    dPadDown
        .whenPressed(new ElevateToPreset(ElevatorPreset.HATCH_LEVEL_ONE, ElevatorPreset.CARGO_LEVEL_ONE, rightBumper));
    dPadLeft.whenPressed(
        new ElevateToPreset(ElevatorPreset.CARGO_LOADING_LEVEL, ElevatorPreset.CARGO_SHIP_CARGO, rightBumper));
  }
}
