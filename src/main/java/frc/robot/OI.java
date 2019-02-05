package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.AlignTurn;
import frc.robot.commands.ChangeDriveMode;
import frc.robot.commands.ElevateToPreset;

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

  public OI() {
    // TODO: real button numbers

    a.whenPressed(new ChangeDriveMode());

    b.whileHeld(new AlignTurn());

    /**
     * If the right bumper is pushed, then the cargo intake will move. If the right
     * bumper is not held, then the hatch mech will be in position.
     */
    if (rightBumper.get()) {
      if (dPadUp.get()) {
        // level 3 cargo
      } else if (dPadRight.get()) {
        // level 2 cargo
      } else if (dPadDown.get()) {
        // level 1 cargo (same everywhere)
      } else if (dPadLeft.get()) {
        // cargo ship cargo deposit
      }
    } else {
      if (dPadUp.get()) {
        // level 3 hatch
      } else if (dPadRight.get()) {
        // level 2 hatch
      } else if (dPadDown.get()) {
        // level 1 hatch (same everywhere)
      } else if (dPadLeft.get()) {
        // cargo loading station intake level
      }
    }
  }
}
