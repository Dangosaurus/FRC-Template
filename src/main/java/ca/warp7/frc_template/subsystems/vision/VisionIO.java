package ca.warp7.frc_template.subsystems.vision;

import edu.wpi.first.math.geometry.Translation2d;
import org.littletonrobotics.junction.AutoLog;

public interface VisionIO {
    @AutoLog
    public static class VisionIOInputs {
        public Translation2d botPose;
    }

    public default void updateInputs(VisionIOInputs inputs) {}
}
