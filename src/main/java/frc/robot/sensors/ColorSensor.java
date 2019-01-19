package frc.robot.sensors;

import edu.wpi.first.wpilibj.I2C;

public class ColorSensor{
    public ColorSensor(I2C.Port port){
        I2C sensor = new I2C(port, 0x39);
        System.out.println(sensor.addressOnly());
    }
}