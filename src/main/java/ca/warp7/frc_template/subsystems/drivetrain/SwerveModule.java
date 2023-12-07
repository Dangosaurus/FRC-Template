package ca.warp7.frc_template.subsystems.drivetrain;

import ca.warp7.frc_template.Constants.DRIVETRAIN;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import org.littletonrobotics.junction.Logger;

public class SwerveModule {
    private final SwerveModuleIO moduleIO;
    private final int moduleID;
    private final String moduleName;

    private final SwerveModuleIOInputsAutoLogged moduleInputs = new SwerveModuleIOInputsAutoLogged();
    private Rotation2d finalSteerOffset = null;

    public SwerveModule(SwerveModuleIO moduleIO, int moduleID, String moduleName) {
        this.moduleIO = moduleIO;
        this.moduleID = moduleID;
        this.moduleName = moduleName;
    }

    public double getDistanceMeters() {
        return moduleInputs.drivePositionRad * DRIVETRAIN.WHEEL_DIAMETER / 2;
    }

    public double getSpeedMetersPerSec() {
        return moduleInputs.driveVelocityRadPerSec * DRIVETRAIN.WHEEL_DIAMETER / 2;
    }

    public Rotation2d getAngle() {
        return finalSteerOffset == null ? new Rotation2d() : moduleInputs.steerPosition.plus(finalSteerOffset);
    }

    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(getDistanceMeters(), getAngle());
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(getSpeedMetersPerSec(), getAngle());
    }

    public void setTargetState(SwerveModuleState state) {
        var optimizedState = SwerveModuleState.optimize(state, getAngle());

        
    }

    public void periodic() {
        moduleIO.updateInputs(moduleInputs);
        Logger.processInputs("Drivetrain/Module" + Integer.toString(moduleID), moduleInputs);

        if (finalSteerOffset == null && moduleInputs.absoluteSteerPosition.getRadians() != 0.0) {
            finalSteerOffset = moduleInputs.absoluteSteerPosition.minus(moduleInputs.steerPosition);
        }
    }
}
