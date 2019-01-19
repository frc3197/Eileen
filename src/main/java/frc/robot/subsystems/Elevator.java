package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Elevate;

public class Elevator extends Subsystem {
  private CANSparkMax left = new CANSparkMax(6, MotorType.kBrushless);
  private CANSparkMax right = new CANSparkMax(5, MotorType.kBrushless);
  private DigitalInput upperLimitSwitch = new DigitalInput(1);
  private DigitalInput lowerLimitSwitch = new DigitalInput(2);

  private SpeedControllerGroup elevatorGroup = new SpeedControllerGroup(left, right);

  public Elevator() {
    super();
    left.setInverted(true);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Elevate());
  }

  public void drive(double speed) {
    //if((Math.abs(speed) > .1) ){ Scheduler.getInstance().disable();}
    double output = speed;
      if(upperLimitSwitch.get() == false)
        output = Math.min(output, 0); //If top pressed(returning a zero value), only drive negative
      if(lowerLimitSwitch.get() == false)
        output = Math.max(output, 0);  //If bottom pressed, only drive positive
      elevatorGroup.set(output);
  }
}
