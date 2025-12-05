package frc.robot.commands;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.LimeLightSubsystem;
import frc.robot.subsystems.SwerveSubsystem;


public class LimeLight extends Command {

    private final SwerveSubsystem swerveSubsystem;
    private final LimeLightSubsystem limeSub; 

    public LimeLight(LimeLightSubsystem limeSub, SwerveSubsystem swerveSubsystem){
        this.limeSub = limeSub;
        this.swerveSubsystem = swerveSubsystem;

        addRequirements(limeSub, swerveSubsystem);
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
            double turningSpeed = limeSub.limelightAngle();
            double xSpeed = limeSub.limelightRange();

            ChassisSpeeds chassisSpeeds = new ChassisSpeeds(xSpeed, 0, turningSpeed);

            SwerveModuleState[] moduleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);

            swerveSubsystem.setModuleStates(moduleStates);

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
