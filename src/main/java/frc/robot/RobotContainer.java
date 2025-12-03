
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
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
        swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
                swerveSubsystem,
                () -> -controller.getRawAxis(OIConstants.kDriverXAxis),
                () -> controller.getRawAxis(OIConstants.kDriverYAxis),
                () -> controller.getRawAxis(OIConstants.kDriverRotAxis)));
                //swerveSubsystem,
                //controller::getLeftX,
                //controller::getLeftY,
                //controller::getRightX
                
        bucketSubsystem.setDefaultCommand(new BucketCmd(
                bucketSubsystem,
                controller::getLeftBumperButton,
                controller::getRightBumperButton
        ));
    }

}

