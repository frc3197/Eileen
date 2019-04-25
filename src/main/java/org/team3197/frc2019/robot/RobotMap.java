package org.team3197.frc2019.robot;

public class RobotMap {

  public static enum CANSparkMaxID {
    kFrontLeft(14, "FrontLeft"), kBackLeft(15, "BackLeft"), kFrontRight(1, "FrontRight"), kBackRight(16, "BackRight"),
    kElevatorLeft(2, "ElevatorLeft"), kElevatorRight(13, "ElevatorRight"), kWrist(12, "Wrist"), kElbow(3, "Elbow"),
    kIntake(11, "Intake"), kHatch(10, "Hatch"), kErectorLeft(5, "ErectorLeft"), kErectorRight(4, "ErectorRight"),
    kLiftVertical(8, "LiftVertical"), kLiftHorizontal(9, "LiftHorizontal");

    public final int id;
    public final String name;

    private CANSparkMaxID(int id, String name) {
      this.id = id;
      this.name = name;
    }
  };

  public static enum ElbowPreset {
    kHatchOne(-17), kHatchTwo(-146), kHatchThree(-146), kCargoOne(-35), kCargoTwo(-35), kCargoThree(-35),
    kCargoShip(-35);
    public double elbowPos;

    private ElbowPreset(double elbowPos) {
      this.elbowPos = elbowPos;
    }
  }

  public static enum ElevatorPreset {
    kHatchLevelOne(-26), kHatchLevelTwo(-18), kHatchLevelThree(25), kCargoLevelOne(-26), kCargoLevelTwo(19),
    kCargoLevelThree(53), kCargoLoadingLevel(7.85), kCargoShipCargo(25);
    public double pos;

    private ElevatorPreset(double pos) {
      this.pos = pos;
    }
  }

  public static enum DeadbandType {
    kElevator(0.05), kElbow(0.06), kWrist(0.05), kDrive(0.08), kClimberVertical(0.05), kErector(0.05),
    kClimberGyroVal(5);
    public double speed;

    private DeadbandType(double speed) {
      this.speed = speed;
    }
  }

  public static enum DriveTrainSide {
    LEFT, RIGHT, BOTH;
  }

  public static enum Channel {
    kWristGyro(0);
    public final int channel;

    private Channel(int channel) {
      this.channel = channel;
    }
  };

  public static enum GyroSensitivity {
    kDrive(0.01), kArm(-0.011), kClimber(0.005);
    public double val;

    private GyroSensitivity(double val) {
      this.val = val;
    }
  };

  public static enum MaxSpeeds {
    kElevator(.7, -.5), kArm(.38), kWrist(.10), kHatch(1), kCargo(1), kClimberVertical(.7), kClimberHorizontal(.7),
    kErector(.3);

    public double forwardSpeed;
    public double reverseSpeed;

    private MaxSpeeds(double forwardSpeed, double reverseSpeed) {
      this.forwardSpeed = forwardSpeed;
      this.reverseSpeed = reverseSpeed;
    }

    private MaxSpeeds(double speed) {
      this.forwardSpeed = speed;
      this.reverseSpeed = -speed;
    }
  }

  public static final double elevatorPresetThreshold = 1.5;

  public static final double elbowPresetThreshold = 1.5;

  public static final double elbowVelocityPresetThreshold = 60;

  public static final double elevatorDegreeSensitivity = 0.05;

  public static final double elbowDegreeSensitivity = 0.05;

  public static final double elevatorSpeedMultiplier = 0.5;

  public static final double elbowSpeedMultiplier = 0.5;

  public static final double wristSpeedMultiplier = 0.5;

  public static final double erectorSpeedMultiplier = 0.5;

}
