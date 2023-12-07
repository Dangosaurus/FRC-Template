package ca.warp7.frc_template.subsystems.talon;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.math.util.Units;

public class TalonIOSparkMAX implements TalonIO {
    private final CANSparkMax talonMotor;
    private final RelativeEncoder talonEncoder;
    private final SparkMaxPIDController talonFeedback;

    public TalonIOSparkMAX(int talonMotorID) {
        talonMotor = new CANSparkMax(talonMotorID, MotorType.kBrushless);
        talonEncoder = talonMotor.getEncoder();
        talonFeedback = talonMotor.getPIDController();

        talonFeedback.setP(0.5);
        talonFeedback.setI(0);
        talonFeedback.setD(0);
        talonFeedback.setIZone(0);
        talonFeedback.setFF(0);
        talonFeedback.setOutputRange(-1, 1);
    }

    @Override
    public void setBrakeMode(boolean brakeEnabled) {
        if (brakeEnabled) {
            talonMotor.setIdleMode(IdleMode.kBrake);
        } else {
            talonMotor.setIdleMode(IdleMode.kCoast);
        }
    }

    @Override
    public void setTalonVoltage(double volts) {
        talonMotor.setVoltage(volts);
    }

    @Override
    public void setTargetTalonAngle(double targetRad) {
        talonFeedback.setReference(targetRad, ControlType.kPosition);
    }

    @Override
    public void updateInputs(TalonIOInputs inputs) {
        inputs.talonAngleRad = Units.rotationsToRadians(talonEncoder.getPosition());
        inputs.talonVelocityRadPerSec = Units.rotationsToRadians(talonEncoder.getVelocity());
    }
}
