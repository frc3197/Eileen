package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class ColorSensor {
    public ColorSensor() {
        I2C sensor = new I2C(Port.kOnboard, 0x39);
        System.out.println(sensor.addressOnly());
    }
}