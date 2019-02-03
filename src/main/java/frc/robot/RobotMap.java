package frc.robot;

public class RobotMap {

  public static enum DriveTrainSide {
    LEFT, RIGHT, BOTH;
  }

  public static enum CANSparkMaxID {
    // TODO: Change for the real robot
    FRONTLEFT(14, "FrontLeft"), BACKLEFT(15, "BackLeft"), FRONTRIGHT(1, "FrontRight"), BACKRIGHT(0, "BackRight"),
    ELEVATORLEFT(13, "ElevatorLeft"), ELEVATORRIGHT(2, "ElevatorRight");

    public final int id;
    public final String name;

    private CANSparkMaxID(int id, String name) {
      this.id = id;
      this.name = name;
    }
  };

  public static enum CANSparkPID {
    P(0), I(0), D(0), F(0);

    public final double val;

    private CANSparkPID(double val) {
      this.val = val;
    }
  };

  public static final double visionTargetX = .5;
  public static final double visionTargetArea = 500;

  public static enum VisionPID {
    // TODO Change these
    P(0), I(0), D(0), F(0);

    public final double val;

    private VisionPID(double val) {
      this.val = val;
    }
  };

  public static enum ElevatorPreset {
    // TODO Change these
    LEVELA(0), LEVELB(0), LEVELC(0);
    public final double pos;

    private ElevatorPreset(double pos) {
      this.pos = pos;
    }

  }

  // TODO change me
  public static final double elevatorPresetThreshold = 0;

  public static final double deadband = .05;

  public static final double gyroDegreeSensitivity = 0.01;
}
