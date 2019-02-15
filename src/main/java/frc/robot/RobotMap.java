package frc.robot;

public class RobotMap {

  public static enum CANSparkMaxID {
    // TODO: Change for the real robot !!
    // practice bot
    kFrontLeft(14, "FrontLeft"), kBackLeft(15, "BackLeft"), kFrontRight(1, "FrontRight"), kBackRight(0, "BackRight"),
    kElevatorLeft(2, "ElevatorLeft"), kElevatorRight(13, "ElevatorRight"), kWrist(12, "Wrist"), kElbow(3, "Elbow"),
    kCargoManipulator(11, "CargoManipulator"), kHatch(10, "Hatch"), kErectorLeft(5, "ErectorLeft"),
    kErectorRight(4, "ErectorRight");

    public final int id;
    public final String name;

    private CANSparkMaxID(int id, String name) {
      this.id = id;
      this.name = name;
    }
  };

  public static enum ArmPreset {
    // TODO Change these for real bot
    kHatch(0), kCargoRocket(0), kCargoShipDump(0);
    public final double pos;

    private ArmPreset(double pos) {
      this.pos = pos;
    }
  }

  public static enum ElevatorPreset {
    kHatchLevelOne(29), kHatchLevelTwo(56), kHatchLevelThree(83), kCargoLevelOne(31), kCargoLevelTwo(60),
    kCargoLevelThree(87), kCargoLoadingLevel(35), kCargoShipCargo(35);
    public final double pos;

    private ElevatorPreset(double pos) {
      this.pos = pos;
    }
  }

  public static enum DeadbandType {
    kElevator(0.03), kElbow(0.03), kWrist(0.02), kDrive(0.08);
    public final double speed;

    private DeadbandType(double speed) {
      this.speed = speed;
    }
  }

  public static enum DriveTrainSide {
    LEFT, RIGHT, BOTH;
  }

  public static enum CANSparkPID {
    P(0), I(0), D(0), F(0);

    public final double val;

    private CANSparkPID(double val) {
      this.val = val;
    }
  };

  /**
   * public static enum VisionPID { // TODO Change these P(0), I(0), D(0), F(0);
   * 
   * public final double val;
   * 
   * private VisionPID(double val) { this.val = val; } };
   */

  public static final double elevatorPresetThreshold = 1;

  public static final double wristPresetThreshold = 1;

  public static final double gyroDegreeSensitivity = 0.01;

  public static final double elevatorDegreeSensitivity = 0.15;

  public static final double wristDegreeSensitivity = 0.15;

  public static final double elevatorExponent = 0.5;

  public static final double wristExponent = 0.5;

  public static final int gyroChannel = 0;

  public static final double xMax = 640;

  public static final double visionTargetX = .5;

  public static final double visionTargetArea = 32000;
}
