==================
org.team3197.frc2019.robot.RobotMap
==================
RobotMap deefines multiple parts of the Robot. Essentially, it "maps" out the robot 
by defining multiple different parts and constants.

Example:: 
    
    public static final double visionTargetArea = 32000;

------------
Dependencies
------------
No Dependencies

--------
Commands
--------

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static enum CANSparkMaxID
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Numbers the CANSparkMax motor controllers.

See `Motor Controller Spreadsheet <https://docs.google.com/spreadsheets/d/14p9fdd08mrI9wpgqd_k9QANKFcTs7CDPGgKoO7wAz68/edit?usp=sharing>`_ for more details.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static enum ArmPreset
~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Sets the preset positions for the arm when placing Hatch Panels or Cargo.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static enum ElevatorPreset
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Sets the preset positions for the elevator when placing Hatch Panels or Cargo.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static enum DeadbandType
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Sets the deadband for specific motors. Deadband is in decimal percent (10% = 0.1)

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static enum DriveTrainSide
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Used only for Drive Train Test. Sets options for left, right, or both sides of the drive train.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static enum CANSparkPID
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Sets PID values for CANSparkMax's. Used but all 0's so useless.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static enum ElevatorPID
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Sets PID values for the Elevator motors. Used but all 0's so useless.

~~~~~~~~~~~~~~~~~~~~~~~~~~
public static enum Channel
~~~~~~~~~~~~~~~~~~~~~~~~~~
Defines ports where Gyros are placed.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static enum GyroSensitivity
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Defines multiplier for Gyro stuff.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static enum MaxSpeeds
~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Defines multipliers that sets max speeds for certain motors.

~~~~~~~~~~~~~~~~~~~~~~~~~~~
public static enum MaxSpeed
~~~~~~~~~~~~~~~~~~~~~~~~~~~
SOON TO BE ERASED FROM EXISTENCE.

~~~~

~~~~
