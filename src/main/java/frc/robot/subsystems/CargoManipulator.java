package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.defaults.Manipulate;

public class CargoManipulator extends Subsystem implements Drivable {

  private CANSparkMax roller = new CANSparkMax(RobotMap.CANSparkMaxID.kCargoManipulator.id, MotorType.kBrushless);

  public CargoManipulator() {
    super();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Manipulate(this));
  }

  public void drive(double speed, boolean hold) {
    roller.set(speed);
  }
}
