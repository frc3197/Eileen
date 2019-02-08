===
I2C
===
As of the 2019 season, I2C has not been solved yet. The following contains the current research done on the REV Color Sensor V2, which uses I2C. It also contains links to resources explaining what I2C is and what it does.

-------------------
REV Color Sensor V2
-------------------
The REV Color Sensor V2 is a line sensor that uses I2C to communicate. WPILib contains libraries on communicating with I2C devices, and sending commands to the device require writing bits to specific addresses.

~~~~~~~~~~~~~~~~~~~
Important Addresses
~~~~~~~~~~~~~~~~~~~
``0x00`` is the enable register.

~~~~~~~~~
Resources
~~~~~~~~~
`Chief Delphi Issue Forum <https://www.chiefdelphi.com/t/rev-color-sensor-v2-and-roborio-communication/342075/>`_

`Full Documentation for REV Color Sensor V2 <http://www.revrobotics.com/content/docs/TMD3782_v2.pdf/>`_

`Full Github Issue Thread <https://github.com/frc3197/2019-FRC/issues/1/>`_
