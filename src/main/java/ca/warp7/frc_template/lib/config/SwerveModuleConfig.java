package ca.warp7.frc_template.lib.config;

import static edu.wpi.first.units.Units.Degrees;

import com.ctre.phoenix6.configs.Slot0Configs;
import edu.wpi.first.units.Angle;
import edu.wpi.first.units.Measure;

public class SwerveModuleConfig {

    public int ModuleID = 0;
    public String FriendlyName = "";

    public int DriveMotorID = 0;
    public int SteerMotorID = 0;
    public int SteerEncoderID = 0;

    public Measure<Angle> SteerEncoderOffset = Degrees.zero();

    public String DriveMotorCANBus = "";
    public String SteerMotorCANBus = "";
    public String SteerEncoderCANBus = "";

    public double DriveMotorGearRatio = 0;
    public double SteerMotorGearRatio = 0;

    public Slot0Configs DriveGains = new Slot0Configs();
    public Slot0Configs SteerGains = new Slot0Configs();
}
