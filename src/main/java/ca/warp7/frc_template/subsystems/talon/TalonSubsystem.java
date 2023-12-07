package ca.warp7.frc_template.subsystems.talon;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class TalonSubsystem extends SubsystemBase {

    private final TalonIO talonIO;
    private final TalonIOInputsAutoLogged talonInputs = new TalonIOInputsAutoLogged();

    public TalonSubsystem(TalonIO talonIO) {
        this.talonIO = talonIO;
    }

    public Command setTargetTalonRad(double targetRad) {
        return this.runOnce(() -> talonIO.setTargetTalonAngle(targetRad));
    }

    public void periodic() {
        talonIO.updateInputs(talonInputs);
        Logger.processInputs("Intake/Talon", talonInputs);
    }
}
