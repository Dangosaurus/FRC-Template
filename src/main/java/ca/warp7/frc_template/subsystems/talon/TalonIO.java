package ca.warp7.frc_template.subsystems.talon;

import org.littletonrobotics.junction.AutoLog;

public interface TalonIO {
    @AutoLog
    public class TalonIOInputs {
        public double talonAngleRad;
        public double talonVelocityRadPerSec;
        public double talonAppliedVolts;
        public double talonStatorCurrentAmps;
    }

    public default void setBrakeMode(boolean brakeEnabled) {}

    public default void setTargetTalonAngle(double angleRad) {}

    public default void setTalonVoltage(double volts) {}

    public default void updateInputs(TalonIOInputs inputs) {}
}
