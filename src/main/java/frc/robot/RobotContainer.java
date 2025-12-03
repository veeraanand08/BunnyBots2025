
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.commands.LimeLight;
import frc.robot.subsystems.LimeLightSubsystem;

public class RobotContainer {

    private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
    private final LimeLightSubsystem limeSub = new LimeLightSubsystem();
    private final Joystick driverJoystick = new Joystick(OIConstants.kDriverControllerPort);
    private final XboxController controller = new XboxController(OIConstants.kDriverControllerPort);


    public RobotContainer() {
        limeSub.setDefaultCommand(new LimeLight (controller::getYButton));

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

