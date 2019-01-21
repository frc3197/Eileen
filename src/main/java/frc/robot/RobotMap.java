package frc.robot;

public class RobotMap {
  public static enum CANSparkMaxID {
    FRONTLEFT(3, "FrontLeft"), BACKLEFT(4, "BackLeft"), FRONTRIGHT(1, "FrontRight"), BACKRIGHT(2, "BackRight"),
    ELEVATORLEFT(6, "ElevatorLeft"), ELEVATORRIGHT(5, "ElevatorRight");

    public final int id;
    public final String name;

    private CANSparkMaxID(int id, String name) {
      this.id = id;
      this.name = name;
    }
  };

  public static final double deadband = .05;
}
