The class frc.robot.Robot 
(inheriting methods from wdu.wpi.first.wpilibj.TimedRobot) 
is the main class that contains the functions that initializes the robot.

Subsystems should be initialized here.

Example: ``public static SubsystemBoi subsystemBoi = new SubSystemBoi();``

Network tables should also be initialized here.

Example:: 

    public static NetWorkTableInstance ntInst = NetworkTableInstance.getDefault();
    public static NetworkTable table;

--------
Commands
--------

~~~~~~~~~~~
robotInit()
~~~~~~~~~~~
Initializes the Robot.

~~~~~~~~~~~~~~~
robotPeriodic()
~~~~~~~~~~~~~~~
Code that runs periodically goes here.

~~~~~~~~~~~~~~
disabledInit()
~~~~~~~~~~~~~~
Initializes disabled mode.

~~~~~~~~~~~~~~~~~~
disabledPeriodic()
~~~~~~~~~~~~~~~~~~
Code that runs periodically while the robot is disabled goes here.

~~~~~~~~~~~~~~~~
autonomousInit()
~~~~~~~~~~~~~~~~
Initializes autonomous mode.

~~~~~~~~~~~~~~~~~~~~
autonomousPeriodic()
~~~~~~~~~~~~~~~~~~~~
Code that runs periodically while the robot is in autonomous goes here.

~~~~~~~~:~~~~
teleopInit()
~~~~~~~~~~~~
Initializes teleop mode.

~~~~~~~~~~~~~~~~
teleopPeriodic()
~~~~~~~~~~~~~~~~
Code that runs periodically while the robot is in teleop goes here.

~~~~~~~~~~
testInit()
~~~~~~~~~~
Initializes test mode.

~~~~~~~~~~~~~~
testPeriodic()
~~~~~~~~~~~~~~
Code that runs periodically while the robot is in test goes here.
