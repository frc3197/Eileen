package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class VisionAlignment extends PIDSubsystem {
  public VisionAlignment() {
    super("SubsystemName", 1, 2, 3);

    setSetpoint(0);
    enable();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(null);
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;

    return 0.0;
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
  }
}
