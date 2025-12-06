
package frc.robot;

import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.BucketCmd;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.subsystems.BucketSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.SwerveModule;

public class RobotContainer {

    private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
    private final BucketSubsystem bucketSubsystem = new BucketSubsystem();

    private final XboxController controller = new XboxController(OIConstants.kDriverControllerPort);

    public RobotContainer() {
        swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
                swerveSubsystem,
                () -> -controller.getLeftY(),
                () -> controller.getLeftX(), 
                controller::getRightX,             
                true
        ));
        
        bucketSubsystem.setDefaultCommand(new BucketCmd(
                bucketSubsystem,
                controller::getLeftBumperButton,
                controller::getRightBumperButton
        ));
    }
}

