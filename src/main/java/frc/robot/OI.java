package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.XboxController;

public class OI {
  public static XboxController joystick = new XboxController(0);
  public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
}
