==================
frc.robot.RobotMap
==================
RobotMap deefines multiple parts of the Robot. Essentially, it "maps" out the robot 
by defining multiple different parts.

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
