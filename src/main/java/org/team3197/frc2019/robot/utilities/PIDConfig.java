package org.team3197.frc2019.robot.utilities;

public final class PIDConfig {

    public final double kP;
    public final double kI;
    public final double kD;
    public final double kIz;
    public final double kFF;
    public final double kMaxOutput;
    public final double kMinOutput;

    public PIDConfig(double kP, double kI, double kD, double kIz, double kFF, double kMaxOutput, double kMinOutput) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kIz = kIz;
        this.kFF = kFF;
        this.kMaxOutput = kMaxOutput;
        this.kMinOutput = kMinOutput;
    }
}
