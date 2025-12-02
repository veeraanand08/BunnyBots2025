
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.BucketCmd;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.subsystems.BucketSubsystem;
import frc.robot.subsystems.SwerveSubsystem;

public class RobotContainer {

    private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
    private final BucketSubsystem bucketSubsystem = new BucketSubsystem();

    private final XboxController controller = new XboxController(OIConstants.kDriverControllerPort);

    public RobotContainer() {
        // HASAN FIX THIS
        // swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
        //         swerveSubsystem,
        //         () -> -driverJoystick.getRawAxis(OIConstants.kDriverXAxis),
        //         () -> driverJoystick.getRawAxis(OIConstants.kDriverYAxis),
        //         () -> driverJoystick.getRawAxis(OIConstants.kDriverRotAxis)));
        
        bucketSubsystem.setDefaultCommand(new BucketCmd(
                bucketSubsystem,
                controller::getLeftBumperButton,
                controller::getRightBumperButton
        ));
    }
}

