package ca.warp7.frc_template.subsystems.talon;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class TalonIOSim implements TalonIO {
    private static final DCMotor talonMotor = DCMotor.getNeo550(1).withReduction(25);
    private SingleJointedArmSim talonSim = new SingleJointedArmSim(talonMotor, 1, 0.01, 0.15, 0, 2 * Math.PI, false, 0);

    private PIDController feedback = new PIDController(0.5, 0, 0);

    double appliedVolts = 0;

    public TalonIOSim() {
        feedback.setTolerance(0.01);
    }

    @Override
    public final void updateInputs(TalonIOInputs inputs) {
        appliedVolts = MathUtil.clamp(feedback.calculate(talonSim.getAngleRads()), -12, 12);

        talonSim.setInputVoltage(appliedVolts);

        talonSim.update(0.02);

        inputs.talonAngleRad = talonSim.getAngleRads();
        inputs.talonAppliedVolts = appliedVolts;
        inputs.talonVelocityRadPerSec = talonSim.getVelocityRadPerSec();
        inputs.talonStatorCurrentAmps = talonSim.getCurrentDrawAmps();
    }

    @Override
    public void setTargetTalonAngle(double angleRad) {
        feedback.setSetpoint(angleRad);
    }
}
