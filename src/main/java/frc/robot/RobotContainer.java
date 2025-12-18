
package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.SwerveModule;
import frc.robot.Constants.IOConstants;
import frc.robot.Constants.SwerveConstants;

public class RobotContainer {

    private final DriveTrain swerveSubsystem = new DriveTrain(
        new SwerveModule(SwerveConstants.kBackRightDriveMotorId, SwerveConstants.kBackRightTurnMotorId, SwerveConstants.kBackRightDriveMotorReversed, SwerveConstants.kBackRightTurnMotorReversed, "BR"),
        new SwerveModule(SwerveConstants.kBackLeftDriveMotorId, SwerveConstants.kBackLeftTurnMotorId, SwerveConstants.kBackLeftDriveMotorReversed, SwerveConstants.kBackLeftTurnMotorReversed, "BL"),
        new SwerveModule(SwerveConstants.kFrontRightDriveMotorId, SwerveConstants.kFrontRightTurnMotorId, SwerveConstants.kFrontRightDriveMotorReversed, SwerveConstants.kFrontRightTurnMotorReversed, "FR"),
        new SwerveModule(SwerveConstants.kFrontLeftDriveMotorId, SwerveConstants.kFrontLeftTurnMotorId, SwerveConstants.kFrontLeftDriveMotorReversed, SwerveConstants.kFrontLeftTurnMotorReversed, "FL")
    );
    private final CommandXboxController  controller = new CommandXboxController(IOConstants.kDriverControllerPort);


    public RobotContainer() {

        swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
                swerveSubsystem,
                controller::getLeftX,
                controller::getLeftY,
                controller::getRightX
        ));
    
    }

    
}

