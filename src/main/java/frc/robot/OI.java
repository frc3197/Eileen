package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AlignTurn;
import frc.robot.commands.ChangeDriveMode;

/**
 * Initializes the joystick and specific buttons
 */
public class OI {
  public XboxController joystick = new XboxController(0);

  public AnalogGyro gyro = new AnalogGyro(0);

  public JoystickButton a = new JoystickButton(joystick, 1);

  public JoystickButton b = new JoystickButton(joystick, 2);

  public OI() {
    // TODO: real button numbers

    a.whenPressed(new ChangeDriveMode());

    b.whileHeld(new AlignTurn());
  }
}
