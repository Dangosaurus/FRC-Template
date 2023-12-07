package ca.warp7.frc_template.subsystems.drivetrain;

import static ca.warp7.frc_template.Constants.DRIVETRAIN.*;

import ca.warp7.frc_template.Constants.DRIVETRAIN;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class SwerveDrivetrainSubsystem extends SubsystemBase {

    private final GyroIO gyroIO;
    private final GyroIOInputsAutoLogged gyroInputs = new GyroIOInputsAutoLogged();

    private final SwerveModule[] swerveModules;
    private SwerveModuleState[] swerveModuleStates;

    private final SwerveDriveKinematics swerveDriveKinematics = new SwerveDriveKinematics(SWERVE_MODULE_TRANSLATIONS);

    public SwerveDrivetrainSubsystem(
            GyroIO gyroIO,
            SwerveModuleIO frontLeftSwerveModuleIO,
            SwerveModuleIO frontRightSwerveModuleIO,
            SwerveModuleIO backLeftSwerveModuleIO,
            SwerveModuleIO backRightSwerveModuleIO) {
        this.gyroIO = gyroIO;

        swerveModules = new SwerveModule[] {
            new SwerveModule(frontLeftSwerveModuleIO, 0, "Front Left"),
            new SwerveModule(frontRightSwerveModuleIO, 1, "Front Right"),
            new SwerveModule(backLeftSwerveModuleIO, 2, "Back Left"),
            new SwerveModule(backRightSwerveModuleIO, 3, "Back Right")
        };
    }

    public void setTargetChassisSpeeds(ChassisSpeeds speeds) {
        ChassisSpeeds discreteSpeeds = ChassisSpeeds.discretize(speeds, 0.02);
        SwerveModuleState[] swerveModuleStates = swerveDriveKinematics.toSwerveModuleStates(discreteSpeeds);
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, DRIVETRAIN.MAX_SPEED.magnitude());


        
    }

    @Override
    public void periodic() {
        gyroIO.updateInputs(gyroInputs);
        Logger.processInputs("Drivetrain/Gyro", gyroInputs);
    }
}
