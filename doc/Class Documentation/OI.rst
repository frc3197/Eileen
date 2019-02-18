============
frc.robot.OI
============

OI defines all of the buttons on the joystick that control the robot. OI stands for "Operator Input." 
Within OI are placed constructers for the Controllers and respective buttons, as well as what each buttons 
does.

------------
Dependencies
------------

- ``edu.wpi.first.wpilibj.GenericHID.Hand``
- ``edu.wpi.first.wpilibj.XboxController``
- ``edu.wpi.first.wpilibj.buttons.JoystickButton``
- ``edu.wpi.first.wpilibj.buttons.POVButton``

--------
Commands
--------

~~~~~~
static
~~~~~~
Pre-generated in a new robot project. Place whenPressed commands here.

Example::

    driverDPadLeft.whenPressed(Robot.driveTrain.changeDriveGyro);

    secondaryA.whenPressed(Robot.arm.reset);

To do: Figure out in what cases would a button require whenPressed v. an actual function.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static double arcadeDriveY()
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Drives the motors in arcade drive mode. Moves Robot forwards and backwards.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static double arcadeDriveR()
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Drives the motors in arcade drive mode. Moves Robot right and left.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static double tankDriveLeft()
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Drives the motors in tank drive mode. Controls the drive motors on the left side.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static double tankDriveRight()
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Drives the motors in tank drive mode. Controls the drive motors on the right side.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static double elevatorSpeed()
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Drives the elevator motors in order to move the elevator up and down.
Math::

    secondaryRightTrigger - secondaryLeftTrigger
    //if secondaryRightTrigger has a value, then elevator motor will move in a positive manner.
    //if secondaryLeftTrigger has a value, then elevator motor will move in a negative manner.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static double elbowSpeed()
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Drives the elbow motor. Multiplied by a constant (0.25) to make it slower.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static double wristSpeed()
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Drives the wrist motor. Multiplied by a constant (0.5) to make it slower.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static double erectorSpeed()
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Drives the climber motor in order to move the knives backwards and forwards.
Math::

    driverRightTrigger - driverLeftTrigger
    //if driverRightTrigger has a value, then climber motor will move in a positive manner.
    //if driverLeftTrigger has a value, then climber motor will move in a negative manner.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static double manipulatorSpeed()
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Drives the manipulator in order to move the wheels that suck the ball in.
Math/Logic::

    (if secondaryRightBumper is pressed, return 1 otherwise 0) + (if secondaryLeftBumper is pressed, return -1 otherwise 0)
    //allows for both bumpers to be pressed, but rollers will not move.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static double hatchSpeed()
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Drives the hatch mechanism in order to move the beak that hooks up hatch panels.
Math/Logic::

    (if driverRightBumper is pressed, return 1 otherwise 0) + (if driverLeftBumper is pressed, return -1 otherwise 0)
    //allows for both bumpers to be pressed, but rollers will not move.


