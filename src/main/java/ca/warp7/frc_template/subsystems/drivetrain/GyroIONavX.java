package ca.warp7.frc_template.subsystems.drivetrain;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

/* IO implementation for NavX */
public class GyroIONavX implements GyroIO {
    private final AHRS navx = new AHRS(SPI.Port.kMXP);

    public GyroIONavX() {
        navx.zeroYaw();
    }

    @Override
    public void updateInputs(GyroIOInputs inputs) {
        inputs.connected = navx.isConnected();
        inputs.yaw = navx.getRotation2d();
    }
}
