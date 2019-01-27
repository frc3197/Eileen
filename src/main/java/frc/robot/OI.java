package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AlignTurn;
import frc.robot.commands.ChangeDriveMode;

public class OI {
  public static XboxController joystick = new XboxController(0);

  public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  public OI() {
    // TODO: real button numbers
    JoystickButton a = new JoystickButton(joystick, 1);
    a.whenPressed(new ChangeDriveMode());
    JoystickButton b = new JoystickButton(joystick, 2);
    b.whileHeld(new AlignTurn());
  }
} 
