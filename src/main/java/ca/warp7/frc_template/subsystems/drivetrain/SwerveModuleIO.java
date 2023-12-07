package ca.warp7.frc_template.subsystems.drivetrain;

import edu.wpi.first.math.geometry.Rotation2d;
import org.littletonrobotics.junction.AutoLog;

public interface SwerveModuleIO {
    @AutoLog
    public static class SwerveModuleIOInputs {
        public double drivePositionRad = 0.0;
        public double driveVelocityRadPerSec = 0.0;
        public double driveAppliedVolts = 0.0;
        public double[] driveStatorCurrentAmps = new double[] {};

        public Rotation2d steerPosition = new Rotation2d();
        public double steerVelocityRadPerSec = 0.0;
        public double steerAppliedVolts = 0.0;
        public double[] steerStatorCurrentAmps = new double[] {};

        public Rotation2d absoluteSteerPosition = new Rotation2d();
    }

    public default void setDriveVoltage(double volts) {}

    public default void setSteerVoltage(double volts) {}

    public default void setDriveBrakeMode(boolean enabled) {}

    public default void setSteerBrakeMode(boolean enabled) {}

    public default void updateInputs(SwerveModuleIOInputs inputs) {}
}
