===
I2C
===
I2C has been mostly solved as of the 2019 season. On the RoboRIO, the SDA and SCL ports are flipped. To solve this, the SDA and SCL wires
on the sensor were physically flipped. In the future, when using I2C sensors, check the sensors documentation and how the wires
are arranged, and then physically rearrange to match the RoboRIO ports.

-------------------
REV Color Sensor V2
-------------------
The REV Color Sensor V2 is a line sensor that uses I2C to communicate. WPILib contains libraries on communicating with I2C devices, 
and sending commands to the device require writing bits to specific addresses.

The REV Color Sensor has the I2C address ``0x39``.

~~~~~~~~~~~~~~~~~~~
Important Addresses
~~~~~~~~~~~~~~~~~~~
``0x00`` is the enable register.

``0x12`` is the device ID register. Returns a hexadecimal value.

- 0x60 = TMD37821
- 0x69 = TMD37823

``0x13`` is the status register (Read-Only).

``0x14`` and ``0x15`` are the low and high Clear data registers respectively.

``0x16`` and ``0x17`` are the low and high Red data registers respectively.

``0x18`` and ``0x19`` are the low and high Green data registers respectively.

``0x1A`` and ``0x1B`` are the low and high Blue data registers respectively.

``0x1C`` and ``0x1D`` are the low and high Proximity data registers respectively.

~~~~~~~~~~~~
Instructions
~~~~~~~~~~~~
The following dependencies are needed::

    import java.nio.ByteBuffer;
    import java.nio.ByteOrder;
    
    import edu.wpi.first.wpilibj.I2C;
    import frc.robot.Robot;


To enable a device, the following code will work for the REV Color Sensor::

    protected final static int COMMAND_REGISTER_BIT = 0x80;

    public ColorSensor() {
        sensor = new I2C(I2C.Port.kOnboard, 0x39); // port, I2c address

        sensor.write(COMMAND_REGISTER_BIT | 0x00, 0b00000011); // power on, color sensor on
    }

The following code writes ``0b00000011`` to ``0x00``. As of the 2019 season, it is unknown why the
bitwise or operation with COMMAND_REGISTER_BIT (0x80) is needed. It is also unknown what ``0b00000011`` 
means in the context of the color sensor.


To read from any register, the following code will work::

    protected final static int COMMAND_REGISTER_BIT = 0x80;
    protected final static int MULTI_BYTE_BIT = 0x20;
    
    protected int readWordRegister(int address) {
        ByteBuffer buf = ByteBuffer.allocate(2);
        sensor.read(COMMAND_REGISTER_BIT | MULTI_BYTE_BIT | address, 2, buf);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        return buf.getShort(0);
    }

ByteBuffer creates a bit of buffer for the data from the color sensor to temporarily stay in.
The read function reads 2 bytes from the requested address and stores it in the created buffer. 
It is currently unkown why the bitwise or operations with COMMAND_REGISTER_BIT (0x80) and 
MULTI_BYTE_BIT (0x20) are needed.

For example, to read the amount of red the color sensor is picking up, you would use the following commands::

    protected final static int RDATA_REGISTER = 0x16;
    
    public int red() {
        return readWordRegister(RDATA_REGISTER);
    }

This would essentially read from 0x16 and return the lower red byte. Unless if necessary, the upper byte registers 
usually will not be used.


For debugging purposes, the read and write functions return true if aborted, and false if the 
operation was successfully completed.    

~~~~~~~~~
Resources
~~~~~~~~~
`Chief Delphi Issue Forum <https://www.chiefdelphi.com/t/rev-color-sensor-v2-and-roborio-communication/342075/>`_

`Full Documentation for REV Color Sensor V2 <http://www.revrobotics.com/content/docs/TMD3782_v2.pdf/>`_

`Full Github Issue Thread <https://github.com/frc3197/2019-FRC/issues/1/>`_

`Documentation for the I2C class in WPILibJ <http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/I2C.html#read(int,int,byte%5B%5D)>`_