
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;
import frc.robot.commands.LimeLight;

public class RobotContainer {

    private final LimeLightSubsystem limeSubsystem = new LimeLightSubsystem();
    private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
    private final CommandXboxController  controller = new CommandXboxController(OIConstants.kDriverControllerPort);


    public RobotContainer() {
        // limeSubsystem.setDefaultCommand(new LimeLight(
        //     limeSubsystem,
        //     controller::getYButton,
        //     swerveSubsystem
        // ) );

        swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
                swerveSubsystem,
                controller::getLeftX,
                controller::getLeftY,
                controller::getRightX
        ));


        controller.y().whileTrue(new LimeLight(limeSubsystem, swerveSubsystem));
    
    }

    
}

