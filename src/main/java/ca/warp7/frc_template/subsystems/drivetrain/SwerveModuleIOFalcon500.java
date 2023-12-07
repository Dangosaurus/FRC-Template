package ca.warp7.frc_template.subsystems.drivetrain;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class SwerveModuleIOFalcon500 implements SwerveModuleIO {
    private final TalonFX driveMotor;
    private final TalonFX steerMotor;
    private final CANcoder absoluteSteerEncoder;

    private final Rotation2d absoluteSteerEncoderOffset;

    private final StatusSignal<Double> driveMotorPosition;
    private final StatusSignal<Double> driveMotorVelocity;
    private final StatusSignal<Double> driveMotorAppliedVolts;
    private final StatusSignal<Double> driveMotorStatorCurrent;

    private final StatusSignal<Double> steerMotorPosition;
    private final StatusSignal<Double> steerMotorVelocity;
    private final StatusSignal<Double> steerMotorAppliedVolts;
    private final StatusSignal<Double> steerMotorStatorCurrent;

    private final StatusSignal<Double> absoluteSteerEncoderPosition;

    public static final double DRIVE_GEAR_RATIO = 6.75 / 1;
    public static final double STEER_GEAR_RATIO = (150.0 / 7.0) / 1.0;

    public SwerveModuleIOFalcon500(
            int driveMotorID,
            String driveMotorBus,
            int steerMotorID,
            String steerMotorBus,
            int absoluteSteerEncoderID,
            String absoluteSteerEncoderBus,
            Rotation2d absoluteSteerEncoderOffset) {
        driveMotor = new TalonFX(driveMotorID, driveMotorBus);
        steerMotor = new TalonFX(steerMotorID, steerMotorBus);
        absoluteSteerEncoder = new CANcoder(absoluteSteerEncoderID, absoluteSteerEncoderBus);

        this.absoluteSteerEncoderOffset = absoluteSteerEncoderOffset;

        TalonFXConfiguration driveMotorConfig = new TalonFXConfiguration();
        driveMotorConfig.CurrentLimits.StatorCurrentLimit = 40.0;
        driveMotorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        driveMotor.getConfigurator().apply(driveMotorConfig);

        TalonFXConfiguration steerMotorConfig = new TalonFXConfiguration();
        steerMotorConfig.CurrentLimits.StatorCurrentLimit = 30.0;
        steerMotorConfig.CurrentLimits.StatorCurrentLimitEnable = true;
        steerMotor.getConfigurator().apply(steerMotorConfig);

        CANcoderConfiguration absoluteEncoderConfig = new CANcoderConfiguration();
        absoluteSteerEncoder.getConfigurator().apply(absoluteEncoderConfig);

        driveMotorPosition = driveMotor.getPosition();
        driveMotorVelocity = driveMotor.getVelocity();
        driveMotorAppliedVolts = driveMotor.getMotorVoltage();
        driveMotorStatorCurrent = driveMotor.getStatorCurrent();

        steerMotorPosition = steerMotor.getPosition();
        steerMotorVelocity = steerMotor.getVelocity();
        steerMotorAppliedVolts = steerMotor.getMotorVoltage();
        steerMotorStatorCurrent = steerMotor.getStatorCurrent();

        absoluteSteerEncoderPosition = absoluteSteerEncoder.getAbsolutePosition();

        BaseStatusSignal.setUpdateFrequencyForAll(100, driveMotorPosition, steerMotorPosition);
        BaseStatusSignal.setUpdateFrequencyForAll(
                50,
                driveMotorVelocity,
                driveMotorAppliedVolts,
                driveMotorStatorCurrent,
                steerMotorVelocity,
                steerMotorAppliedVolts,
                steerMotorStatorCurrent,
                absoluteSteerEncoderPosition);
        driveMotor.optimizeBusUtilization();
        steerMotor.optimizeBusUtilization();
        absoluteSteerEncoder.optimizeBusUtilization();
    }

    @Override
    public void updateInputs(SwerveModuleIOInputs inputs) {
        BaseStatusSignal.refreshAll(
                driveMotorPosition,
                driveMotorVelocity,
                driveMotorAppliedVolts,
                driveMotorStatorCurrent,
                steerMotorPosition,
                steerMotorVelocity,
                steerMotorAppliedVolts,
                steerMotorStatorCurrent,
                absoluteSteerEncoderPosition);

        inputs.drivePositionRad = Units.rotationsToRadians(driveMotorPosition.getValueAsDouble() / DRIVE_GEAR_RATIO);
        inputs.driveVelocityRadPerSec =
                Units.rotationsToRadians(driveMotorVelocity.getValueAsDouble() / DRIVE_GEAR_RATIO);
        inputs.driveAppliedVolts = driveMotorAppliedVolts.getValueAsDouble();
        inputs.driveStatorCurrentAmps = new double[] {driveMotorStatorCurrent.getValueAsDouble()};

        inputs.steerPosition = Rotation2d.fromRotations(driveMotorPosition.getValueAsDouble() / STEER_GEAR_RATIO);
        inputs.steerVelocityRadPerSec =
                Units.rotationsToRadians(driveMotorVelocity.getValueAsDouble() / STEER_GEAR_RATIO);
        inputs.steerAppliedVolts = driveMotorAppliedVolts.getValueAsDouble();
        inputs.steerStatorCurrentAmps = new double[] {driveMotorStatorCurrent.getValueAsDouble()};

        inputs.absoluteSteerPosition = Rotation2d.fromRotations(absoluteSteerEncoderPosition.getValueAsDouble())
                .minus(absoluteSteerEncoderOffset);
    }
}
