package frc.robot.commands;
import java.util.function.Supplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.LimeLightSubsystem;
import frc.robot.subsystems.SwerveSubsystem;


public class LimeLight extends Command {

    private final SwerveSubsystem swerveSubsystem;
    private final LimeLightSubsystem limeSub;
    private final Supplier<Boolean> YKey;   

    public LimeLight(LimeLightSubsystem limeSub, Supplier<Boolean> YKey, SwerveSubsystem swerveSubsystem){
        this.YKey = YKey;
        this.limeSub = limeSub;
        this.swerveSubsystem = swerveSubsystem;

        addRequirements(limeSub, swerveSubsystem);
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        if (YKey.get()) {
            double turningSpeed = limeSub.limelight_Angle();
            double xSpeed = limeSub.limelight_Range();

            ChassisSpeeds chassisSpeeds = new ChassisSpeeds(xSpeed, 0, turningSpeed);

            SwerveModuleState[] moduleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);

            swerveSubsystem.setModuleStates(moduleStates);
        }
        
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
