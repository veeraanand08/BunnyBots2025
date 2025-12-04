
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;
import frc.robot.commands.LimeLight;

public class RobotContainer {

    private final LimeLightSubsystem limeSubsystem = new LimeLightSubsystem();
    private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
    private final Joystick driverJoystick = new Joystick(OIConstants.kDriverControllerPort);
    private final XboxController controller = new XboxController(OIConstants.kDriverControllerPort);


    public RobotContainer() {
        limeSubsystem.setDefaultCommand(new LimeLight(
            limeSubsystem,
            controller::getYButton,
            swerveSubsystem
        ) );

        swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
                swerveSubsystem,
                () -> -driverJoystick.getRawAxis(OIConstants.kDriverXAxis),
                () -> driverJoystick.getRawAxis(OIConstants.kDriverYAxis),
                () -> driverJoystick.getRawAxis(OIConstants.kDriverRotAxis)));
                //swerveSubsystem,
                //controller::getLeftX,
                //controller::getLeftY,
                //controller::getRightX
    }
}

