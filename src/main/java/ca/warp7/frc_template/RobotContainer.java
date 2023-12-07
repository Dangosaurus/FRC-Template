// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package ca.warp7.frc_template;

import ca.warp7.frc_template.subsystems.talon.TalonIO;
import ca.warp7.frc_template.subsystems.talon.TalonIOSim;
import ca.warp7.frc_template.subsystems.talon.TalonIOSparkMAX;
import ca.warp7.frc_template.subsystems.talon.TalonSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
    /* Subsystems */
    private final TalonSubsystem talonSubsystem;

    /* Controllers */
    private final CommandXboxController operator = new CommandXboxController(1);

    /* Dashboard */

    public RobotContainer() {

        switch (Constants.ROBOT_MODE) {
            case REAL:
                talonSubsystem = new TalonSubsystem(new TalonIOSparkMAX(12));
                break;

            case SIM:
                talonSubsystem = new TalonSubsystem(new TalonIOSim());
                break;

            default:
                // Replayed robot
                talonSubsystem = new TalonSubsystem(new TalonIO() {});
                break;
        }

        configureBindings();
    }

    private void configureBindings() {
        operator.a().onTrue(talonSubsystem.setTargetTalonRad(Math.PI / 4));
        operator.b().onTrue(talonSubsystem.setTargetTalonRad(0));
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
