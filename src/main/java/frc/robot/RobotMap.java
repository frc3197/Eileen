package frc.robot;

public class RobotMap {

  public static enum DriveTrainSide {
    LEFT, RIGHT, BOTH;
  }

  public static enum CANSparkMaxID {
    // TODO: Change for the real robot !!! very important change can id on 2017
    // practice bot
    FRONT_LEFT(14, "FrontLeft"), BACK_LEFT(15, "BackLeft"), FRONT_RIGHT(1, "FrontRight"), BACK_RIGHT(0, "BackRight"),
    ELEVATOR_LEFT(2, "ElevatorLeft"), ELEVATOR_RIGHT(13, "ElevatorRight"), ARM_WRIST(12, "ArmWrist"),
    ARM_ELBOW(3, "ArmElbow"), ARM_BALL_MANIPULATOR(11, "BallIntake");

    public final int id;
    public final String name;

    private CANSparkMaxID(int id, String name) {
      this.id = id;
      this.name = name;
    }
  };

  public static final double xMax = 640;
  public static final double visionTargetX = .5;
  public static final double visionTargetArea = 32000;

  public static enum VisionPID {
    // TODO Change these
    P(0), I(0), D(0), F(0);

    public final double val;

    private VisionPID(double val) {
      this.val = val;
    }
  };

  public static enum CANSparkPID {
    P(0), I(0), D(0), F(0);

    public final double val;

    private CANSparkPID(double val) {
      this.val = val;
    }
  };

  public static enum ElevatorPreset {
    // TODO Change these for real bot
    HATCH_LEVEL_ONE(29), HATCH_LEVEL_TWO(56), HATCH_LEVEL_THREE(83), CARGO_LEVEL_ONE(31), CARGO_LEVEL_TWO(60),
    CARGO_LEVEL_THREE(87), CARGO_LOADING_LEVEL(35), CARGO_SHIP_CARGO(35);
    public final double pos;

    private ElevatorPreset(double pos) {
      this.pos = pos;
    }
  }

  // TODO change me
  public static final double elevatorPresetThreshold = 1;

  public static final double deadband = 0.08;

  public static final double gyroDegreeSensitivity = 0.01;

  public static final double elevatorDegreeSensitivity = 0.15;

  public static final double elevatorExponent = 0.5;

  public static final int gyroChannel = 0;
}
