package frc.robot;

public class RobotMap {
  public static enum CANSparkMaxID {
    FRONTLEFT(3), BACKLEFT(4), FRONTRIGHT(1), BACKRIGHT(2), ELEVATORLEFT(6), ELEVATORRIGHT(5);

    public final int id;

    private CANSparkMaxID(int id){
      this.id = id;
    }
  };
  
  public static final double deadband = .05;
}
