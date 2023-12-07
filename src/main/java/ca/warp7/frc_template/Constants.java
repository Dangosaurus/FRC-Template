package ca.warp7.frc_template;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.mechanisms.swerve.SwerveModuleConstantsFactory;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.*;

public class Constants {
    public static enum MODE {
        REAL,
        REPLAY,
        SIM
    }

    public static MODE ROBOT_MODE = MODE.SIM;

    public static final Measure<Voltage> BATTERY_NOMINAL_VOLTAGE = Volts.of(12);

    public static enum GOALS {
        NONE,
        CONE_STOW,
        CUBE_STOW,
        MID_GOAL,
        HIGH_GOAL,
        GROUND_PICKUP,
        SINGLE_SUBSTATION_CONE,
        SINGLE_SUBSTATION_CUBE,
        DOUBLE_SUBSTATION
    }

    /* Drivetrain Information */
    public static final class DRIVETRAIN {
        // public static final Measure<Distance> TRACK_WIDTH = Inches.of(0);
        // public static final Measure<Distance> WHEEL_BASE = Inches.of(0);
        public static final double TRACK_WIDTH_X_INCHES = 0;
        public static final double TRACK_WIDTH_Y_INCHES = 0;

        // public static final Measure<Distance> WHEEL_DIAMETER = Inches.of(0);
        public static final double WHEEL_DIAMETER = Units.inchesToMeters(4);
        // public static final Measure<Distance> WHEEN_CIRCUMFERENCE = WHEEL_DIAMETER.times(Math.PI);

        // Theoretical maximum speed for Falcon500's and SDS MK4i L2 configuration
        public static final Measure<Velocity<Distance>> MAX_SPEED = FeetPerSecond.of(16.3);
        // Calculated using the long side of the robot and max speed
        // public static final Measure<Velocity<Angle>> MAX_ANGULAR_SPEED =
        //         RotationsPerSecond.of(MAX_SPEED.baseUnitMagnitude()
        //                 / (Math.max(TRACK_WIDTH.baseUnitMagnitude(), WHEEL_BASE.baseUnitMagnitude()) / 2));

        // public static final Measure<Velocity<Angle>> MAX_ANGULAR_SPEED_ROT_PER_SEC =
        //         RotationsPerSecond.of(MAX_SPEED.baseUnitMagnitude()
        //                 / (Math.max(TRACK_WIDTH.baseUnitMagnitude(), WHEEL_BASE.baseUnitMagnitude()) / 2));

        // Gear ratios for SDS MK4i L2 configuration
        public static final double DRIVE_GEAR_RATIO = 6.75 / 1;
        public static final double STEER_GEAR_RATIO = (150.0 / 7.0) / 1.0;

        public static final Measure<Voltage> kS = Volts.of(0);
        public static final Measure<Per<Voltage, Velocity<Angle>>> kV = VoltsPerRadianPerSecond.of(0);
        public static final Measure<Per<Voltage, Velocity<Velocity<Angle>>>> kA = VoltsPerRadianPerSecondSquared.of(0);

        // public static final SwerveDriveKinematics SWERVE_DRIVE_KINEMATICS = new SwerveDriveKinematics(
        //         new Translation2d(TRACK_WIDTH.in(Meters) / 2.0, WHEEL_BASE.in(Meters) / 2.0),
        //         new Translation2d(TRACK_WIDTH.in(Meters) / 2.0, -WHEEL_BASE.in(Meters) / 2.0),
        //         new Translation2d(-TRACK_WIDTH.in(Meters) / 2.0, WHEEL_BASE.in(Meters) / 2.0),
        //         new Translation2d(-TRACK_WIDTH.in(Meters) / 2.0, -WHEEL_BASE.in(Meters) / 2.0));

        public static final Translation2d[] SWERVE_MODULE_TRANSLATIONS = new Translation2d[] {
            new Translation2d(TRACK_WIDTH_X_INCHES / 2.0, TRACK_WIDTH_Y_INCHES / 2.0),
            new Translation2d(TRACK_WIDTH_X_INCHES / 2.0, -TRACK_WIDTH_Y_INCHES / 2.0),
            new Translation2d(-TRACK_WIDTH_X_INCHES / 2.0, TRACK_WIDTH_Y_INCHES / 2.0),
            new Translation2d(-TRACK_WIDTH_X_INCHES / 2.0, -TRACK_WIDTH_Y_INCHES / 2.0)
        };

        public static final class MODULES {
            private static final SwerveModuleConstantsFactory CONSTANTS_FACTORY = new SwerveModuleConstantsFactory()
                    .withDriveMotorGearRatio(DRIVE_GEAR_RATIO)
                    .withSteerMotorGearRatio(STEER_GEAR_RATIO)
                    .withWheelRadius(4)
                    .withSlipCurrent(800)
                    .withDriveMotorGains(null)
                    .withSteerMotorGains(null);
        }
    }

    /* Operator Interface */
    public static final class OI {}
}
